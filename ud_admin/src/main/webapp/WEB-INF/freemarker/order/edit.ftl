<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
</head>
<body class="menu-page" ng-controller="EditOrderCtrl as vm">
<div id="main-box">
    <#include "../lib/top_bar.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-md-push-2">
                    <h2 class="page-title">编辑Order</h2>

                    <form class="form-horizontal" ng-submit="vm.update()">
                        <div class="panel panel-default">
                            <div class="panel-heading">Order信息</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">id</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.id!''}" ng-model="vm.entity.id">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">studentId</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.studentId!''}" ng-model="vm.entity.studentId">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">driverId</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.driverId!''}" ng-model="vm.entity.driverId">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">type</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.type!''}" ng-model="vm.entity.type">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">course</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.course!''}" ng-model="vm.entity.course">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">address</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.address!''}" ng-model="vm.entity.address">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">longitude</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.longitude!''}" ng-model="vm.entity.longitude">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">latitude</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.latitude!''}" ng-model="vm.entity.latitude">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">reserveDay</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.reserveDay!''}" ng-model="vm.entity.reserveDay">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">reserveStartTime</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.reserveStartTime!''}" ng-model="vm.entity.reserveStartTime">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">reserveEndTime</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.reserveEndTime!''}" ng-model="vm.entity.reserveEndTime">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">status</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.status!''}" ng-model="vm.entity.status">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">serviceCode</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.serviceCode!''}" ng-model="vm.entity.serviceCode">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">trainStartTime</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.trainStartTime!''}" ng-model="vm.entity.trainStartTime">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">trainEndTime</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.trainEndTime!''}" ng-model="vm.entity.trainEndTime">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">createTime</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.createTime!''}" ng-model="vm.entity.createTime">
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
<script src="${contextPath}/resources/js/order.js?v=${res_version}"></script>
</body>
</html>