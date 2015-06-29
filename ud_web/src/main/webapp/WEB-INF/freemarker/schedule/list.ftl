<#import "../lib/page.ftl" as page>
<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
</head>
<body ng-controller="ListScheduleCtrl as vm">
<div class="container" style="margin-top: 20px;">
    <div class="container">
        <a href="${contextPath}/schedule/index.do" class="btn btn-primary pull-right">
            <span class="glyphicon glyphicon-plus"></span>发布可预约时间
        </a>

        <h2 class="page-title">可预约时间列表</h2>

        <div class="margin-top-1">
            <table class="footable table">
                <thead>
                <tr class="table-header">
               		<th>driverId</th>
               		<th>预约类型</th>
               		<th>日前</th>
               		<th>开始时间</th>
               		<th>结束时间</th>
               		<th>是否长期有效</th>
               		<th>发布时间</th>
               		<th></th>
                </tr>
                </thead>
                <tbody>
                <#if dataList?? && dataList?size &gt; 0>
                	<#list dataList as item>
                        <tr>
                        	<td>${item.driverId!''}</td>
                        	<td>
                                <#if item.type='exercise'>
                                陪练
                                <#else>
                                陪驾
                                </#if>
                            </td>
                        	<td>${item.day?string("yyyy-MM-dd")}</td>
                        	<td>${item.startTime?string("HH:mm")}</td>
                        	<td>${item.endTime?string("HH:mm")}</td>
                        	<td>
                                <#if item.isAlwaysEnable>
                                <span style="color: green;font-weight: bold;">是</span>
                                <#else>
                                否
                                </#if>
                            </td>
                        	<td>${item.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                            <td>
                                <a href="" ng-click="vm.deleteItem(${item.id});">删除</a>
                            </td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
            <#if pageInfo??>
            	<@page.page urlPrefix="${contextPath}/schedule/list.do?p=" urlPostfix="" pageInfo=pageInfo/>
            </#if>
        </div>
    </div>
</div>
<#include "../lib/footer.ftl">
<script src="${contextPath}/resources/js/schedule.js?v=${res_version}"></script>
</body>
</html>