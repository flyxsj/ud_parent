;
(function ($, window, document) {
    $(document).ready(function () {
        angular.module('CommentModule', ['BaseModule'])
            .controller('AddCommentCtrl', AddCommentCtrl)
            .controller('EditCommentCtrl', EditCommentCtrl)
            .controller('ListCommentCtrl', ListCommentCtrl)
            .factory('commentService', commentService);
        commentService.$inject = ['httpService'];
        ListCommentCtrl.$inject = ['commentService'];
        AddCommentCtrl.$inject = ['commentService'];
        EditCommentCtrl.$inject = ['commentService', 'util'];
        angular.bootstrap(document, ['CommentModule']);
    });
    function ListCommentCtrl(commentService) {
        var that = this;
        that.deleteItem = deleteItem;
        function deleteItem(id) {
            if (!confirm('确认要删除该记录？')) {
                return;
            }
            commentService.deleteEntity(id).then(
                function (data) {
                    if (data.code === 'ok') {
                        window.location.href = window.contextPath + '/admin/comment/list.do';
                    }
                }
            );
        }
    }

    function AddCommentCtrl(commentService) {
        var that = this;
        that.entity = {
    		orderId: '',
    		score: '',
    		description: '',
    		createTime: ''
        };
        that.create = create;
        function create() {
            commentService.create(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                        window.location.href = window.contextPath + '/admin/comment/list.do';
                    }
                }
            );
        }
    }

    function EditCommentCtrl(commentService, util) {
        var that = this;
        that.entity = {
    		orderId: '',
    		score: '',
    		description: '',
    		createTime: ''
        };
        that.update = update;
        activate();
        function activate() {
            var id = util.getUrlParam('id');
            commentService.get(id).then(
                function (result) {
                    that.entity = result.data;
                }
            );
        }

        function update() {
            commentService.update(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                        window.location.href = window.contextPath + '/admin/comment/list.do';
                    }
                }
            );
        }
    }

    function commentService(httpService) {
        return {
            deleteEntity: deleteEntity,
            create: create,
            update: update,
            get: get
        };

        function get(id) {
            return httpService.callAPI('/admin/comment/get.do?id=' + id);
        }

        function deleteEntity(id) {
            return httpService.callAPI('/admin/comment/delete.do?id=' + id);
        }

        function create(entity) {
            return httpService.callAPI('/admin/comment/create.do', {
                data: entity
            });
        }

        function update(entity) {
            return httpService.callAPI('/admin/comment/update.do', {
                data: entity
            });
        }
    }
})(jQuery, window, document);