'use strict';
App.directive('datePicker', function () {
    return {
        require: 'ngModel',
        restrict: 'A',
        link,
        scope: {
            dateFormat: '='
        }
    }

    function link(scope, element, attrs, ngModel) {
        angular.element(element).datetimepicker({
            format: scope.dateFormat || 'LL',
            locale: 'pt-br',
            showClose: true
        }).on('dp.change', e => {
            ngModel.$setViewValue(e.date.toISOString());
            ngModel.$commitViewValue();
        });
    }
});