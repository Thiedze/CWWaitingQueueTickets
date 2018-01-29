wqtControllers.controller('wqtController', function($scope, wqtService) {

	wqtService.getContactInformation({}, function(data) {
		$scope.contactInformation = data;
	});

	wqtService.getTicket({
		type : "public"
	}, function(data) {
		$scope.currentTicket = data;
	});

	wqtService.getTicket({
		type : "private"
	}, function(data) {
		$scope.myTicket = data;
		if ($scope.myTicket.arrival !== undefined && $scope.myTicket.arrival !== null) {
			$scope.pickUpPeriod = new Date(new Date().getTime() - new Date($scope.myTicket.arrival).getTime());
		}
	});

	wqtService.getTickets({}, function(data) {
		$scope.tickets = data;
	});

	wqtService.checkTickets();

	$scope.createTicket = function() {
		wqtService.createTicket(function(data) {
			$scope.myTicket = data;
		});
	};

	$scope.updateTicket = function() {
		wqtService.updateTicket(function(data) {
			$scope.tickets = data;
		});
	}

	$scope.startTicket = function() {
		wqtService.startTicket(function(data) {
			$scope.tickets = data;
		});
	}
});