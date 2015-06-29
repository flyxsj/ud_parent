<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
</head>
<body ng-controller="AddStudentCtrl as vm">
<div class="container" style="margin-top: 20px;">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-push-2">
            <h2 class="page-title">学员注册</h2>

            <form class="form-horizontal" ng-submit="vm.register()">
                <div class="panel panel-default">
                    <div class="panel-heading">学员信息</div>
                    <div class="panel-body">
                        <div class="form-group hidden">
                            <label class="col-sm-2 control-label">openid</label>

                            <div class="col-sm-10">
                                <input type="hidden" name="openid" class="form-control" value="${openid!''}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">mobile</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" ng-model="vm.mobile">
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
<script src="${contextPath}/resources/js/student.js?v=${res_version}"></script>
</body>
</html>