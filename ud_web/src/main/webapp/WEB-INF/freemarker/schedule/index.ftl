<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/jquery-ui.min.css">
</head>
<body ng-controller="AddScheduleCtrl as vm">
<div class="container" style="margin-top: 20px;">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-push-2">
            <h2 class="page-title">发布可预约时间</h2>

            <form class="form-horizontal" ng-submit="vm.create()">
                <div class="panel panel-default">
                    <div class="panel-heading">预约信息</div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">driverId</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" disabled ng-model="vm.entity.driverId">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">type</label>

                            <div class="col-sm-10">
                                <select class="form-control" ng-model="vm.entity.type">
                                    <option value="exercise">陪练</option>
                                    <option value="drive">陪驾</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">day</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" maxlength="10" id="txtDay"
                                       ng-model="vm.entity.day">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">startTime</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" ng-model="vm.entity.startTime">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">endTime</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" ng-model="vm.entity.endTime">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否长期有效</label>

                            <div class="col-sm-10">
                                <input type="checkbox" ng-model="vm.entity.isAlwaysEnable" ng-checked="vm.entity.isAlwaysEnable">
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
<script type="text/javascript" src="${contextPath}/resources/lib/jquery-ui.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/lib/jquery-ui-i18n.min.js"></script>
<script src="${contextPath}/resources/js/schedule.js?v=${res_version}"></script>
</body>
</html>