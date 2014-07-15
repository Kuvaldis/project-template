var module = angular.module('app.controller.user', [
    'ui.router'
]);

module.config(function ($stateProvider) {
    $stateProvider.state('/user', {
        url: '/user',
        templateUrl: 'controller/user/user.tpl.html',
        controller: 'UserController'
    });
});

module.controller('UserController', function ($scope) {
    $scope.telephone = '555-33-33'
});