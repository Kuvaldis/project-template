var module = angular.module('app.controller.security', [
    'ui.router'
]);

module.config(function ($stateProvider) {
    $stateProvider.state('/security', {
        url: '/security',
        templateUrl: 'controller/security/security.tpl.html',
        controller: 'SecurityController'
    });
});

module.controller('SecurityController', function ($scope) {
    $scope.telephone = '555-33-33'
});