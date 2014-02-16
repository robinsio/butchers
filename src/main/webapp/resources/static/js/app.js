'use strict';

angular.module('org.robins.io.butchers', [
        'ngRoute',
        'ngResource'])
    .config(['$routeProvider', function ($routeProvider) {

        $routeProvider.when('/home', {
            templateUrl: 'resources/static/partials/pages/home.html',
            controller: 'homeCtrl'
        });

        $routeProvider.when('/customer', {
            templateUrl: 'resources/static/partials/pages/customer-list.html',
            controller: 'customerListCtrl'
        });

        $routeProvider.when('/customer/new', {
            templateUrl: 'resources/static/partials/pages/customer-detail-new.html',
            controller: 'customerDetailNewCtrl'
        });

        $routeProvider.when('/customer/:customerId', {
            templateUrl: 'resources/static/partials/pages/customer-detail.html',
            controller: 'customerDetailCtrl'
        });

        $routeProvider.when('/customer/:customerId/edit', {
            templateUrl: 'resources/static/partials/pages/customer-detail-edit.html',
            controller: 'customerDetailEditCtrl'
        });

        $routeProvider.otherwise({redirectTo: '/home'});
    }]);