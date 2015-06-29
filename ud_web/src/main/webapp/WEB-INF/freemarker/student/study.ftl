<#import "../lib/page.ftl" as page>
<!DOCTYPE html>
<html>
<head>
    <#include "../lib/header.ftl">
</head>
<body ng-controller="ListExamCtrl as vm">
<div class="container" style="margin-top: 20px;">
    <div class="container">
        <h2 class="page-title">驾考学习</h2>
        <div>
            <select>
                <option value="course1">科目一</option>
                <option value="course4">科目四</option>
                <option value="safety">文明安全驾驶</option>
            </select>
        </div>
        <#if dataList?? && dataList?size &gt; 0>
        <#list dataList as item>
        <#assign seq = (pageInfo.pageIndex - 1) * pageInfo.pageSize + item_index + 1 />
        <div class="exam-item" style="margin-bottom: 30px;" data-seq="${seq}" data-answer="${item.answer}">
            <div class="question">
                <span>${seq}.</span>
                <span>${item.question}</span>
            </div>
            <div class="options-json hidden">
                ${item.options}
            </div>
            <div class="options" ng-repeat="opt in vm.options${seq}List">
                <span><input type="checkbox" value="{{opt.code}}">&nbsp;&nbsp;{{opt.code}}：</span>
                <span ng-bind-html="opt.content"></span>
            </div>
        </div>
        </#list>
        </#if>
        <p>
            <button type="button" ng-click="vm.showAnswer()">显示答案</button>
        </p>
        <div class="margin-top-1">
            <#if pageInfo??>
                <@page.page urlPrefix="${contextPath}/student/study.do?p=" urlPostfix="" pageInfo=pageInfo/>
            </#if>
        </div>
    </div>
</div>
<#include "../lib/footer.ftl">
<script type="text/javascript" src="${contextPath}/resources/lib/angular/angular-sanitize.min.js"></script>
<script src="${contextPath}/resources/js/student.js?v=${res_version}"></script>
</body>
</html>