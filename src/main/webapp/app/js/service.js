var wqtServices = angular.module('wqtServices', [ 'ngResource' ]);

wqtServices.factory('wqtService', [ '$resource', function($resource) {
	return $resource('../rest/wqt/:action', {}, {
			getTicket : {
					method : 'GET',
					params : {
						action : 'ticket'
					},
					isArray : false
			},
			addTicket : {
					method : 'POST',
					params : {
						action : 'ticket'
					},
					isArray : false
			}
	});
} ]);