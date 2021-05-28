angular.module('app').controller('registrationController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.aprilMarketCurrentUser = {username: $scope.user.username, token: response.data.token};

                    $scope.currentUsername = $scope.user.name;
                    $scope.user.username = null;
                    $scope.user.password = null;

                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
    };

    $scope.clearUser = function () {
        delete $localStorage.aprilMarketCurrentUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.aprilMarketCurrentUser) {
            return true;
        } else {
            return false;
        }
    };

    // username, password, email
    $scope.createNewUser = function () {
        $http.post(contextPath + '/api/v1/users', $scope.newUser)
            .then(function (response) {
                $scope.newUser.username = null;
                $scope.newUser.password = null;
                $scope.newUser.email = null;
                document.location.href = "/market/";
            });
    };

});

