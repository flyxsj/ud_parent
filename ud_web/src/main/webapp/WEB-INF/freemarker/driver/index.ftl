<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
</head>
<body ng-controller="AddDriverCtrl as vm">
<div class="container" style="margin-top: 20px;">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-push-2">
            <form class="form-horizontal" ng-submit="vm.create()">
                <div class="panel panel-default">
                    <div class="panel-heading">Driver信息</div>
                    <div class="panel-body">
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

                            <div class="col-sm-10" ng-model="vm.entity.hasShop">
                                <select class="form-control">
                                    <option value="true">有</option>
                                    <option value="false">无</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">address</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" ng-model="vm.entity.address">
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
                        <div>
                            <button class="btn btn-success pull-right" type="submit">Save</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<#include "../lib/footer.ftl">
<script src="${contextPath}/resources/js/driver.js?v=${res_version}"></script>
</body>
</html>