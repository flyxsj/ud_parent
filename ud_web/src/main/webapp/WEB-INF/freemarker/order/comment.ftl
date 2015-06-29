<!DOCTYPE html>
<html>
<head>
<#include "../lib/header.ftl">
</head>
<body ng-controller="CommentOrderCtrl as vm">
<div class="container" style="margin-top: 20px;">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-push-2">
            <h2 class="page-title">评论订单</h2>

            <div class="panel panel-default">
                <div class="panel-heading">订单信息</div>
                <form class="form-horizontal" ng-submit="vm.saveComment()">
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">orderNumber</label>

                            <div class="col-sm-10">
                                <input type="text" disabled class="form-control" value="${entity.orderNumber!''}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">教练</label>

                            <div class="col-sm-10">
                                <input type="text" disabled class="form-control" value="${driver.name!''}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">reserveDay</label>

                            <div class="col-sm-10">
                                <input type="text" disabled class="form-control"
                                       value="${entity.reserveDay?string('yyyy-MM-dd')}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">reserveStartTime</label>

                            <div class="col-sm-10">
                                <input type="text" disabled class="form-control"
                                       value="${entity.reserveStartTime?string('HH:mm')}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">reserveEndTime</label>

                            <div class="col-sm-10">
                                <input type="text" disabled class="form-control" value="${entity.reserveEndTime?string('HH:mm')}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">trainStartTime</label>

                            <div class="col-sm-10">
                                <input type="text" disabled class="form-control"
                                       value="${entity.trainStartTime?string('yyyy-MM-dd HH:mm:ss')}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">trainEndTime</label>

                            <div class="col-sm-10">
                                <input type="text" disabled class="form-control" value="${entity.trainEndTime?string('yyyy-MM-dd HH:mm:ss')}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">评分</label>

                            <div class="col-sm-10">
                                <select class="form-control" ng-model="vm.level">
                                    <option value="good">好评</option>
                                    <option value="normal">一般</option>
                                    <option value="bad">差评</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">评论</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" ng-model="vm.comment">
                            </div>
                        </div>
                        <div>
                            <button class="btn btn-success pull-right" type="submit">提交评论</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<#include "../lib/footer.ftl">
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2"></script>
<script src="${contextPath}/resources/js/order.js?v=${res_version}"></script>
</body>
</html>