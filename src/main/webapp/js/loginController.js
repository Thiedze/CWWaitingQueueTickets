var wqtLoginApp = angular.module('wqtLoginApp', [])

wqtLoginApp.controller('wqtLoginController', function($scope, $location) {
	var error = $location.search().error;
	$scope.message = "";

	if (error === "true") {
		$scope.message = "Ungültige Anmeldung";
	}
});