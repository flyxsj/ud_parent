<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
<link href="${contextPath}/resources/lib/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
</head>
<body class="menu-page" ng-controller="AddVideoCtrl as vm">
<div id="main-box">
    <#include "../lib/top_bar.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-md-push-2">
                    <h2 class="page-title">添加视频</h2>

                    <form class="form-horizontal" ng-submit="vm.create()">
                        <div class="panel panel-default">
                            <div class="panel-heading">视频信息</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">类型</label>

                                    <div class="col-sm-10">
                                        <select class="form-control" ng-model="vm.entity.type">
                                            <option value="course">学车视频</option>
                                            <option value="safety">文明驾车视频</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">标题</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" ng-model="vm.entity.title" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">内容</label>

                                    <div class="col-sm-10">
                                        <script type="text/plain" id="editor" style="width:98%;height:240px;">
                                            <p></p>
                                        </script>
                                    </div>
                                </div>
                                <div>
                                    <button class="btn btn-success pull-right" type="submit">保存</button>
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
<script type="text/javascript" charset="utf-8" src="${contextPath}/resources/lib/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${contextPath}/resources/lib/umeditor/umeditor.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${contextPath}/resources/lib/umeditor/lang/zh-cn/zh-cn.js"></script>
<script src="${contextPath}/resources/js/video.js?v=${res_version}"></script>
</body>
</html>