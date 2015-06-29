<#import "../lib/page.ftl" as page>
<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
</head>
<body ng-controller="ListRecruitCtrl as vm">
<div class="container" style="margin-top: 20px;">
    <div class="container">
        <a href="${contextPath}/recruit/add.do" class="btn btn-primary pull-right">
            <span class="glyphicon glyphicon-plus"></span>添加Recruit
        </a>

        <h2 class="page-title">Recruit控制台</h2>

        <div class="margin-top-1">
            <table class="footable table">
                <thead>
                <tr class="table-header">
               		<th>id</th>
               		<th>schoolName</th>
               		<th>price</th>
               		<th>scope</th>
               		<th>address</th>
               		<th>phone</th>
               		<th>introduce</th>
               		<th>status</th>
               		<th>createTime</th>
               		<th>updateTime</th>
               		<th></th>
                </tr>
                </thead>
                <tbody>
                <#if dataList?? && dataList?size &gt; 0>
                	<#list dataList as item>
                        <tr>
                        	<td>${item.id!''}</td>
                        	<td>${item.schoolName!''}</td>
                        	<td>${item.price!''}</td>
                        	<td>${item.scope!''}</td>
                        	<td>${item.address!''}</td>
                        	<td>${item.phone!''}</td>
                        	<td>${item.introduce!''}</td>
                        	<td>${item.status!''}</td>
                        	<td>${item.createTime!''}</td>
                        	<td>${item.updateTime!''}</td>
                            <td>
                                <div class="dropdown">
                                    <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="true">
                                        操作 <span class="caret"></span></button>
                                    <ul class="dropdown-menu dropdown-menu-left" role="menu" aria-labelledby="dropdownMenu1">
                                        <li role="presentation">
                                            <a href="${contextPath}/recruit/edit.do?id=${item.id}">编辑</a>
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
            	<@page.page urlPrefix="${contextPath}/recruit/list.do?p=" urlPostfix="" pageInfo=pageInfo/>
            </#if>
        </div>
    </div>
</div>
<#include "../lib/footer.ftl">
<script src="${contextPath}/resources/js/recruit.js?v=${res_version}"></script>
</body>
</html>