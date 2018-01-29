var wqtServices = angular.module('wqtServices', [ 'ngResource' ]);

wqtServices.factory('wqtService', [ '$resource', function($resource) {
	return $resource('../rest/wqt/:action', {}, {
			getTicket : {
					method : 'GET',
					params : {
						action : 'getTicket'
					},
					isArray : false
			},
			getTickets : {
					method : 'GET',
					params : {
						action : 'tickets'
					},
					isArray : true
			},
			createTicket : {
					method : 'POST',
					params : {
						action : 'createTicket'
					},
					isArray : false
			},
			getContactInformation : {
					method : 'GET',
					params : {
						action : 'contact_information'
					},
					isArray : false
			},
			updateTicket : {
					method : 'PUT',
					params : {
						action : 'updateTicket'
					},
					isArray : true
			},
			checkTickets : {
					method : 'PATCH',
					params : {
						action : 'checkTickets'
					},
					isArray : false
			},
			startTicket : {
					method : 'PUT',
					params : {
						action : 'startTicket'
					},
					isArray : true
			}
	});
} ]);