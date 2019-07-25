var App = angular.module('App', [
    'smart-table', 
    'ui.mask', 
    'ui.router'
]);

App.constant('URL_API',  'http://localhost:8080/api' );

App.config(function($stateProvider, $urlRouterProvider, $locationProvider) {
    $locationProvider.hashPrefix('');
    $urlRouterProvider.otherwise('/');

    var checkinState = {
        name: 'checkin',
        url: '/',
        views: {
            'conteudo': {
                templateUrl: 'checkin/checkin.html',
                controller: 'CheckinController as vm'
            }
        }
    }
  
    var hospedeState = {
        name: 'hospede',
        url: '/hospede',
        views: {
            'conteudo': {
                templateUrl: 'hospede/hospede.html',
                controller: 'HospedeController as vm'
            }
        }
    }
  
    $stateProvider.state(checkinState);
    $stateProvider.state(hospedeState);
});


App.controller('MainController', function ($http, $timeout) {
    'ngInject';


});
