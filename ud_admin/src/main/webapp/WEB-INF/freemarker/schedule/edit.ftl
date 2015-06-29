<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
</head>
<body class="menu-page" ng-controller="EditScheduleCtrl as vm">
<div id="main-box">
    <#include "../lib/top_bar.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-md-push-2">
                    <h2 class="page-title">编辑Schedule</h2>

                    <form class="form-horizontal" ng-submit="vm.update()">
                        <div class="panel panel-default">
                            <div class="panel-heading">Schedule信息</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">id</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.id!''}" ng-model="vm.entity.id">
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
                                    <label class="col-sm-2 control-label">day</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.day!''}" ng-model="vm.entity.day">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">startTime</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.startTime!''}" ng-model="vm.entity.startTime">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">endTime</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.endTime!''}" ng-model="vm.entity.endTime">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">isAlwaysEnable</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.isAlwaysEnable!''}" ng-model="vm.entity.isAlwaysEnable">
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
<script src="${contextPath}/resources/js/schedule.js?v=${res_version}"></script>
</body>
</html>