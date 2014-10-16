//musician.factory('musicianHttpInterceptor', function ($q, $window) {
//	return function (promise) {
//		return promise.then(function (response) {
//			// do something on success
//			return response;
//		}, function (response) {
//			// do something on error
//			return $q.reject(response);
//		});
//	};
//});

musician.factory('Bands', function ($resource) {
	return $resource('bands/:bandId',
		{
			bandId: "@bandId"
		},
		{
			get: {
				method: 'GET',
				isArray: true
			}
		}
	);
});