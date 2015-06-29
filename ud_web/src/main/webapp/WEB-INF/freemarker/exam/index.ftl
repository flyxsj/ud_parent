<#import "../lib/page.ftl" as page>
<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
</head>
<body ng-controller="ListExamCtrl as vm">
<div class="container" style="margin-top: 20px;">
    <div class="container">

        <h2 class="page-title">考试</h2>

        <div class="margin-top-1">
            <div class="exam-list" >
                <div class="exam-item hidden" ng-repeat="item in examList" finish-load="vm.firstLoaded()">
                    <label data-examid="{{item.id}}">{{$index + 1}}.</label>
                    <p ng-bind-html="item.question"></p>
                    <div ng-repeat="opt in item.options">
                        <input type="checkbox" value="{{opt.code}}">
                        <span>{{opt.code}}</span>:<span ng-bind-html="opt.content" class="answer-options"></span>
                    </div>
                </div>
            </div>
            <div id="divLoadMore" class="">
                <div class="exam-item hidden" ng-repeat="item in vm.moreList" finish-load="vm.moreLoaded()">
                    <label data-examid="{{item.id}}"></label>
                    <p ng-bind-html="item.question"></p>
                    <div ng-repeat="opt in item.options">
                        <input type="checkbox" value="{{opt.code}}">
                        <span>{{opt.code}}</span>:<span ng-bind-html="opt.content" class="answer-options"></span>
                    </div>
                </div>
            </div>
            <p class="answer hidden">
                正确答案：<label></label>
            </p>
            <div>
                <button class="btn-next hidden" ng-click="vm.nextQuestion()">下一题</button>
                <button class="btn-end" ng-click="vm.endExam()">结束考试并查看分数</button>
            </div>
        </div>
    </div>
</div>
<#include "../lib/footer.ftl">
<script type="text/javascript" src="${contextPath}/resources/lib/angular/angular-sanitize.min.js"></script>
<script src="${contextPath}/resources/js/exam.js?v=${res_version}"></script>
</body>
</html>