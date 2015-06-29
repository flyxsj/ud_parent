<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
</head>
<body class="menu-page" ng-controller="EditDriverCtrl as vm">
<div id="main-box">
    <#include "../lib/top_bar.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-md-push-2">
                    <h2 class="page-title">编辑教练</h2>

                    <form class="form-horizontal" ng-submit="vm.update()">
                        <div class="panel panel-default">
                            <div class="panel-heading">教练信息</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">id</label>

                                    <div class="col-sm-10">
                                        <input type="text" disabled class="form-control" value="${entity.id}" ng-model="vm.entity.id">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">openid</label>

                                    <div class="col-sm-10">
                                        <input type="text" disabled class="form-control" value="${entity.openid!''}" ng-model="vm.entity.openid">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">驾照</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.driverLicense!''}" ng-model="vm.entity.driverLicense">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">身份证</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.idNumber!''}" ng-model="vm.entity.idNumber">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">是否有店面</label>

                                    <div class="col-sm-10">
                                        <select class="form-control">
                                            <option value="true" <#if entity.hasShop==true>selected="selected"</#if> >有</option>
                                            <option value="false" <#if entity.hasShop==false>selected="selected"</#if> >无</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">地址</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.address!''}" ng-model="vm.entity.address">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">经度</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.longitude!''}" ng-model="vm.entity.longitude">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">纬度</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.latitude!''}" ng-model="vm.entity.latitude">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">驾龄</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.driveAge!''}" ng-model="vm.entity.driveAge">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">手机</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.mobile!''}" ng-model="vm.entity.mobile">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">分数</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.score!'0'}" ng-model="vm.entity.score">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">U币</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.ub!'0'}" ng-model="vm.entity.ub">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">状态</label>

                                    <div class="col-sm-10">
                                        <select class="form-control" ng-model="vm.entity.status">
                                            <option value="I">待审核</option>
                                            <option value="D">已注销</option>
                                            <option value="A">已审核</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">加入时间</label>

                                    <div class="col-sm-10">
                                        <input type="text" disabled class="form-control" value="${entity.createTime?string("yyyy-MM-dd HH:mm:ss")}">
                                    </div>
                                </div>
                                <#if entity.status != 'D'>
                                <div>
                                    <button class="btn btn-success pull-right" type="submit">保存</button>
                                    <#if entity.status = 'I'>
                                        <button class="btn btn-success pull-right" ng-click="vm.pass()" style="margin-right: 20px;" type="button">审核通过</button>
                                    </#if>
                                </div>
                                </#if>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "../lib/footer.ftl">
<script src="${contextPath}/resources/js/driver.js?v=${res_version}"></script>
</body>
</html>