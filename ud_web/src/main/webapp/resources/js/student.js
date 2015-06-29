;
(function ($, window, document) {
    $(document).ready(function () {
        angular.module('StudentModule', ['ngSanitize', 'BaseModule'])
            .controller('AddStudentCtrl', AddStudentCtrl)
            .controller('ListExamCtrl', ListExamCtrl)
            .factory('studentService', studentService)
        ;
        studentService.$inject = ['httpService'];
        ListExamCtrl.$inject = ['studentService'];
        AddStudentCtrl.$inject = ['studentService'];
        angular.bootstrap(document, ['StudentModule']);
    });
    function ListExamCtrl(studentService) {
        var that = this;
        that.showAnswer = showAnswer;
        activate();
        function activate() {
            $('.exam-item').each(function (i, obj) {
                var seq = $(obj).data('seq');
                var text = $(obj).find('div.options-json').text();
                that['options' + seq + 'List'] = JSON.parse(text);
            });
            studentService.requestAward().then(
                function (result) {
                    var code = result['code'];
                    if (code == 'ok') {
                        alert('恭喜，您领取到了每天10U豆学习奖励');
                    } else if (code == 'awarded') {
                        alert('您已经领取过每天10U豆学习奖励');
                    }
                }
            )
        }

        function showAnswer() {
            $('.exam-item').each(function (i, obj) {
                var seq = $(obj).data('seq');
                var answer = $(obj).data('answer');
                var array = answer.split(",");
                $(array).each(function (j, answer) {
                    $(obj).find('input[value="' + answer + '"]').attr('checked', 'checked');
                });
            });
        }
    }

    function AddStudentCtrl(studentService) {
        var that = this;
        var openid = $('input[name="openid"]').val();
        that.register = register;
        that.mobile = '';
        activate();

        function activate() {
            if (openid.length == 0) {
                alert('未能获取到您的微信信息，将无法注册');
            }
        }

        function register() {
            if (openid.length == 0) {
                alert('未能获取到您的微信信息，无法注册。请尝试重新进入本页面');
                return;
            }
            var entity = {
                "openid": openid,
                "mobile": that.mobile
            };
            studentService.register(entity).then(
                function (result) {
                    if (result.code == 'ok') {
                        alert('恭喜，注册成功');
                    } else if (result.code == 'existing') {
                        alert('您已经注册');
                    } else {
                        alert('注册失败');
                    }
                }
            );
        }
    }

    function studentService(httpService) {
        return {
            register: register,
            get: get,
            deleteEntity: deleteEntity,
            requestAward: requestAward
        };

        function deleteEntity(id) {
            return httpService.callAPI('/student/rest/delete.do?id=' + id);
        }

        function get(id) {
            return httpService.callAPI('/student/rest/get.do?id=' + id);
        }

        function register(entity) {
            return httpService.callAPI('/student/rest/register.do', {
                data: entity
            });
        }

        function requestAward() {
            return httpService.callAPI('/student/rest/study_award.do');
        }
    }
})(jQuery, window, document);