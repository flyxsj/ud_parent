;
(function ($, window, document) {
    $(document).ready(function () {
        angular.module('LuckModule', ['BaseModule'])
            .controller('AddLuckCtrl', AddLuckCtrl)
            .factory('luckService', luckService);
        luckService.$inject = ['httpService'];
        AddLuckCtrl.$inject = ['luckService'];
        angular.bootstrap(document, ['LuckModule']);
    });

    function AddLuckCtrl(luckService) {
        var that = this;
        that.getLuckBean = getLuckBean;
        that.share = share;
        that.amount = 0;
        that.luckId = 0;
        activate();
        function activate() {
        }

        function share() {
            luckService.share(that.luckId).then(
                function (result) {
                    if (result.code == 'ok') {
                        alert('分享成功，U豆已入账');
                    }
                }
            );
        }

        function getLuckBean() {
            //TODO may need to get openid at back end
            luckService.getLuckBean('aaa').then(
                function (result) {
                    var code = result.code;
                    if (code == 'ok') {
                        var data = result['data'];
                        that.amount = data['bean'];
                        that.luckId = data['id'];
                    } else if (code == 'has_got') {
                        alert('你今天已抽奖');
                    } else if (code == 'not_student') {
                        alert('你还不是学员，不能抽奖');
                    } else {
                        alert('抽奖失败');
                    }
                }
            );
        }
    }

    function luckService(httpService) {
        return {
            share: share,
            getLuckBean: getLuckBean
        };

        function getLuckBean(openid) {
            return httpService.callAPI('/luck/rest/getLuckBean.do?openid=' + openid);
        }

        function share(id) {
            return httpService.callAPI('/luck/rest/share.do?luckId=' + id);
        }
    }
})(jQuery, window, document);