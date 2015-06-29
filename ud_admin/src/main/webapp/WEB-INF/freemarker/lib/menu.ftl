<#macro list pageType="">
<div class="left-main">
    <div class="list-group">
        <a href="${contextPath}/admin/customer/list.do" class="list-group-item <#if pageType=='customer'>active</#if>">客户管理</a>
        <a href="${contextPath}/admin/terminal/list.do" class="list-group-item <#if pageType=='terminal'>active</#if>">终端管理</a>
        <#if loginAccount.roleType == 'admin'>
        <a href="${contextPath}/admin/account/list.do" class="list-group-item <#if pageType=='user'>active</#if>">用户管理</a>
        </#if>
        <a href="${contextPath}/admin/account/setting.do" class="list-group-item <#if pageType=='setting'>active</#if>">个人设置</a>
        <a href="${contextPath}/admin/config/index.do" class="list-group-item <#if pageType=='other'>active</#if>">其他功能</a>
    </div>
</div>
</#macro>