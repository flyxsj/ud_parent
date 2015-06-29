;
(function ($, window, document) {
    $(document).ready(function () {
        angular.module('RecruitModule', ['BaseModule'])
            .controller('AddRecruitCtrl', AddRecruitCtrl)
            .controller('ListRecruitCtrl', ListRecruitCtrl)
            .factory('recruitService', recruitService);
        recruitService.$inject = ['httpService'];
        ListRecruitCtrl.$inject = ['recruitService'];
        AddRecruitCtrl.$inject = ['util', 'recruitService'];
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
                    }
                }
            );
        }
    }

    function AddRecruitCtrl(util, recruitService) {
        var that = this, editor;
        that.entity = {
            schoolName: '',
            price: '',
            scope: '',
            address: '',
            phone: '',
            introduce: '',
            longitude: '',
            latitude: ''
        };
        that.addressInfo = {};
        that.create = create;
        activate();

        function activate() {
            editor = UM.getEditor('introduceEditor');
            util.autoAddress('txtAddress', '', function (result) {
                that.addressInfo = result;
            });
        }

        function create() {
            that.entity.introduce = editor.getContent();
            that.entity.latitude = that.addressInfo['latitude'];
            that.entity.longitude = that.addressInfo['longitude'];
            recruitService.create(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                    }
                }
            );
        }
    }

    function recruitService(httpService) {
        return {
            create: create,
            get: get,
            deleteEntity: deleteEntity
        };

        function deleteEntity(id) {
            return httpService.callAPI('/admin/recruit/delete.do?id=' + id);
        }

        function get(id) {
            return httpService.callAPI('/recruit/rest/get.do?id=' + id);
        }

        function create(entity) {
            return httpService.callAPI('/recruit/rest/create.do', {
                data: entity
            });
        }
    }
})(jQuery, window, document);