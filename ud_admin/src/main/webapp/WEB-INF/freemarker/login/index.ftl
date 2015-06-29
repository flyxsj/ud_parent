<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../lib/header.ftl" />
</head>
<body ng-controller="LoginCtrl as vm">
<div class="container">

    <form class="form-signin" ng-submit="vm.login()">
        <h2 class="form-signin-heading">请登录</h2>
        <input type="text" class="form-control" placeholder="用户名" autofocus ng-model="vm.entity.username">
        <input type="password" class="form-control" placeholder="密码" ng-model="vm.entity.password">
        <br>
        <br>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    </form>
</div>
<#include "../lib/footer.ftl"/>
<script type="text/javascript" src="${contextPath}/resources/js/login.js?v=${res_version}"></script>
<script>
    $(function(){
         //$('.form-signin').trigger('submit');
    });
</script>
</body>
</html>