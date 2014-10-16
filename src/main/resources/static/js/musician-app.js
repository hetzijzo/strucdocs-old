var musician = angular.module('musician-app', ['ngResource'], function () {
	// Disabled fading because this causes and angular error
	//$dialogProvider.options({backdropClick: false, modalFade: false});
});

musician.config(function ($routeProvider, $locationProvider, $httpProvider) {
	$httpProvider.responseInterceptors.push('musicianHttpInterceptor');
	var progressFunction = function (data, headersGetter) {
		//showLoading();
		return data;
	};
	$httpProvider.defaults.transformRequest.push(progressFunction);

	var interceptor = ['$rootScope', '$q', function (scope, $q, $dialog) {
		function success(response) {
			return response;
		}

		function error(response) {
			var status = response.status;

			if (status == 400) {
				return $q.reject(response);
			} else if (status == 401) {
				window.location = "./login?timeout=1";
			} else if (status == 403 || status == 404 || status == 500) {
				bootbox.alert(response.data.message);
			}

			// otherwise
			return $q.reject(response.data);
		}

		return function (promise) {
			return promise.then(success, error);
		}
	}];
	$httpProvider.responseInterceptors.push(interceptor);
});