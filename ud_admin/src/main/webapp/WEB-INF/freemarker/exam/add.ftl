<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
<link href="${contextPath}/resources/lib/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
</head>
<body class="menu-page" ng-controller="AddExamCtrl as vm">
<div id="main-box">
    <#include "../lib/top_bar.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-md-push-2">
                    <h2 class="page-title">添加题目</h2>

                    <form class="form-horizontal" ng-submit="vm.create()">
                        <div class="panel panel-default">
                            <div class="panel-heading">题目信息</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">科目</label>

                                    <div class="col-sm-10">
                                        <select class="form-control" ng-model="vm.entity.type">
                                            <option value="course1">科目一</option>
                                            <option value="course4">科目四</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">问题</label>

                                    <div class="col-sm-10">
                                        <script type="text/plain" id="questionEditor" style="width:98%;height:240px;">
                                            <p></p>
                                        </script>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">答案选项</label>
                                    <div class="col-sm-10">
                                        <div ng-repeat="item in vm.options">
                                            <span>
                                                <a href="" ng-click="vm.removeOption(item.code)">删除</a>
                                            </span>
                                            <span>{{item.code}}：{{item.content}}</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"></label>
                                    <div class="col-sm-10">
                                        <script type="text/plain" id="optionEditor" style="width:98%;height:80px;">
                                            <p></p>
                                        </script>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"></label>
                                    <div class="col-sm-10">
                                        <button class="btn btn-success" type="button" ng-click="vm.addOption()">添加选项</button>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">正确答案</label>

                                    <div class="col-sm-10" id="answerArea">
                                        <span ng-repeat="item in vm.options">
                                            <input type="checkbox" value="{{item.code}}">{{item.code}}
                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                        </span>
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
<script src="${contextPath}/resources/js/exam.js?v=${res_version}"></script>
</body>
</html>