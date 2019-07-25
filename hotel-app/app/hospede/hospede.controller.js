(function() {
    'use strict';

    App.controller('HospedeController', hospedeController);
    hospedeController.$inject = ['HospedeService', 'MensagemService'];

    function hospedeController(HospedeService, MensagemService) {

        const vm = this;
    
        vm.init = init;
        vm.buscarHospedes = buscarHospedes;
        vm.salvarNovoHospede = salvarNovoHospede;
        vm.editarHospede = editarHospede;
        vm.deletarHospede = deletarHospede;
    
        init();
    
        function salvarNovoHospede() {

            HospedeService.salvarNovoHospede(vm.hospede)
                .then(() => notificarEBuscarHospedes('Hospede salvo com sucesso!'))
                .then(populaHospede)
                .catch(MensagemService.formatarError)
                .finally(() => vm.hospede = {});
    
        };

        function notificarEBuscarHospedes(mensagem) {

            alert(mensagem);
            return HospedeService.buscarHospedes();
        } 
    
        function editarHospede(hospede) {
    
            vm.hospede = {
                id: hospede.id,
                nome: hospede.nome,
                documento: Number(hospede.documento),
                telefone: Number(hospede.telefone)
            }
        }
    
        function deletarHospede(hospedeId) {

            HospedeService.deletarHospede(hospedeId)
                .then(() => notificarEBuscarHospedes('Hospede deletado com sucesso!'))
                .then(populaHospede)
                .catch(MensagemService.formatarError);
        }
    
        function init() {
    
            vm.hospedes = [];
            vm.hospede = {
                nome: '',
                documento: '',
                telefone: ''
            }
            vm.filtro = {
                hospede: {
    
                }
            };
    
            buscarHospedes();
        }
    
        function buscarHospedes() {
    
            HospedeService.buscarHospedes()
                .then(populaHospede)
                .catch(MensagemService.formatarError);
    
        }

        function populaHospede({ data }) {
         
           vm.hospedes = data.content;
        }

    }

})();