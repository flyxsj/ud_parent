;
(function ($, window, document) {
    $(document).ready(function () {
        angular.module('AdminUserModule', ['BaseModule'])
            .controller('AddAdminUserCtrl', AddAdminUserCtrl)
            .controller('EditAdminUserCtrl', EditAdminUserCtrl)
            .controller('ListAdminUserCtrl', ListAdminUserCtrl)
            .factory('adminUserService', adminUserService);
        adminUserService.$inject = ['httpService'];
        ListAdminUserCtrl.$inject = ['adminUserService'];
        AddAdminUserCtrl.$inject = ['adminUserService'];
        EditAdminUserCtrl.$inject = ['adminUserService', 'util'];
        angular.bootstrap(document, ['AdminUserModule']);
    });
    function ListAdminUserCtrl(adminUserService) {
        var that = this;
        that.deleteItem = deleteItem;
        function deleteItem(id) {
            if (!confirm('确认要删除该用户？')) {
                return;
            }
            adminUserService.deleteEntity(id).then(
                function (data) {
                    if (data.code === 'ok') {
                        window.location.href = window.contextPath + '/admin/user/list.do';
                    }
                }
            );
        }
    }

    function AddAdminUserCtrl(adminUserService) {
        var that = this;
        that.entity = {
            username: '',
            password: '',
            roleType: '',
            remark: ''
        };
        that.create = create;
        function create() {
            adminUserService.create(that.entity).then(
                function (data) {
                    if (data.code == 'ok') {
                        window.location.href = window.contextPath + '/admin/user/list.do';
                    }
                }
            );
        }
    }

    function EditAdminUserCtrl(adminUserService, util) {
        var that = this;
        that.entity = {
            id: '',
            username: '',
            password: '',
            roleType: '',
            remark: ''
        };
        that.update = update;
        activate();
        function activate() {
            var id = util.getUrlParam('id');
            adminUserService.get(id).then(
                function (result) {
                    that.entity = result.data;
                }
            );
        }

        function update() {
            adminUserService.update(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                        window.location.href = window.contextPath + '/admin/user/list.do';
                    }
                }
            );
        }
    }

    function adminUserService(httpService) {
        return {
            deleteEntity: deleteEntity,
            create: create,
            update: update,
            get: get
        };

        function get(id) {
            return httpService.callAPI('/admin/user/get.do?id=' + id);
        }

        function deleteEntity(id) {
            return httpService.callAPI('/admin/user/delete.do?id=' + id);
        }

        function create(entity) {
            return httpService.callAPI('/admin/user/create.do', {
                data: entity
            });
        }

        function update(entity) {
            return httpService.callAPI('/admin/user/update.do', {
                data: entity
            });
        }
    }
})(jQuery, window, document);