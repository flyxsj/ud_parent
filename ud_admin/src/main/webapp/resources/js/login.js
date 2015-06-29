;
(function ($, window, document) {
    $(document).ready(function () {
        angular.module('LoginModule', ['BaseModule'])
            .controller('LoginCtrl', LoginCtrl);
        LoginCtrl.$inject = ['httpService'];
        angular.bootstrap(document, ['LoginModule']);
    });
    function LoginCtrl(httpService) {
        var that = this;
        that.entity = {
            username: 'xie',
            password: '123456'
        };
        that.login = login;
        function login() {
            httpService.callAPI('/admin/user/login.do', {
                data: {
                    username: that.entity.username,
                    password: that.entity.password
                }
            }).then(
                function (result) {
                    console.log(result);
                    if (result.code === 'ok') {
                        window.location.href = contextPath + '/admin/user/main.do';
                    }
                }
            );
        }
    }
})(jQuery, window, document);