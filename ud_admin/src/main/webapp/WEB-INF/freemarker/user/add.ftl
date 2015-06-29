<#import "../lib/page.ftl" as page>
<!DOCTYPE html>
<html>
<head>
    <#include "../lib/header.ftl" />
</head>
<body class="menu-page" ng-controller="AddAdminUserCtrl as vm">
<div id="main-box">
    <#include "../lib/top_bar.ftl" />
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-md-push-2">
                    <h2 class="page-title">添加管理员</h2>

                    <form class="form-horizontal" ng-submit="vm.create()">
                        <div class="panel panel-default">
                            <div class="panel-heading">管理员信息</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">username</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.username" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">password</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.password">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">roleType</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.roleType" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">remark</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.remark">
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
<#include "../lib/footer.ftl" />
<script src="${contextPath}/resources/js/adminUser.js?v=${res_version}"></script>
</body>
</html>