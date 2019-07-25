(function () {
    'use strict';

    App.service('CheckinService', checkinService);
    checkinService.$inject = ['$http', 'URL_API'];

    function checkinService($http, URL_API) {

        const CHECKIN_URI = `${URL_API}/checkin`;

        this.realizarCheckin = checkin => {

            return $http.post(CHECKIN_URI, checkin);
        }

        this.buscarCheckin = status => {

            return $http.get(`${CHECKIN_URI}/${status}`);
        }

    }

})();