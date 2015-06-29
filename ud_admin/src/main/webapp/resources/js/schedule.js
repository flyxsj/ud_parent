;
(function ($, window, document) {
    $(document).ready(function () {
        angular.module('ScheduleModule', ['BaseModule'])
            .controller('AddScheduleCtrl', AddScheduleCtrl)
            .controller('EditScheduleCtrl', EditScheduleCtrl)
            .controller('ListScheduleCtrl', ListScheduleCtrl)
            .factory('scheduleService', scheduleService);
        scheduleService.$inject = ['httpService'];
        ListScheduleCtrl.$inject = ['scheduleService'];
        AddScheduleCtrl.$inject = ['scheduleService'];
        EditScheduleCtrl.$inject = ['scheduleService', 'util'];
        angular.bootstrap(document, ['ScheduleModule']);
    });
    function ListScheduleCtrl(scheduleService) {
        var that = this;
        that.deleteItem = deleteItem;
        function deleteItem(id) {
            if (!confirm('确认要删除该记录？')) {
                return;
            }
            scheduleService.deleteEntity(id).then(
                function (data) {
                    if (data.code === 'ok') {
                        window.location.href = window.contextPath + '/admin/schedule/list.do';
                    }
                }
            );
        }
    }

    function AddScheduleCtrl(scheduleService) {
        var that = this;
        that.entity = {
    		driverId: '',
    		type: '',
    		day: '',
    		startTime: '',
    		endTime: '',
    		isAlwaysEnable: '',
    		createTime: ''
        };
        that.create = create;
        function create() {
            scheduleService.create(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                        window.location.href = window.contextPath + '/admin/schedule/list.do';
                    }
                }
            );
        }
    }

    function EditScheduleCtrl(scheduleService, util) {
        var that = this;
        that.entity = {
    		driverId: '',
    		type: '',
    		day: '',
    		startTime: '',
    		endTime: '',
    		isAlwaysEnable: '',
    		createTime: ''
        };
        that.update = update;
        activate();
        function activate() {
            var id = util.getUrlParam('id');
            scheduleService.get(id).then(
                function (result) {
                    that.entity = result.data;
                }
            );
        }

        function update() {
            scheduleService.update(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                        window.location.href = window.contextPath + '/admin/schedule/list.do';
                    }
                }
            );
        }
    }

    function scheduleService(httpService) {
        return {
            deleteEntity: deleteEntity,
            create: create,
            update: update,
            get: get
        };

        function get(id) {
            return httpService.callAPI('/admin/schedule/get.do?id=' + id);
        }

        function deleteEntity(id) {
            return httpService.callAPI('/admin/schedule/delete.do?id=' + id);
        }

        function create(entity) {
            return httpService.callAPI('/admin/schedule/create.do', {
                data: entity
            });
        }

        function update(entity) {
            return httpService.callAPI('/admin/schedule/update.do', {
                data: entity
            });
        }
    }
})(jQuery, window, document);