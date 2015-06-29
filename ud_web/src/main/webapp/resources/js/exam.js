;
(function ($, window, document) {
    $(document).ready(function () {
        angular.module('ExamModule', ['ngSanitize', 'BaseModule'])
            .controller('ListExamCtrl', ListExamCtrl)
            .factory('examService', examService)
            .directive('finishLoad', function () {
                return function (scope, element, attrs) {
                    if (scope.$last) {
                        scope.$eval(attrs.finishLoad);
                    }
                };
            })
        ;
        examService.$inject = ['httpService'];
        ListExamCtrl.$inject = ['examService', '$sce', '$scope'];
        angular.bootstrap(document, ['ExamModule']);
    });
    function ListExamCtrl(examService, $sce, $scope) {
        var that = this;
        var pageIndex = 1;
        var itemIndex = 0;
        $scope.examList = [];
        that.moreList = [];
        that.noMore = false;
        that.firstLoaded = firstLoaded;
        that.trustHTML = trustHTML;
        that.moreLoaded = function () {
            //TODO need to append after angular rendering completed
            setTimeout(function () {
                $('.exam-list').append($('#divLoadMore').html());
                $('.exam-list .exam-item').each(function (i, obj) {
                    $(obj).find('label').text(i + 1 + '.');
                });
            }, 200);
        };
        that.nextQuestion = function () {
            var current = $('.exam-list .exam-item').eq(itemIndex);
            var answers = [];
            $('input:checkbox', current).each(function () {
                if ($(this).is(":checked")) {
                    answers.push($(this).attr("value"));
                }
            });
            if (answers.length == 0) {
                alert('请选择答案');
                return;
            }
            var examId = current.find('label').data('examid');
            examService.submitAnswer(examId, answers.join(',')).then(
                function (result) {
                    if (result['code'] == 'ok') {
                        var data = result['data'];
                        if (data && data['answer']) {
                            $('.answer label').text(data['answer']);
                            $('.answer').removeClass('hidden');
                            setTimeout(function () {
                                current.addClass('hidden');
                                $('.answer').addClass('hidden');
                                if (current.nextAll().length < 5) {
                                    that.loadMore();
                                }
                                itemIndex++;
                                $('.exam-list .exam-item').eq(itemIndex).removeClass('hidden');
                                if ($('.exam-list .exam-item').length == (itemIndex + 1)) {
                                    $('.btn-next').addClass('hidden');
                                }
                            }, 2000);
                        }
                    } else {
                        alert('提交失败');
                    }
                }
            );
        };
        that.loadMore = function () {
            if (that.noMore) {
                return;
            }
            examService.getSimulateExamList(pageIndex).then(
                function (data) {
                    if (data.code == 'ok') {
                        var items = data['data'];
                        if (items.length > 0) {
                            $(items).each(function (i, obj) {
                                obj.options = JSON.parse(obj.options);
                            });
                            that.moreList = items;
                            pageIndex++;
                        } else {
                            that.noMore = true;
                        }
                    }
                }
            );
        };
        that.endExam = function () {
            examService.endExam().then(
                function (result) {
                    if (result['code'] == 'ok') {
                        alert('你的分数是：' + result['data']['score']);
                    } else {
                        alert('提交失败');
                    }
                }
            );
        };
        activate();
        function firstLoaded() {
            $('.exam-list .exam-item').eq(0).removeClass('hidden');
            if ($('.exam-list .exam-item').length > 1) {
                $('.btn-next').removeClass('hidden');
            }
        }

        function trustHTML(val) {
            return $sce.trustAsHtml(val);
        }

        function activate() {
            examService.getSimulateExamList(pageIndex).then(
                function (data) {
                    if (data.code == 'ok') {
                        var items = data['data'];
                        $(items).each(function (i, obj) {
                            obj.options = JSON.parse(obj.options);
                        });
                        $scope.examList = items;
                        pageIndex++;
                    }
                }
            );
        }
    }

    function examService(httpService) {
        return {
            create: create,
            get: get,
            deleteEntity: deleteEntity,
            getSimulateExamList: getSimulateExamList,
            submitAnswer: submitAnswer,
            endExam: endExam
        };

        function deleteEntity(id) {
            return httpService.callAPI('/admin/exam/delete.do?id=' + id);
        }

        function get(id) {
            return httpService.callAPI('/exam/rest/get.do?id=' + id);
        }

        function submitAnswer(id, answer) {
            return httpService.callAPI('/exam/rest/submitAnswer.do?id=' + id + '&answer=' + answer);
        }

        function endExam() {
            return httpService.callAPI('/exam/rest/endExam.do');
        }

        function getSimulateExamList(pageIndex) {
            return httpService.callAPI('/exam/rest/getSimulateExamList.do?pageIndex=' + pageIndex);
        }

        function create(entity) {
            return httpService.callAPI('/exam/rest/create.do', {
                data: entity
            });
        }
    }
})(jQuery, window, document);