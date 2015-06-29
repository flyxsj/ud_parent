;
(function ($, window, document) {
    $(document).ready(function () {
        angular.module('StudentModule', ['BaseModule'])
            .controller('AddStudentCtrl', AddStudentCtrl)
            .controller('EditStudentCtrl', EditStudentCtrl)
            .controller('ListStudentCtrl', ListStudentCtrl)
            .factory('studentService', studentService);
        studentService.$inject = ['httpService'];
        ListStudentCtrl.$inject = ['studentService'];
        AddStudentCtrl.$inject = ['studentService'];
        EditStudentCtrl.$inject = ['studentService', 'util'];
        angular.bootstrap(document, ['StudentModule']);
    });
    function ListStudentCtrl(studentService) {
        var that = this;
        that.deleteItem = deleteItem;
        function deleteItem(id) {
            if (!confirm('确认要删除该记录？')) {
                return;
            }
            studentService.deleteEntity(id).then(
                function (data) {
                    if (data.code === 'ok') {
                        window.location.href = window.contextPath + '/admin/student/list.do';
                    }
                }
            );
        }
    }

    function AddStudentCtrl(studentService) {
        var that = this;
        that.entity = {
    		openid: '',
    		mobile: '',
    		bean: '',
    		ub: '',
    		createTime: ''
        };
        that.create = create;
        function create() {
            studentService.create(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                        window.location.href = window.contextPath + '/admin/student/list.do';
                    }
                }
            );
        }
    }

    function EditStudentCtrl(studentService, util) {
        var that = this;
        that.entity = {
    		openid: '',
    		mobile: '',
    		bean: '',
    		ub: '',
    		createTime: ''
        };
        that.update = update;
        activate();
        function activate() {
            var id = util.getUrlParam('id');
            studentService.get(id).then(
                function (result) {
                    that.entity = result.data;
                }
            );
        }

        function update() {
            studentService.update(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                        window.location.href = window.contextPath + '/admin/student/list.do';
                    }
                }
            );
        }
    }

    function studentService(httpService) {
        return {
            deleteEntity: deleteEntity,
            create: create,
            update: update,
            get: get
        };

        function get(id) {
            return httpService.callAPI('/admin/student/get.do?id=' + id);
        }

        function deleteEntity(id) {
            return httpService.callAPI('/admin/student/delete.do?id=' + id);
        }

        function create(entity) {
            return httpService.callAPI('/admin/student/create.do', {
                data: entity
            });
        }

        function update(entity) {
            return httpService.callAPI('/admin/student/update.do', {
                data: entity
            });
        }
    }
})(jQuery, window, document);