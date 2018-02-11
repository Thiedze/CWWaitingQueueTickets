var wqtLoginApp = angular.module('wqtLoginApp', []);

wqtLoginApp.config(
  function($locationProvider) {

    $locationProvider.html5Mode({
        enabled : true,
        requireBase : false
    });
});

var wqtLoginApp = angular.module('wqtLoginApp', []);

wqtLoginApp.controller('wqtLoginController', function($scope, $location) {
	var error = $location.search().error;
	$scope.message = "";

	if (error === "true") {
		$scope.message = "Ungültige Anmeldung";
	}
});