<#import "../lib/page.ftl" as page>
<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
</head>
<body ng-controller="ListStudentCtrl as vm">
<div class="container" style="margin-top: 20px;">
    <div class="container">
        <a href="${contextPath}/student/add.do" class="btn btn-primary pull-right">
            <span class="glyphicon glyphicon-plus"></span>添加Student
        </a>

        <h2 class="page-title">Student控制台</h2>

        <div class="margin-top-1">
            <table class="footable table">
                <thead>
                <tr class="table-header">
               		<th>id</th>
               		<th>openid</th>
               		<th>mobile</th>
               		<th>bean</th>
               		<th>ub</th>
               		<th>createTime</th>
               		<th></th>
                </tr>
                </thead>
                <tbody>
                <#if dataList?? && dataList?size &gt; 0>
                	<#list dataList as item>
                        <tr>
                        	<td>${item.id!''}</td>
                        	<td>${item.openid!''}</td>
                        	<td>${item.mobile!''}</td>
                        	<td>${item.bean!''}</td>
                        	<td>${item.ub!''}</td>
                        	<td>${item.createTime!''}</td>
                            <td>
                                <div class="dropdown">
                                    <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="true">
                                        操作 <span class="caret"></span></button>
                                    <ul class="dropdown-menu dropdown-menu-left" role="menu" aria-labelledby="dropdownMenu1">
                                        <li role="presentation">
                                            <a href="${contextPath}/student/edit.do?id=${item.id}">编辑</a>
                                        </li>
                                        <li role="presentation">
                                            <a href="" ng-click="vm.deleteItem(${item.id});">删除</a>
                                        </li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
            <#if pageInfo??>
            	<@page.page urlPrefix="${contextPath}/student/list.do?p=" urlPostfix="" pageInfo=pageInfo/>
            </#if>
        </div>
    </div>
</div>
<#include "../lib/footer.ftl">
<script src="${contextPath}/resources/js/student.js?v=${res_version}"></script>
</body>
</html>