musician.controller('BandCtrl', function BaseCtrl($scope, Bands) {
	Bands.get(function (data) {
		$scope.bands = data;
	});
});