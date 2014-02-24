/**
 * Rest Client Service
 *
 * Service: Butchers API Calls
 **/

var client = angular.module('org.robins.io.butchers')

client.factory('login', function($resource){
    return $resource('api/user/:action', {},
        {
            authenticate: {
                method: 'POST',
                params: {'action' : 'authenticate'},
                headers : {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }
    );
});