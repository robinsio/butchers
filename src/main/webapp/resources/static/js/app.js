'use strict';

var app = angular.module('org.robins.io.butchers', [
        'ngRoute',
        'ngResource',
        'ngCookies']);

app.config(['$routeProvider', '$locationProvider', '$httpProvider', function ($routeProvider, $locationProvider, $httpProvider) {

    $routeProvider.when('/login', {
        templateUrl: 'resources/static/partials/pages/login.html',
        controller: 'loginCtrl'
    });

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

    /* Intercept http errors */
    var interceptor = function ($rootScope, $q, $location) {

        function success(response) {
            return response;
        }

        function error(response) {

            var status = response.status;
            var config = response.config;
            var method = config.method;
            var url = config.url;

            if (status == 401) {
                $location.path( "/login" );
                $rootScope.error = "Invalid login credentials, please try again";
            } else {
                $rootScope.error = method + " on " + url + " failed with status " + status;
            }

            return $q.reject(response);
        }

        return function (promise) {
            return promise.then(success, error);
        };
    };
    $httpProvider.responseInterceptors.push(interceptor);

} ]);

app.run(function($rootScope, $http, $location, $cookieStore, login) {

    /* Reset error when a new view is loaded */
    $rootScope.$on('$viewContentLoaded', function() {
        delete $rootScope.error;
    });

    $rootScope.hasRole = function(role) {
        if ($rootScope.user === undefined) {
            return false;
        }
        if ($rootScope.user.roles[role] === undefined) {
            return false;
        }
        return $rootScope.user.roles[role];
    };

    $rootScope.logout = function() {
        delete $rootScope.user;
        delete $http.defaults.headers.common['X-Auth-Token'];
        $cookieStore.remove('user');
        $location.path("/login");
    };

    /* Try getting valid user from cookie or go to login page */
    var originalPath = $location.path();

    $location.path("/login");

    var user = $cookieStore.get('user');

    if (user !== undefined) {
        $rootScope.user = user;
        $http.defaults.headers.common['X-Auth-Token'] = user.token;
        $location.path(originalPath);
    }

    $rootScope.$on("$routeChangeStart", function(event, next, prev) {
        if($rootScope.user == undefined) {
            $location.path("/login");
        }
    });
});