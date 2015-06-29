<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
<link href="${contextPath}/resources/lib/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
</head>
<body ng-controller="AddRecruitCtrl as vm">
<div class="container" style="margin-top: 20px;">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-push-2">
            <h2 class="page-title">发布驾校招生信息</h2>

            <form class="form-horizontal" ng-submit="vm.create()">
                <div class="panel panel-default">
                    <div class="panel-heading">招生信息</div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">驾校名称</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" ng-model="vm.entity.schoolName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">学车价格</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" ng-model="vm.entity.price">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">招生范围</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" ng-model="vm.entity.scope">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">驾校地址</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="txtAddress" ng-model="vm.entity.address">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">联系电话</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" ng-model="vm.entity.phone">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">驾校简介</label>

                            <div class="col-sm-10">
                                <script type="text/plain" id="introduceEditor" style="width:100%;height:240px;">
                                    <p></p>
                                </script>
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
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2"></script>
<script type="text/javascript" charset="utf-8" src="${contextPath}/resources/lib/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${contextPath}/resources/lib/umeditor/umeditor.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${contextPath}/resources/lib/umeditor/lang/zh-cn/zh-cn.js"></script>
<script src="${contextPath}/resources/js/recruit.js?v=${res_version}"></script>
</body>
</html>