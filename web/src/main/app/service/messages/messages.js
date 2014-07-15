var module = angular.module('app.service.messages', []);

module.service('MessagesService', function () {
    return {
        // menu
        'menu.title.home': 'Home',
        'menu.title.user': 'User',
        'menu.title.security': 'Security',
        'menu.title.login': 'Sign in',

        // login
        'login.label.login': 'Login',
        'login.label.password': 'Password',
        'login.button.login': 'Submit'
    }
});