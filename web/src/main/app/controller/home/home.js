var module = angular.module('app.controller.home', [
    'ui.router'
]);

module.config(function ($stateProvider) {
    $stateProvider.state('/home', {
        url: '/home',
        templateUrl: 'controller/home/home.tpl.html',
        controller: 'HomeController'
    });
});

module.controller('HomeController', function ($scope) {
    $scope.greetings = 'Hi!'
});