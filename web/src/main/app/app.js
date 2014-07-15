var module = angular.module('app', [
    'ui.router',
    'templates',
    'app.controller.home',
    'app.controller.user',
    'app.controller.security',
    'app.service.messages'
]);
module.config(function ($stateProvider, $urlRouterProvider, $locationProvider) {
    $urlRouterProvider.otherwise('/home');
    $locationProvider.html5Mode(true);
    $locationProvider.hashPrefix('!');
});

module.controller('AppController', ['$scope', '$rootScope', '$state', 'MessagesService',
    function ($scope, $rootScope, $state, messages) {
        $rootScope._app = {
            initialized: false
        };
        $rootScope._messages = messages;
        $rootScope._page = {
            isActive: function (name) {
                return $state.current.name === name
            }
        };
        $rootScope._user = {
            roles: []
        };
        $rootScope._security = {
            hasRole: function (role) {
                if (!$rootScope._user.loggedIn) {
                    return false;
                }
                return _.contains($rootScope._user.roles, role);
            }
        };

        $scope.login = {
            submit: function() {
                alert($scope.login.username + ' ' + $scope.login.password)
            }
        };

        $('#main-menu').show();
        $rootScope._app.initialized = true;
    }]);


$(document).ready(function () {
    angular.bootstrap($('html'), ['app']);
});