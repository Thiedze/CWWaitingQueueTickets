var wqtApp = angular.module('wqtApp', [ 'ngRoute', 'ngSanitize', 'wqtServices', 'wqtControllers' ]);

wqtApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.otherwise({
		templateUrl : 'index.html',
    			controller : 'wqtController'
	});
} ]);

var wqtControllers = angular.module('wqtControllers', []);