<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
</head>
<body class="menu-page" ng-controller="AddRecruitCtrl as vm">
<div id="main-box">
    <#include "../lib/top_bar.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-md-push-2">
                    <h2 class="page-title">添加Recruit</h2>

                    <form class="form-horizontal" ng-submit="vm.create()">
                        <div class="panel panel-default">
                            <div class="panel-heading">Recruit信息</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">id</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.id">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">schoolName</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.schoolName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">price</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.price">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">scope</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.scope">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">address</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.address">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">phone</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.phone">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">introduce</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.introduce">
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
<script src="${contextPath}/resources/js/recruit.js?v=${res_version}"></script>
</body>
</html>