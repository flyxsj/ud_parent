<#import "../lib/page.ftl" as page>
<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
</head>
<body ng-controller="ListOrderCtrl as vm">
<div class="container" style="margin-top: 20px;">
    <div class="container">
        <h2 class="page-title">订单列表</h2>

        <div class="margin-top-1">
            <table class="footable table">
                <thead>
                <tr class="table-header">
               		<th>id</th>
               		<th>订单编号</th>
               		<th>type</th>
               		<th>course</th>
               		<th>reserveDay</th>
               		<th>reserveStartTime</th>
               		<th>reserveEndTime</th>
               		<th>status</th>
               		<th>createTime</th>
               		<th></th>
                </tr>
                </thead>
                <tbody>
                <#if dataList?? && dataList?size &gt; 0>
                	<#list dataList as item>
                        <tr>
                        	<td>${item.id!''}</td>
                        	<td>${item.orderNumber!''}</td>
                        	<td>${item.type!''}</td>
                        	<td>${item.course!''}</td>
                        	<td>${item.reserveDay?string("yyyy-MM-dd")}</td>
                        	<td>${item.reserveStartTime?string("HH:mm")}</td>
                        	<td>${item.reserveEndTime?string("HH:mm")}</td>
                        	<td>${item.status!''}</td>
                        	<td>${item.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                            <td>
                                <div class="dropdown">
                                    <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="true">
                                        操作 <span class="caret"></span></button>
                                    <ul class="dropdown-menu dropdown-menu-left" role="menu" aria-labelledby="dropdownMenu1">
                                        <#if status='reserved'>
                                        <li role="presentation">
                                            <a href="" ng-click="vm.accept('${item.orderNumber}')">
                                                接受订单
                                            </a>
                                        </li>
                                        </#if>
                                        <#if status='accepted' && type='student'>
                                        <li role="presentation">
                                            <a href="${contextPath}/order/accept.do?orderNumber=${item.orderNumber}">
                                                支付
                                            </a>
                                        </li>
                                        </#if>
                                        <#if status='paid' && type='driver'>
                                            <li role="presentation">
                                                <a href="" ng-click="vm.showStart('${item.orderNumber}')">输入预约码</a>
                                            </li>
                                        </#if>
                                        <#if status='start'>
                                        <li role="presentation">
                                            <a href="" ng-click="vm.end('${item.orderNumber}')">
                                                结束
                                            </a>
                                        </li>
                                        </#if>
                                        <#if status='end' && type='student'>
                                        <li role="presentation">
                                            <a href="${contextPath}/order/comment.do?orderNumber=${item.orderNumber}">
                                                评价
                                            </a>
                                        </li>
                                        </#if>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
            <#if pageInfo??>
            	<@page.page urlPrefix="${contextPath}/order/list.do?openid=${openid!''}&type=${type}&status=${status}&p="
                urlPostfix="" pageInfo=pageInfo/>
            </#if>
        </div>
        <div id="optArea" style="display: none;margin: 30px 0;">
            <input type="hidden" name="orderNumber">
            <input type="text" name="serviceCode">
            <button type="button" ng-click="vm.start()">开始计时</button>
        </div>
    </div>
</div>
<#include "../lib/footer.ftl">
<script src="${contextPath}/resources/js/order.js?v=${res_version}"></script>
</body>
</html>