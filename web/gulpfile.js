var gulp = require("gulp"),
    src = gulp.src,
    dest = gulp.dest,
    clean = require("gulp-rimraf"),
    less = require("gulp-less"),
    autoprefixer = require("gulp-autoprefixer"),
    concat = require("gulp-concat"),
    minifyCss = require("gulp-minify-css"),
    html2js = require("gulp-ng-html2js"),
    inject = require("gulp-inject"),
    ngmin = require("gulp-ngmin"),
    uglify = require("gulp-uglify"),
    connect = require("connect"),
    util = require("gulp-util"),
    livereload = require("connect-livereload"),
    tinylr = require("tiny-lr"),
    fs = require("fs"),
    path = require("path"),
    karma = require("gulp-karma"),
    argv = require('yargs').argv;

var paths = {
    src: {
        base: 'src/main',
        files: 'src/main/**',
        less: {
            files: 'src/main/less/**/*.less'
        },
        templates: {
            files: 'src/main/app/**/*.tpl.html'
        },
        js: {
            files: 'src/main/app/**/*.js'
        },
        index: {
            file: 'src/main/index.html'
        },
        config: {
            file: '../config/web-config/web-config.json',
            template: 'src/main/config.js'
        }
    },
    test: {
        base: 'src/test',
        files: 'src/test/**'
    },
    build: {
        base: 'app',
        files: 'app/**',
        css: {
            dest: 'app/css',
            files: 'app/css/**/*.css'
        },
        js: {
            dest: 'app/src',
            files: ['app/src/**/*.js', 'app/templates.js', 'app/config.js']
        },
        templates: {
            file: 'templates.js',
            dest: 'app'
        },
        config: {
            dest: 'app'
        },
        index: {
            dest: 'app',
            file: 'app/index.html'
        },
        vendor: {
            dest: 'app/vendor',
            base: 'vendor',
            files: {
                css: 'app/vendor/**/*.css',
                js: 'app/vendor/**/*.js'
            }
        }
    },
    dist: {
        base: 'dist',
        css: {
            dest: 'dist/css',
            files: 'dist/css/**/*.css',
            file: 'app.min.css'
        },
        js: {
            dest: 'dist/js',
            files: 'dist/js/**/*.js',
            file: 'app.min.js'
        },
        index: {
            dest: 'dist'
        }
    },
    vendor: {
        css: [
            'vendor/bootstrap/dist/css/bootstrap.css'
        ],
        js: [
            'vendor/jquery/dist/jquery.min.js',
            'vendor/bootstrap/dist/js/bootstrap.min.js',
            'vendor/angular/angular.js',
            'vendor/angular-bootstrap/ui-bootstrap.min.js',
            'vendor/angular-ui-router/release/angular-ui-router.min.js'
        ]
    }
};

gulp.task("clean:build", function () {
    return src(paths.build.base, {read: false})
        .pipe(clean())
});

gulp.task("clean:dist", function () {
    return src(paths.dist.base, {read: false})
        .pipe(clean())
});

gulp.task("clean", ["clean:dist", "clean:build"]);

var createProperties = function(o, parentName) {
    var result = {};
    var createName = function(parentName, property) {
        return parentName + (parentName ? '.' : '') + property
    };
    for (var p in o) {
        if (typeof o[p] === 'object') {
            var deepProps = createProperties(o[p], createName(parentName, p));
            for (var dp in deepProps) {
                result[dp] = deepProps[dp]
            }
        } else {
            result[createName(parentName, p)] = o[p];
        }
    }
    return result
};

var toConfigConstant = function(filePath, file) {
    var conf = JSON.parse(file.contents.toString('utf8'));
    var properties = createProperties(conf, '');
    var result = '';
    for (var p in properties) {
        result += '.constant("' + p + '", "' + properties[p] + '")'
    }
    result += ';';
    util.log(argv);
    return result;
};

gulp.task("build:config", function() {
    return src(paths.src.config.template)
        .pipe(inject(src(paths.src.config.file), {
            starttag: '/* inject:constants */',
            endtag: '/* inject:constants:end */',
            transform: toConfigConstant
        }))
        .pipe(dest(paths.build.config.dest))
});

gulp.task("build:css", function () {
    return src(paths.src.less.files)
        .pipe(less())
        .pipe(autoprefixer())
        .pipe(dest(paths.build.css.dest))
});

gulp.task("build:templates", function () {
    return src(paths.src.templates.files)
        .pipe(html2js({
            moduleName: 'templates'
        }))
        .pipe(concat(paths.build.templates.file))
        .pipe(dest(paths.build.templates.dest))
});

gulp.task("build:js", function () {
    return src(paths.src.js.files)
        .pipe(dest(paths.build.js.dest))
});

var toPathBuild = function (path, vendor) {
    if (vendor) {
        var pathParts = path.split('/');
        var filename = pathParts[pathParts.length - 1];
        return '/' + paths.build.vendor.base + '/' + filename
    }
    return path.substring(path.indexOf(paths.build.base) + paths.build.base.length)
};

var toPathBuildCss = function (path, vendor) {
    return '<link rel="stylesheet" href="' + toPathBuild(path, vendor) + '">';
};

var toPathBuildCssVendor = function(path) {
    return toPathBuildCss(path, true)
};

var toPathBuildCssApp = function(path) {
    return toPathBuildCss(path, false)
};

var toPathBuildJs = function (path, vendor) {
    return '<script src="' + toPathBuild(path, vendor) + '"></script>'
};

var toPathBuildJsVendor = function(path) {
    return toPathBuildJs(path, true)
};

var toPathBuildJsApp = function(path) {
    return toPathBuildJs(path, false)
};

gulp.task("build:vendor", function () {
    return src([].concat(paths.vendor.css).concat(paths.vendor.js))
        .pipe(dest(paths.build.vendor.dest))
});

gulp.task("build:app", ["build:css", "build:js", "build:templates", "build:config"]);

var buildIndex = function() {
    return src(paths.src.index.file)
        .pipe(inject(src(paths.vendor.css, {read: false}), {
            starttag: '<!-- inject:vendor:css -->',
            transform: toPathBuildCssVendor
        }))
        .pipe(inject(src(paths.build.css.files, {read: false}), {
            starttag: '<!-- inject:app:css -->',
            transform: toPathBuildCssApp
        }))
        .pipe(inject(src(paths.vendor.js, {read: false}), {
            starttag: '<!-- inject:vendor:js -->',
            transform: toPathBuildJsVendor
        }))
        .pipe(inject(src(paths.build.js.files, {read: false}), {
            starttag: '<!-- inject:app:js -->',
            transform: toPathBuildJsApp
        }))
        .pipe(dest(paths.build.index.dest))
};

gulp.task("build:index", ["build:app", "build:vendor"], function () {
    return buildIndex();
});

gulp.task("build:index:dev", ["build:app"], function () {
    return buildIndex();
});

gulp.task("build", ["build:index"]);

gulp.task("build:dev", ["build:index:dev"]);

gulp.task("dist:css", ["build:css"], function () {
    return src([].concat(paths.vendor.css).concat(paths.build.css.files))
        .pipe(concat(paths.dist.css.file))
        .pipe(minifyCss())
        .pipe(dest(paths.dist.css.dest))
});

gulp.task("dist:js", ["build:js", "build:templates", "build:config"], function () {
    return src([].concat(paths.vendor.js).concat(paths.build.js.files))
        .pipe(concat(paths.dist.js.file))
        .pipe(ngmin())
        .pipe(uglify())
        .pipe(dest(paths.dist.js.dest))
});

var toPathDist = function (path) {
    return path.substring(path.indexOf(paths.dist.base) + paths.dist.base.length)
};

var toPathDistCss = function (path) {
    return '<link rel="stylesheet" href="' + toPathDist(path) + '">';
};

var toPathDistJs = function (path) {
    return '<script src="' + toPathDist(path) + '"></script>'
};

gulp.task("dist:index", ["build:index", "dist:css", "dist:js"], function () {
    return src(paths.src.index.file)
        .pipe(inject(src(paths.dist.js.files, {read: false}), {
            starttag: '<!-- inject:app:css -->',
            transform: toPathDistCss
        }))
        .pipe(inject(src(paths.dist.css.files, {read: false}), {
            starttag: '<!-- inject:app:js -->',
            transform: toPathDistJs
        }))
        .pipe(dest(paths.dist.index.dest))
});

gulp.task("dist", ["dist:index"]);

var startServer = function() {
    var port = 4000;
    var server = connect();
    server.use(livereload({port: 4002}))
        .use(connect.static(paths.build.base))
        .use(function(req, res) {
            if (!/.js|.css/.test(req.url)) {
                var stream = fs.createReadStream(paths.build.index.file);
                stream.pipe(res);
            }
        })
        .listen(port);
    util.log("Server up and running: http://localhost:" + port);
};

var lr;
var startLiveReload = function () {
    lr = tinylr();
    lr.listen(4002);
};

function notifyLiveReload(event) {
    var fileName = path.relative(paths.build.base, event.path);
    lr.changed({
        body: {
            files: [fileName]
        }
    });
}

gulp.task("dev", ["build"], function () {
    startServer();
    startLiveReload();
    gulp.watch(paths.build.files, notifyLiveReload);
    gulp.watch(paths.src.files, ["build:dev"]);
});

gulp.task("test", ["build"], function() {
    return gulp.src([paths.test.files].concat(paths.vendor.js).concat(paths.build.js.files))
        .pipe(karma({
            configFile: 'karma.conf.js',
            action: 'run'
        }))
        .on('error', function(err) {
            throw err;
        });
});