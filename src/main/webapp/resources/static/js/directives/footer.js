/**
 * Footer Directive
 */
angular.module('org.robins.io.butchers')
    .directive('footer', function () {
        return {
            restrict: 'A',
            replace: true,
            templateUrl: "resources/static/partials/directives/footer.html",
            controller: ['$scope', function ($scope) {}]
        }
    });