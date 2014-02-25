/**
 * Home Controller
 */
angular.module('org.robins.io.butchers')
    .controller('loginCtrl', function ($scope, $rootScope, $http, $cookieStore, $location, login) {

        $scope.login = function() {
            login.authenticate($.param({username: $scope.username, password: $scope.password}), function(user) {
                $rootScope.user = user;
                $http.defaults.headers.common['X-Auth-Token'] = user.token;
                $cookieStore.put('user', user);
                $location.path("/");
            });
        };
    });