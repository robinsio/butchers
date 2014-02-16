/**
 * Customers Controller
 */
var controllers = angular.module('org.robins.io.butchers')

controllers.controller('customerListCtrl', function ($scope, $window, customer) {

    $scope.customers = customer.query();

    $scope.viewCustomerDetail = function (customerId) {
        $window.location.href = '#/customer/' + customerId;
    }

    $scope.viewCustomerDetailEdit = function (customerId) {
        $window.location.href = '#/customer/' + customerId + "/edit";
    }

    $scope.viewCustomerCreate = function(){
        $window.location.href = "#/customer/new"
    }
});

controllers.controller('customerDetailCtrl', function ($scope, $window, $routeParams, customer) {

    var customerId = $routeParams.customerId;

    $scope.customer = customer.get({customerId: customerId});

    $scope.viewCustomerDetailEdit = function (customerId) {
        $window.location.href = '#/customer/' + customerId + "/edit";
    }
});

controllers.controller('customerDetailEditCtrl', function ($scope, $window, $routeParams, customer) {

    var customerId = $routeParams.customerId;

    $scope.customer = customer.get({customerId: customerId});

    $scope.updateCustomer = function (customer) {
        customer.$update({customerId: customerId}, function(res){
            $window.location.href = "#customer/" + customerId
        });
    }
});

controllers.controller('customerDetailNewCtrl', function ($scope, $window, customer) {

    $scope.customer = new customer;

    $scope.createCustomer = function (customer) {
        customer.$save(function(res){
            $window.location.href = "#customer/" + res.id;
        });
    }
});
