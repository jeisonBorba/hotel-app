(function () {
    'use strict';

    App.service('MensagemService', mensagemService);

    function mensagemService() {

        this.formatarError = error => {

            if (error.data && error.data.errors && error.data.errors.length) {
                return alert(error.data.errors[0]);
            }
            if (error.data && error.data.message) {
                return alert(error.data.message);
            }
    
            return alert('Ocorreu um erro! Favor tentar novamente');
        }

    }

})();