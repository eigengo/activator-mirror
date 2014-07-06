angular.module('mirror', []);

angular.module('mirror').controller('MirrorController',
    function($scope, $location, $http, $window) {
        $scope.subject = $location.search().subject;

        $scope.insertTimeline = function() {
            console.log("ere);");
            $http({method: 'POST', url: '/timeline', headers: { 'Subject': $scope.subject }, data: $scope.timelineMessage}).
                success(function(data, status, headers, config) {
                    // this callback will be called asynchronously
                    // when the response is available
                    alert('it worked!');
                }).
                error(function(data, status, headers, config) {
                    $window.location.href = '/oauth2callback';
                });
        };
    });