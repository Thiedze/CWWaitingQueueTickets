wqtControllers.controller('wqtController', function($scope, wqtService) {

	wqtService.currentTicket(function(data) {
		$scope.currentTicket = data;
	});

	$scope.myTicket = 'Keine';

	$scope.getNextTicket = function() {
		WaitingQueueTicketService.nextTicket(function(data) {
			$scope.myTicket = data;
		}, function(error) {
			this.errorMessage = error;
		});
	};
});