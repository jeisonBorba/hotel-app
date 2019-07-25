(function () {
    'use strict';

    App.controller('CheckinController', checkinController);
    checkinController.$inject = ['CheckinService', 'HospedeService', 'MensagemService'];

    function checkinController(CheckinService, HospedeService, MensagemService) {

        const vm = this;
    
        vm.init = init;
        vm.buscarCheckin = buscarCheckin;
        vm.buscarHospedePorFiltro = buscarHospedePorFiltro;
        vm.realizarCheckin = realizarCheckin;
        vm.onChangeDataSaida = onChangeDataSaida;
        vm.onChangeDataEntrada = onChangeDataEntrada;
        vm.somarValorGasto = somarValorGasto;
    
        init();
    
        function formatDatas() {
    
            if (vm.novoCheckin.dataEntrada) {
                vm.novoCheckin.dataEntrada = moment(vm.novoCheckin.dataEntrada, 'YYYY-MM-DD').toISOString();
            }
            if (vm.novoCheckin.dataSaida) {
                vm.novoCheckin.dataSaida = moment(vm.novoCheckin.dataSaida, 'YYYY-MM-DD').toISOString();
            }
        }
    
        function buscarHospedePorFiltro() {
            
            HospedeService.buscarHospedePorFiltro(vm.filtro.hospede.info)
                .then(response => vm.listHospedes = response.data)
                .catch(MensagemService.formatarError);
        }
    
        function realizarCheckin() {
    
            if (!vm.novoCheckin) {
                return alert('Favor informar os dados para checkin!')
            }
    
            formatDatas();

            CheckinService.realizarCheckin(vm.novoCheckin)
                .then(() => {
                    vm.novoCheckin = {};
                    alert('Registro adicionado com sucesso!');
                    vm.buscarCheckin();
                })
                .catch(MensagemService.formatarError);
        }
    
        function onChangeDataSaida() {
    
            if (moment(vm.novoCheckin.dataEntrada).isAfter(moment(vm.novoCheckin.dataSaida))) {
                alert('A data de saída não pode ser menor que a data de entrada.');
                vm.novoCheckin.dataSaida = null;
            }
        }
    
        function onChangeDataEntrada() {
    
            if (moment().isAfter(moment(vm.novoCheckin.dataEntrada))) {
                alert('A data de entrada não pode ser menor que a data atual.');
                vm.novoCheckin.dataEntrada = null;
            }
        }
    
        function init() {
    
            vm.checkIn = [];
            vm.filtro = {
                checkin: {
                    status: 'in'
                }
            };
    
            buscarCheckin();
        }
    
        function somarValorGasto(checkin) {
    
            return checkin.valorPago + checkin.valorUltimaHospedagem;
        }
    
        function buscarCheckin() {

            CheckinService.buscarCheckin(vm.filtro.checkin.status)
                .then(response => vm.checkins = response.data)
                .catch(MensagemService.formatarError);
        }

    }
})();