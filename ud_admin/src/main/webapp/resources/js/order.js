;
(function ($, window, document) {
    $(document).ready(function () {
        angular.module('OrderModule', ['BaseModule'])
            .controller('AddOrderCtrl', AddOrderCtrl)
            .controller('EditOrderCtrl', EditOrderCtrl)
            .controller('ListOrderCtrl', ListOrderCtrl)
            .factory('orderService', orderService);
        orderService.$inject = ['httpService'];
        ListOrderCtrl.$inject = ['orderService'];
        AddOrderCtrl.$inject = ['orderService'];
        EditOrderCtrl.$inject = ['orderService', 'util'];
        angular.bootstrap(document, ['OrderModule']);
    });
    function ListOrderCtrl(orderService) {
        var that = this;
        that.deleteItem = deleteItem;
        function deleteItem(id) {
            if (!confirm('确认要删除该记录？')) {
                return;
            }
            orderService.deleteEntity(id).then(
                function (data) {
                    if (data.code === 'ok') {
                        window.location.href = window.contextPath + '/admin/order/list.do';
                    }
                }
            );
        }
    }

    function AddOrderCtrl(orderService) {
        var that = this;
        that.entity = {
    		studentId: '',
    		driverId: '',
    		type: '',
    		course: '',
    		address: '',
    		longitude: '',
    		latitude: '',
    		reserveDay: '',
    		reserveStartTime: '',
    		reserveEndTime: '',
    		status: '',
    		serviceCode: '',
    		trainStartTime: '',
    		trainEndTime: '',
    		createTime: ''
        };
        that.create = create;
        function create() {
            orderService.create(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                        window.location.href = window.contextPath + '/admin/order/list.do';
                    }
                }
            );
        }
    }

    function EditOrderCtrl(orderService, util) {
        var that = this;
        that.entity = {
    		studentId: '',
    		driverId: '',
    		type: '',
    		course: '',
    		address: '',
    		longitude: '',
    		latitude: '',
    		reserveDay: '',
    		reserveStartTime: '',
    		reserveEndTime: '',
    		status: '',
    		serviceCode: '',
    		trainStartTime: '',
    		trainEndTime: '',
    		createTime: ''
        };
        that.update = update;
        activate();
        function activate() {
            var id = util.getUrlParam('id');
            orderService.get(id).then(
                function (result) {
                    that.entity = result.data;
                }
            );
        }

        function update() {
            orderService.update(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                        window.location.href = window.contextPath + '/admin/order/list.do';
                    }
                }
            );
        }
    }

    function orderService(httpService) {
        return {
            deleteEntity: deleteEntity,
            create: create,
            update: update,
            get: get
        };

        function get(id) {
            return httpService.callAPI('/admin/order/get.do?id=' + id);
        }

        function deleteEntity(id) {
            return httpService.callAPI('/admin/order/delete.do?id=' + id);
        }

        function create(entity) {
            return httpService.callAPI('/admin/order/create.do', {
                data: entity
            });
        }

        function update(entity) {
            return httpService.callAPI('/admin/order/update.do', {
                data: entity
            });
        }
    }
})(jQuery, window, document);