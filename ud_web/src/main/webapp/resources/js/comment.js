;
(function ($, window, document) {
    $(document).ready(function () {
        angular.module('CommentModule', ['BaseModule'])
            .controller('AddCommentCtrl', AddCommentCtrl)
            .controller('ListCommentCtrl', ListCommentCtrl)
            .factory('commentService', commentService);
        commentService.$inject = ['httpService'];
        ListCommentCtrl.$inject = ['commentService'];
        AddCommentCtrl.$inject = ['commentService'];
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
                    }
                }
            );
        }
    }
    
    function AddCommentCtrl(commentService) {
        var that = this;
        that.entity = {
    		orderId: '',
    		level: '',
    		description: '',
    		createTime: ''
        };
        that.create = create;
        activate();
        
        function activate(){
        	
        }
        function create() {
            commentService.create(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                    }
                }
            );
        }
    }

    function commentService(httpService) {
        return {
            create: create,
            get: get,
            deleteEntity: deleteEntity
        };
        
        function deleteEntity(id) {
            return httpService.callAPI('/admin/comment/delete.do?id=' + id);
        }

        function get(id) {
            return httpService.callAPI('/comment/rest/get.do?id=' + id);
        }

        function create(entity) {
            return httpService.callAPI('/comment/rest/create.do', {
                data: entity
            });
        }
    }
})(jQuery, window, document);