;
(function ($, window, document) {
    var OPTIONS_CODE = 'ABCDEFGHIJK'.split('');
    $(document).ready(function () {
        angular.module('VideoModule', ['BaseModule'])
            .controller('AddVideoCtrl', AddVideoCtrl)
            .controller('EditVideoCtrl', EditVideoCtrl)
            .controller('ListVideoCtrl', ListVideoCtrl)
            .factory('videoService', videoService);
        videoService.$inject = ['httpService'];
        ListVideoCtrl.$inject = ['videoService'];
        AddVideoCtrl.$inject = ['videoService'];
        EditVideoCtrl.$inject = ['videoService', 'util'];
        angular.bootstrap(document, ['VideoModule']);
    });
    function ListVideoCtrl(videoService) {
        var that = this;
        that.deleteItem = deleteItem;
        function deleteItem(id) {
            if (!confirm('确认要删除该记录？')) {
                return;
            }
            videoService.deleteEntity(id).then(
                function (data) {
                    if (data.code === 'ok') {
                        window.location.href = window.contextPath + '/admin/video/list.do';
                    }
                }
            );
        }
    }

    function AddVideoCtrl(videoService) {
        var that = this, editor;
        that.entity = {
            type: 'course',
            title: '',
            content: ''
        };
        that.create = create;
        activate();
        function activate() {
            editor = UM.getEditor('editor');
        }

        function create() {
            that.entity.content = editor.getContent();
            videoService.create(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                        alert('保存成功');
                        window.location.href = window.contextPath + '/admin/video/add.do';
                    }
                }
            );
        }
    }

    function EditVideoCtrl(videoService, util) {
        var that = this, editor;
        that.entity = {
            type: 'course',
            title: '',
            content: ''
        };
        that.update = update;
        activate();
        function activate() {
            editor = UM.getEditor('editor');
            var id = util.getUrlParam('id');
            videoService.get(id).then(
                function (result) {
                    that.entity = result.data;
                }
            );
        }

        function update() {
            delete that.entity.createTime;
            that.entity.content = editor.getContent();
            videoService.update(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                        alert('保存成功');
                        window.location.href = window.contextPath + '/admin/video/list.do';
                    }
                }
            );
        }
    }

    function videoService(httpService) {
        return {
            deleteEntity: deleteEntity,
            create: create,
            update: update,
            get: get
        };

        function get(id) {
            return httpService.callAPI('/admin/video/get.do?id=' + id);
        }

        function deleteEntity(id) {
            return httpService.callAPI('/admin/video/delete.do?id=' + id);
        }

        function create(entity) {
            return httpService.callAPI('/admin/video/create.do', {
                data: entity
            });
        }

        function update(entity) {
            return httpService.callAPI('/admin/video/update.do', {
                data: entity
            });
        }
    }
})(jQuery, window, document);