<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
</head>
<body class="menu-page" ng-controller="AddDriverCtrl as vm">
<div id="main-box">
    <#include "../lib/top_bar.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-md-push-2">
                    <h2 class="page-title">添加Driver</h2>

                    <form class="form-horizontal" ng-submit="vm.create()">
                        <div class="panel panel-default">
                            <div class="panel-heading">Driver信息</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">id</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.id">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">openid</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.openid">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">driverLicense</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.driverLicense">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">idNumber</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.idNumber">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">hasShop</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.hasShop">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">address</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.address">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">longitude</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.longitude">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">latitude</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.latitude">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">driveAge</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.driveAge">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">mobile</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.mobile">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">score</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.score">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">ub</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.ub">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">status</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.status">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">createTime</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.createTime">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">updateTime</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.updateTime">
                                    </div>
                                </div>
                                <div>
                                    <button class="btn btn-success pull-right" type="submit">Save</button>
                                </div>
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