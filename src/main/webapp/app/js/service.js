var wqtServices = angular.module('wqtServices', [ 'ngResource' ]);

wqtServices.factory('wqtService', [ '$resource', function($resource) {
	return $resource('../rest/wqt/:action', {}, {
		currentTicket : {
				method : 'GET',
				params : {
					action : 'currentTicket'
				},
				isArray : false
		}
	});
} ]);