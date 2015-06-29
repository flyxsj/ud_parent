<#import "../lib/page.ftl" as page>
<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
</head>
<body class="menu-page" ng-controller="ListStudentCtrl as vm">
<div id="main-box">
    <#include "../lib/top_bar.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="container">
                <h2 class="page-title">学员列表</h2>

                <div class="margin-top-1">
                    <table class="footable table">
                        <thead>
                        <tr class="table-header">
                       		<th>id</th>
                       		<th>openid</th>
                       		<th>手机</th>
                       		<th>U豆</th>
                       		<th>U币</th>
                       		<th>加入时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#if dataList?? && dataList?size &gt; 0>
                        	<#list dataList as item>
                                <tr>
                                	<td>${item.id!''}</td>
                                	<td>${item.openid!''}</td>
                                	<td>${item.mobile!''}</td>
                                	<td>${item.bean!'0'}</td>
                                	<td>${item.ub!'0'}</td>
                                	<td>${item.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                                </tr>
                            </#list>
                        </#if>
                        </tbody>
                    </table>
                    <#if pageInfo??>
                    	<@page.page urlPrefix="${contextPath}/admin/student/list.do?p=" urlPostfix="" pageInfo=pageInfo/>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "../lib/footer.ftl">
<script src="${contextPath}/resources/js/student.js?v=${res_version}"></script>
</body>
</html>