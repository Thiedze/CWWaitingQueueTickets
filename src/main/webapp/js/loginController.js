var wqtLoginApp = angular.module('wqtLoginApp', []).config(
  $locationProvider.html5Mode({
      enabled : true,
      requireBase : false
  });
});

wqtLoginApp.controller('wqtLoginController', function($scope, $location) {
	var error = $location.search().error;
	$scope.message = "";

	if (error === "true") {
		$scope.message = "Ungültige Anmeldung";
	}
});