;
(function ($, window, document) {
    $(document).ready(function () {
        angular.module('DriverModule', ['BaseModule'])
            .controller('EditDriverCtrl', EditDriverCtrl)
            .controller('ListDriverCtrl', ListDriverCtrl)
            .factory('driverService', driverService);
        driverService.$inject = ['httpService'];
        ListDriverCtrl.$inject = ['driverService'];
        EditDriverCtrl.$inject = ['driverService', 'util'];
        angular.bootstrap(document, ['DriverModule']);
    });
    function ListDriverCtrl(driverService) {
        var that = this;
        that.disable = disable;
        function disable(id) {
            if (!confirm('确认注销吗？')) {
                return;
            }
            driverService.disable(id).then(
                function (data) {
                    if (data.code == 'ok') {
                        window.location.reload();
                    }
                }
            );
        }
    }

    function EditDriverCtrl(driverService, util) {
        var that = this;
        that.entity = {
            openid: '',
            driverLicense: '',
            idNumber: '',
            hasShop: '',
            address: '',
            longitude: '',
            latitude: '',
            driveAge: '',
            mobile: '',
            score: '',
            ub: '',
            status: ''
        };
        that.update = update;
        that.pass = pass;
        activate();
        function activate() {
            var id = util.getUrlParam('id');
            driverService.get(id).then(
                function (result) {
                    that.entity = result.data;
                }
            );
        }

        function update() {
            driverService.update(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                        window.location.href = window.contextPath + '/admin/driver/list.do';
                    }
                }
            );
        }

        function pass() {
            driverService.pass(that.entity.id).then(
                function (data) {
                    if (data.code == 'ok') {
                        alert('操作成功');
                    }
                }
            );
        }
    }

    function driverService(httpService) {
        return {
            deleteEntity: deleteEntity,
            create: create,
            update: update,
            get: get,
            disable: disable,
            pass: pass
        };

        function get(id) {
            return httpService.callAPI('/admin/driver/get.do?id=' + id);
        }

        function disable(id) {
            return httpService.callAPI('/admin/driver/disable.do?id=' + id);
        }

        function pass(id) {
            return httpService.callAPI('/admin/driver/pass.do?id=' + id);
        }

        function deleteEntity(id) {
            return httpService.callAPI('/admin/driver/delete.do?id=' + id);
        }

        function create(entity) {
            return httpService.callAPI('/admin/driver/create.do', {
                data: entity
            });
        }

        function update(entity) {
            return httpService.callAPI('/admin/driver/update.do', {
                data: entity
            });
        }
    }
})(jQuery, window, document);