(function () {
    'use strict';

    App.service('HospedeService', hospdeService);
    hospdeService.$inject = ['$http', 'URL_API'];

    function hospdeService($http, URL_API) {

        const HOSPEDE_URI = `${URL_API}/hospede`;

        this.buscarHospedePorFiltro = info => {

            return $http.get(`${HOSPEDE_URI}/byFiltro/${info}`);
        }

        this.salvarNovoHospede = hospede => {

            return $http.post(HOSPEDE_URI, hospede);
        }

        this.buscarHospedes = () => {

            return $http.get(HOSPEDE_URI);
        }

        this.deletarHospede = id => {

            return $http.delete(`${HOSPEDE_URI}/${id}`);
        }


    }

})();