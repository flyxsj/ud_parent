<#import "../lib/page.ftl" as page>
<!DOCTYPE html>
<html>
<#include "../lib/header.ftl">
<body class="menu-page" ng-controller="ListExamCtrl as vm">
<div id="main-box">
    <#include "../lib/top_bar.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="container">
                <a href="${contextPath}/admin/video/add.do" class="btn btn-primary pull-right">
                    <span class="glyphicon glyphicon-plus"></span>添加视频
                </a>

                <h2 class="page-title">视频管理</h2>

                <div class="margin-top-1">
                    <table class="footable table">
                        <thead>
                        <tr class="table-header">
                       		<th>id</th>
                       		<th>类型</th>
                            <th>标题</th>
                       		<th>创建时间</th>
                       		<th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <#if dataList?? && dataList?size &gt; 0>
                        	<#list dataList as item>
                                <tr>
                                	<td>${item.id}</td>
                                	<td>
                                        <#if item.type='course'>
                                        学车视频
                                        <#elseif item.type='safety'>
                                        文明驾车视频
                                        </#if>
                                    </td>
                                	<td>
                                        <div style="width: 200px;overflow: hidden;">
                                            ${item.title}
                                        </div>
                                    </td>
                                	<td>${item.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                                    <td>
                                        <div class="dropdown">
                                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="true">
                                                操作 <span class="caret"></span></button>
                                            <ul class="dropdown-menu dropdown-menu-left" role="menu" aria-labelledby="dropdownMenu1">
                                                <li role="presentation">
                                                    <a href="${contextPath}/admin/video/edit.do?id=${item.id}">编辑</a>
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
                    	<@page.page urlPrefix="${contextPath}/admin/video/list.do?p=" urlPostfix="" pageInfo=pageInfo/>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "../lib/footer.ftl">
<script src="${contextPath}/resources/js/video.js?v=${res_version}"></script>
</body>
</html>