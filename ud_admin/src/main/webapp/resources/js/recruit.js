;
(function ($, window, document) {
    $(document).ready(function () {
        angular.module('RecruitModule', ['BaseModule'])
            .controller('AddRecruitCtrl', AddRecruitCtrl)
            .controller('EditRecruitCtrl', EditRecruitCtrl)
            .controller('ListRecruitCtrl', ListRecruitCtrl)
            .factory('recruitService', recruitService);
        recruitService.$inject = ['httpService'];
        ListRecruitCtrl.$inject = ['recruitService'];
        AddRecruitCtrl.$inject = ['recruitService'];
        EditRecruitCtrl.$inject = ['recruitService', 'util'];
        angular.bootstrap(document, ['RecruitModule']);
    });
    function ListRecruitCtrl(recruitService) {
        var that = this;
        that.deleteItem = deleteItem;
        function deleteItem(id) {
            if (!confirm('确认要删除该记录？')) {
                return;
            }
            recruitService.deleteEntity(id).then(
                function (data) {
                    if (data.code === 'ok') {
                        window.location.href = window.contextPath + '/admin/recruit/list.do';
                    }
                }
            );
        }
    }

    function AddRecruitCtrl(recruitService) {
        var that = this;
        that.entity = {
    		schoolName: '',
    		price: '',
    		scope: '',
    		address: '',
    		phone: '',
    		introduce: '',
    		status: '',
    		createTime: '',
    		updateTime: ''
        };
        that.create = create;
        function create() {
            recruitService.create(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                        window.location.href = window.contextPath + '/admin/recruit/list.do';
                    }
                }
            );
        }
    }

    function EditRecruitCtrl(recruitService, util) {
        var that = this;
        that.entity = {
    		schoolName: '',
    		price: '',
    		scope: '',
    		address: '',
    		phone: '',
    		introduce: '',
    		status: '',
    		createTime: '',
    		updateTime: ''
        };
        that.update = update;
        activate();
        function activate() {
            var id = util.getUrlParam('id');
            recruitService.get(id).then(
                function (result) {
                    that.entity = result.data;
                }
            );
        }

        function update() {
            recruitService.update(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                        window.location.href = window.contextPath + '/admin/recruit/list.do';
                    }
                }
            );
        }
    }

    function recruitService(httpService) {
        return {
            deleteEntity: deleteEntity,
            create: create,
            update: update,
            get: get
        };

        function get(id) {
            return httpService.callAPI('/admin/recruit/get.do?id=' + id);
        }

        function deleteEntity(id) {
            return httpService.callAPI('/admin/recruit/delete.do?id=' + id);
        }

        function create(entity) {
            return httpService.callAPI('/admin/recruit/create.do', {
                data: entity
            });
        }

        function update(entity) {
            return httpService.callAPI('/admin/recruit/update.do', {
                data: entity
            });
        }
    }
})(jQuery, window, document);