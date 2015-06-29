;
(function ($, window, document) {
    $(document).ready(function () {
        angular.module('ScheduleModule', ['BaseModule'])
            .controller('AddScheduleCtrl', AddScheduleCtrl)
            .controller('ListScheduleCtrl', ListScheduleCtrl)
            .factory('scheduleService', scheduleService);
        scheduleService.$inject = ['httpService'];
        ListScheduleCtrl.$inject = ['scheduleService'];
        AddScheduleCtrl.$inject = ['scheduleService'];
        angular.bootstrap(document, ['ScheduleModule']);
    });
    function ListScheduleCtrl(scheduleService) {
        var that = this;
        that.deleteItem = deleteItem;
        function deleteItem(id) {
            if (!confirm('确认删除吗？')) {
                return;
            }
            scheduleService.deleteEntity(id).then(
                function (data) {
                    if (data.code == 'ok') {
                        window.location.reload();
                    }
                }
            );
        }
    }

    function AddScheduleCtrl(scheduleService) {
        var that = this;
        that.entity = {
            driverId: '6',  //TODO
            type: 'exercise',
            day: '2015-07-02',
            startTime: '',
            endTime: '',
            isAlwaysEnable: ''
        };
        that.create = create;
        activate();
        function activate() {
            $('#txtDay').datepicker();
        }

        function create() {
            scheduleService.create(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                        alert('发布成功');
                    }
                }
            );
        }
    }

    function scheduleService(httpService) {
        return {
            create: create,
            get: get,
            deleteEntity: deleteEntity
        };

        function deleteEntity(id) {
            return httpService.callAPI('/schedule/rest/delete.do?id=' + id);
        }

        function get(id) {
            return httpService.callAPI('/schedule/rest/get.do?id=' + id);
        }

        function create(entity) {
            return httpService.callAPI('/schedule/rest/create.do', {
                data: entity
            });
        }
    }
})(jQuery, window, document);