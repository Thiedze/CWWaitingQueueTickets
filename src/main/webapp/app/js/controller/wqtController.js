wqtControllers.controller('wqtController', function($scope, wqtService) {

	wqtService.getTicket({
		type : "public"
	}, function(data) {
		$scope.currentTicket = data;
	});

	wqtService.getTicket({
		type : "private"
	}, function(data) {
		$scope.myTicket = data;
	});

	$scope.getNextTicket = function() {
		wqtService.addTicket(function(data) {
			$scope.myTicket = data;
		});
	};
});