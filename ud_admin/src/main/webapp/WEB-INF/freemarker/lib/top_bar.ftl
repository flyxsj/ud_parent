<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="${contextPath}/admin/user/main.do">首页</a>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">系统用户<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${contextPath}/admin/user/list.do">管理员列表</a></li>
                        <li><a href="buyer-add.html">添加管理员</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">教练管理<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${contextPath}/admin/driver/list.do">教练列表</a></li>
                        <li><a href="${contextPath}/admin/recruit/list.do">店面管理</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">学员管理<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${contextPath}/admin/student/list.do">学员列表</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">题库管理<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${contextPath}/admin/exam/list.do">题库列表</a></li>
                        <li><a href="${contextPath}/admin/exam/add.do">添加题目</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">视频管理<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${contextPath}/admin/video/list.do">视频列表</a></li>
                        <li><a href="${contextPath}/admin/video/add.do">添加视频</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Menu Test<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <label>&nbsp;&nbsp;Parent 1</label>
                        <li><a href="rule-list.html">Sub menu1</a></li>
                        <label>&nbsp;&nbsp;Parent 2</label>
                        <li><a href="rule-list.html">Sub menu2</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span>
                        <span id="logigUsername">${loginAccount.username}</span></a>
                    <ul class="dropdown-menu">
                        <li><a href="change-password.html">修改密码</a></li>
                        <li role="presentation" class="divider"></li>
                        <li><a class="logout" href="#">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>