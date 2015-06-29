;
(function ($, window, document, undefined) {
    $(document).ready(function () {
        angular.module('DriverModule', ['BaseModule'])
            .controller('AddDriverCtrl', AddDriverCtrl)
            .factory('driverService', driverService);
        driverService.$inject = ['httpService'];
        AddDriverCtrl.$inject = ['driverService', 'util'];
        angular.bootstrap(document, ['DriverModule']);
    });
    function AddDriverCtrl(driverService, util) {
        var that = this;
        that.entity = {
            openid: 'test-openid' + (+new Date()),
            driverLicense: '',
            idNumber: '',
            address: '',
            longitude: '',
            latitude: '',
            driveAge: '',
            mobile: ''
        };
        that.create = create;
        activate();
        function create() {
            var obj = angular.copy(that.entity);
            obj.hasShop = $('select').val();
            driverService.create(obj).then(
                function (data) {
                    if (data.code == 'ok') {
                        alert('注册成功');
                    }
                }
            );
        }

        function activate() {
        }
    }

    function driverService(httpService) {
        return {
            create: create
        };
        function create(entity) {
            return httpService.callAPI('/driver/rest/create.do', {
                data: entity
            });
        }
    }
})(jQuery, window, document);