<!DOCTYPE html>
<html>
<head>
    <#include "../lib/header.ftl">
</head>
<body ng-controller="AddLuckCtrl as vm">
<div class="container" style="margin-top: 20px;">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-push-2">
            <h2 class="page-title">抽奖</h2>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div>
                        <button class="btn btn-success" type="button" ng-click="vm.getLuckBean()">点击抽奖</button>
                        <label ng-show="vm.amount > 0">恭喜你抽中{{vm.amount}}U豆，分享才可以获得</label>
                    </div>
                    <div ng-show="vm.amount > 0" style="margin-top: 20px;">
                        <button class="btn btn-success" type="button" ng-click="vm.share()">分享获取U豆</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "../lib/footer.ftl">
    <script src="${contextPath}/resources/js/luck.js?v=${res_version}"></script>
</body>
</html>