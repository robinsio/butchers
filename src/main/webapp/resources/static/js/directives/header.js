/**
 * Header Directive
 */
angular.module('org.robins.io.butchers')
    .directive('header', function () {
        return {
            restrict: 'A',
            replace: true,
            scope: {user: '=', active: '='},
            templateUrl: "resources/static/partials/directives/header.html",
            controller: ['$scope', '$window', function ($scope, $window) {

                $scope.isActive = function($type){
                    if($scope.active == $type) return {active: true}
                }
            }]
        }
    });