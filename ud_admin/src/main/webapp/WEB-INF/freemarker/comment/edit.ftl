<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
</head>
<body class="menu-page" ng-controller="EditCommentCtrl as vm">
<div id="main-box">
    <#include "../lib/top_bar.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-md-push-2">
                    <h2 class="page-title">编辑Comment</h2>

                    <form class="form-horizontal" ng-submit="vm.update()">
                        <div class="panel panel-default">
                            <div class="panel-heading">Comment信息</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">id</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.id!''}" ng-model="vm.entity.id">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">orderId</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.orderId!''}" ng-model="vm.entity.orderId">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">score</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.score!''}" ng-model="vm.entity.score">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">description</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${entity.description!''}" ng-model="vm.entity.description">
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
<script src="${contextPath}/resources/js/comment.js?v=${res_version}"></script>
</body>
</html>