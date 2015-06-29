;
(function ($, window, document) {
    var OPTIONS_CODE = 'ABCDEFGHIJK'.split('');
    $(document).ready(function () {
        angular.module('ExamModule', ['BaseModule'])
            .controller('AddExamCtrl', AddExamCtrl)
            .controller('EditExamCtrl', EditExamCtrl)
            .controller('ListExamCtrl', ListExamCtrl)
            .factory('examService', examService);
        examService.$inject = ['httpService'];
        ListExamCtrl.$inject = ['examService'];
        AddExamCtrl.$inject = ['examService'];
        EditExamCtrl.$inject = ['examService', 'util'];
        angular.bootstrap(document, ['ExamModule']);
    });
    function ListExamCtrl(examService) {
        var that = this;
        that.deleteItem = deleteItem;
        function deleteItem(id) {
            if (!confirm('确认要删除该记录？')) {
                return;
            }
            examService.deleteEntity(id).then(
                function (data) {
                    if (data.code === 'ok') {
                        window.location.href = window.contextPath + '/admin/exam/list.do';
                    }
                }
            );
        }
    }

    function AddExamCtrl(examService) {
        var that = this, qEditor, optionEditor;
        that.entity = {
            type: 'course1',
            question: '',
            options: '',
            answer: ''
        };
        that.answers = [];
        that.options = [];
        that.create = create;
        that.addOption = addOption;
        that.removeOption = removeOption;
        activate();
        function activate() {
            qEditor = UM.getEditor('questionEditor');
            optionEditor = UM.getEditor('optionEditor');
        }

        function addOption() {
            var item = {
                'code': OPTIONS_CODE[that.options.length],
                'content': optionEditor.getContent()
            };
            that.options.push(item);
            optionEditor.setContent('<p></p>');
            optionEditor.focus();
        }

        function removeOption(code) {
            var options = [];
            $(that.options).each(function (i, v) {
                if (v['code'] != code) {
                    var item = {
                        'code': OPTIONS_CODE[options.length],
                        'content': v['content']
                    };
                    options.push(item);
                }
            });
            that.options = options;
        }

        function create() {
            $('#answerArea input:checked').each(function (i, v) {
                that.answers.push($(v).val());
            });
            if (that.answers.length == 0) {
                alert('请选择正确答案');
                return;
            }
            that.entity.question = qEditor.getContent();
            that.entity.options = angular.toJson(that.options);
            that.entity.answer = that.answers.join(',');
            examService.create(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                        alert('保存成功');
                        window.location.href = window.contextPath + '/admin/exam/add.do';
                    }
                }
            );
        }
    }

    function EditExamCtrl(examService, util) {
        var that = this, qEditor, optionEditor;
        that.entity = {
            type: 'course1',
            question: '',
            options: '',
            answer: ''
        };
        that.answers = [];
        that.options = [];
        that.addOption = addOption;
        that.removeOption = removeOption;
        that.update = update;
        activate();
        function activate() {
            qEditor = UM.getEditor('questionEditor');
            optionEditor = UM.getEditor('optionEditor');
            var id = util.getUrlParam('id');
            examService.get(id).then(
                function (result) {
                    var data = result.data;
                    that.entity = data;
                    that.options = JSON.parse(data['options']);
                    that.answers = data['answer'].split(',');
                    setCheckedAnswer();
                }
            );
        }

        function setCheckedAnswer() {
            setTimeout(function () {
                $(that.answers).each(function (i, v) {
                    $('input[value="' + v + '"]').attr('checked', '');
                });
            }, 200);
        }

        function addOption() {
            var item = {
                'code': OPTIONS_CODE[that.options.length],
                'content': optionEditor.getContent()
            };
            that.options.push(item);
            optionEditor.setContent('<p></p>');
            optionEditor.focus();
        }

        function removeOption(code) {
            var options = [];
            $(that.options).each(function (i, v) {
                if (v['code'] != code) {
                    var item = {
                        'code': OPTIONS_CODE[options.length],
                        'content': v['content']
                    };
                    options.push(item);
                }
            });
            that.options = options;
            setCheckedAnswer();
        }

        function update() {
            var answers = [];
            $('#answerArea input:checked').each(function (i, v) {
                answers.push($(v).val());
            });
            if (that.answers.length == 0) {
                alert('请选择正确答案');
                return;
            }
            delete that.entity.createTime;
            that.entity.question = qEditor.getContent();
            that.entity.options = angular.toJson(that.options);
            that.entity.answer = answers.join(',');
            examService.update(angular.copy(that.entity)).then(
                function (data) {
                    if (data.code == 'ok') {
                        alert('保存成功');
                        window.location.href = window.contextPath + '/admin/exam/list.do';
                    }
                }
            );
        }
    }

    function examService(httpService) {
        return {
            deleteEntity: deleteEntity,
            create: create,
            update: update,
            get: get
        };

        function get(id) {
            return httpService.callAPI('/admin/exam/get.do?id=' + id);
        }

        function deleteEntity(id) {
            return httpService.callAPI('/admin/exam/delete.do?id=' + id);
        }

        function create(entity) {
            return httpService.callAPI('/admin/exam/create.do', {
                data: entity
            });
        }

        function update(entity) {
            return httpService.callAPI('/admin/exam/update.do', {
                data: entity
            });
        }
    }
})(jQuery, window, document);