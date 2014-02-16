/**
 * Rest Client Service
 *
 * Service: Butchers API Calls
 **/

var client = angular.module('org.robins.io.butchers')

client.factory('customer', function($resource){
    return $resource('api/customer/:customerId', {customerId:'@customerId'},
        {
            'update': { method:'PUT' }
        });
});