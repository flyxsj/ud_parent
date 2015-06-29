<#import "../lib/page.ftl" as page>
<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
</head>
<body class="menu-page" ng-controller="ListDriverCtrl as vm">
<div id="main-box">
    <#include "../lib/top_bar.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="container">
                <h2 class="page-title">教练列表</h2>

                <div class="margin-top-1">
                    <table class="footable table">
                        <thead>
                        <tr class="table-header">
                       		<th>id</th>
                       		<th>驾照</th>
                       		<th>是否有店面</th>
                       		<th>地址</th>
                       		<th>驾龄</th>
                       		<th>手机</th>
                       		<th>状态</th>
                       		<th>加入时间</th>
                       		<th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <#if dataList?? && dataList?size &gt; 0>
                        	<#list dataList as item>
                                <tr>
                                	<td>${item.id}</td>
                                	<td>${item.driverLicense}</td>
                                	<td>
                                        <#if item.hasShop??>
                                        有
                                        <#else>
                                        无
                                        </#if>
                                    </td>
                                	<td title="${item.address!''}">
                                        <#assign address="${item.address!''}"/>
                                        <#if address?length &gt; 10>
                                        ${address?substring(0,10)}
                                        <#else>
                                        ${address}
                                        </#if>
                                    </td>
                                	<td>${item.driveAge!''}</td>
                                	<td>${item.mobile!''}</td>
                                	<td>
                                        <#if item.status='I'>
                                        待审核
                                        <#elseif item.status='D'>
                                        已注销
                                        <#elseif item.status='A'>
                                        已审核
                                        </#if>
                                    </td>
                                	<td>${item.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                                    <td>
                                        <div class="dropdown">
                                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="true">
                                                操作 <span class="caret"></span></button>
                                            <ul class="dropdown-menu dropdown-menu-left" role="menu" aria-labelledby="dropdownMenu1">
                                                <li role="presentation">
                                                    <a href="${contextPath}/admin/driver/edit.do?id=${item.id}">编辑</a>
                                                </li>
                                                <#if item.status!='D'>
                                                <li role="presentation">
                                                    <a href="" ng-click="vm.disable(${item.id});">注销</a>
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
                    	<@page.page urlPrefix="${contextPath}/admin/driver/list.do?p=" urlPostfix="" pageInfo=pageInfo/>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "../lib/footer.ftl">
<script src="${contextPath}/resources/js/driver.js?v=${res_version}"></script>
</body>
</html>