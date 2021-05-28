angular.module('app').controller('cartController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.loadCart = function (page) {
        $http({
            url: '/market/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.cartDto = response.data;
        });
    };

    $scope.addToCart = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
        });
    }


    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.aprilMarketCurrentUser = {username: $scope.user.username, token: response.data.token};

                    $scope.currentUsername = $scope.user.name;

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $scope.showMyOrders();
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


    $scope.createOrder = function (phone, address) {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'POST',
            params: {
                phone: phone,
                address: address
            }
        })
        .then(function (response) {
            $scope.loadCart();
        });
    };


    $scope.clearCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/clear',
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.loadCart();

});

