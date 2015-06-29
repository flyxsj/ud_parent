<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/jquery-ui.min.css">
</head>
<body ng-controller="AddOrderCtrl as vm">
<div class="container" style="margin-top: 20px;">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-push-2">
            <h2 class="page-title">预约陪练/陪驾</h2>

            <div class="panel panel-default">
                <div class="panel-heading">预约信息</div>
                <form class="form-horizontal" ng-submit="vm.search()">
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">studentId</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" readonly value="2"
                                       ng-model="vm.entity.studentId">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">陪练/陪驾</label>

                            <div class="col-sm-10">
                                <select class="form-control" ng-model="vm.entity.type">
                                    <option value="exercise">陪练</option>
                                    <option value="drive">陪驾</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" ng-show="vm.entity.type == 'exercise'">
                            <label class="col-sm-2 control-label">陪练科目</label>

                            <div class="col-sm-10">
                                <select class="form-control" ng-model="vm.entity.course">
                                    <option value="exercise2">科目二</option>
                                    <option value="exercise3">科目三</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">地址</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="txtAddress" ng-model="vm.entity.address">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">预约日期</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" maxlength="10" id="txtDay"
                                       ng-model="vm.entity.day">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">预约开始时间</label>

                            <div class="col-sm-10">
                                <select ng-model="startTime" ng-cloak>
                                    <option ng-repeat="o in vm.startTimeRange" value="{{o.value}}"
                                            ng-selected="{{o.value == startTime}}">{{o.label}}
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">预约结束时间</label>

                            <div class="col-sm-10">
                                <select ng-model="endTime" ng-cloak>
                                    <option ng-repeat="o in vm.endTimeRange" value="{{o.value}}"
                                            ng-selected="{{o.value == endTime}}">{{o.label}}
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div>
                            <button class="btn btn-success pull-right" type="submit">搜索教练</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="panel panel-default" ng-show="vm.driverList.length">
                <div class="panel-heading">匹配的教练列表</div>
                <div class="panel-body">
                    <div class="form-group clearfix">
                        <div class="col-sm-12" ng-repeat="item in vm.driverList">
                            <input type="radio" name="driverId" value="{{item.driver.id}}">
                            教练：{{item.driver.name}}
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            地址：{{item.driver.address}}
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            距离：{{item.distance}}km
                        </div>
                    </div>
                    <div>
                        <button class="btn btn-success pull-right" type="button" ng-click="vm.reserve()">预
                            约
                        </button>
                    </div>
                </div>
            </div>
            <div class="panel panel-default" ng-show="vm.driverList.length == 0 && vm.searching">
                <div class="panel-heading">附近无教练</div>
            </div>
        </div>
    </div>
</div>
<#include "../lib/footer.ftl">
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2"></script>
<script type="text/javascript" src="${contextPath}/resources/lib/jquery-ui.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/lib/jquery-ui-i18n.min.js"></script>
<script src="${contextPath}/resources/js/order.js?v=${res_version}"></script>
</body>
</html>