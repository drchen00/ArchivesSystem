<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>权证管理</title>

    <!--IE Compatibility modes-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!--Mobile first-->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap -->
    <link rel="stylesheet" href="assets/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/lib/datetimepicker/build/css/bootstrap-datetimepicker.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/lib/font-awesome/css/font-awesome.min.css">

    <!-- Metis core stylesheet -->
    <link rel="stylesheet" href="assets/css/main.css">

    <!-- metisMenu stylesheet -->
    <link rel="stylesheet" href="assets/lib/metisMenu/dist/metisMenu.css">

    <link rel="stylesheet" href="assets/css/theme.css">

    <!--Modernizr-->
    <script src="assets/lib/modernizr/modernizr.min.js"></script>
</head>
<body class="">
<div class="bg-dark dk" id="wrap">
    <div id="top">

        <!-- .navbar -->
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">

                <!-- Brand and toggle get grouped for better mobile display -->
                <header class="navbar-header">
                    <a href="index.jsp" class="navbar-brand text-center">电子档案系统</a>
                </header>
                <div class="topnav">
                    <div class="btn-group">
                        <a data-placement="bottom" data-original-title="全屏" data-toggle="tooltip"
                           class="btn btn-default btn-sm" id="toggleFullScreen">
                            <i class="glyphicon glyphicon-fullscreen"></i>
                        </a>
                    </div>
                    <div class="btn-group">
                        <a href="logout.action" data-toggle="tooltip" data-original-title="登出" data-placement="bottom"
                           class="btn btn-metis-1 btn-sm">
                            <i class="fa fa-power-off"></i>
                        </a>
                    </div>
                    <div class="btn-group">
                        <a data-placement="bottom" data-original-title="显示/隐藏左侧边栏" data-toggle="tooltip"
                           class="btn btn-primary btn-sm toggle-left" id="menu-toggle">
                            <i class="fa fa-bars"></i>
                        </a>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </nav><!-- /.navbar -->
    </div><!-- /#top -->
    <div id="left">
        <div class="media user-media bg-dark dker">
            <div class="user-media-toggleHover">
                <span class="fa fa-user"></span>
            </div>
            <div class="user-wrapper bg-dark">
                <div class="media-body">
                    <h5 class="media-heading">
                        用户
                        <s:bean name="com.bean.UserBean" var="user"/>
                        <s:property value="#user.name"/>
                    </h5>
                    <ul class="list-unstyled user-info">
                        <li>权限 Administrator</li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- #menu -->
        <ul id="menu" class="bg-blue dker">
            <li class="nav-header">Menu</li>
            <li class="nav-divider"></li>
            <li>
                <a href="index.jsp">
                    <i class="fa fa-home"></i>
                    <span class="link-title">主页</span>
                </a>
            </li>
            <li>
                <a href="tags.jsp">
                    <i class="fa fa-tags"></i>
                    <span class="link-title">标签管理</span>
                </a>
            </li>
            <li class="active">
                <a href="#">
                    <i class="fa fa-archive"></i>
                    <span class="link-title">权证管理</span>
                    <span class="fa arrow"></span>
                </a>
                <ul>
                    <li>
                        <a href="archive-query.jsp">
                            <i class="fa fa-angle-right"></i>
                            <span class="link-title">查询</span>
                        </a>
                    </li>
                    <li>
                        <a href="archive-stock.jsp">
                            <i class="fa fa-angle-right"></i>
                            <span class="link-title">入库</span>
                        </a>
                    </li>
                    <li class="active">
                        <a href="#">
                            <i class="fa fa-angle-right"></i>
                            <span class="link-title">借出</span>
                        </a>
                    </li>
                    <li>
                        <a href="archive-return.jsp">
                            <i class="fa fa-angle-right"></i>
                            <span class="link-title">归还</span>
                        </a>
                    </li>
                    <li>
                        <a href="archive-destroy.jsp">
                            <i class="fa fa-angle-right"></i>
                            <span class="link-title">销毁</span>
                        </a>
                    </li>
                    <li>
                        <a href="archive-check.jsp">
                            <i class="fa fa-angle-right"></i>
                            <span class="link-title">盘点</span>
                        </a>
                    </li>
                </ul>
            </li>
            <li class="nav-divider"></li>
            <li>
                <a href="login.html">
                    <i class="fa fa-sign-in"></i>
              <span class="link-title">
    Login Page
    </span>
                </a>
            </li>
        </ul><!-- /#menu -->
    </div><!-- /#left -->
    <div id="content">
        <div class="outer">
            <div class="main-bar">
                <h3>
                    <i class="fa fa-archive"></i>
                    <span class="link-title">权证管理</span>
                </h3>
            </div><!-- /.main-bar -->
            <div class="inner bg-light lter">
                <style>
                    .col-md-6.input-group,
                    .form-control.col-md-6 {
                        width: 50% !important;
                    }

                    .input-group {
                        float: left !important;
                        display: inline-table;
                    }
                </style>
                <div class="row">
                    <div class="col-md-12">
                        <div class="box">
                            <header>
                                <h4>权证借出</h4>
                            </header>
                            <div class="body">
                                <form class="form-horizontal" id="loan" action="loan_archive.action" method="post">
                                    <div class="form-group">
                                        <label class="control-label col-md-4">标签编号</label>
                                        <div class="col-md-8">
                                            <div class="col-md-6 input-group">
                                                <input id="tagNum" name="tagNum" readonly="readonly"
                                                       class="form-control">
                                                <span class="input-group-addon" style="cursor: hand">
                                                    <i class="fa fa-refresh"></i>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">借出时间</label>
                                        <div class="col-md-8">
                                            <div class="col-md-6 input-group date" id="time">
                                                <input name="time" class="form-control ignore" readonly="readonly">
                                                <span class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">权证名</label>
                                        <div class="col-md-8">
                                            <input id="name" name="name" readonly="readonly"
                                                   class="form-control col-md-6 ignore">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">权证编号</label>
                                        <div class="col-md-8">
                                            <input id="archiveNum" readonly="readonly" name="archiveNum"
                                                   class="form-control col-md-6 ignore">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">权证状态</label>
                                        <div class="col-md-8">
                                            <input id="status" readonly="readonly" name="status"
                                                   class="form-control col-md-6">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">创建时间</label>
                                        <div class="col-md-8">
                                            <input id="createdTime" name="createdTime" readonly="readonly"
                                                   class="form-control col-md-6 ignore">
                                        </div>
                                    </div>
                                    <div class="form-actions">
                                        <input type="submit" value="提交" class="btn btn-primary">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <br>
            </div><!-- /.inner -->
        </div><!-- /.outer -->
    </div><!-- /#content -->
</div><!-- /#wrap -->
<footer class="Footer bg-dark dker">
    <p>2014 &copy; Metis Bootstrap Admin Template</p>
</footer><!-- /#footer -->

<!-- #helpModal -->
<div id="helpModal" class="modal fade" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">标签获取中</h4>
            </div>
            <div class="modal-body">
                <p>
                    这是一个模态框
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消获取</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal --><!-- /#helpModal -->

<!--jQuery -->
<script src="assets/lib/jquery/jquery-2.2.4.min.js"></script>
<script src="assets/lib/validation/dist/jquery.validate.min.js"></script>
<script src="assets/lib/validation/dist/localization/messages_zh.min.js"></script>
<script src="assets/lib/validation/dist/additional-methods.js"></script>

<!--Bootstrap -->
<script src="assets/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/lib/moment/moment-with-locales.js"></script>
<script src="assets/lib/datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>

<!-- MetisMenu -->
<script src="assets/lib/metisMenu/dist/metisMenu.min.js"></script>

<!-- Screenfull -->
<script src="assets/lib/screenfull/screenfull.min.js"></script>

<!-- Metis core scripts -->
<script src="assets/js/core.min.js"></script>
<script src="assets/js/loan.js"></script>
<script>var userID = '<s:property value="#user.id"/>'</script>
<script src="assets/js/webSocket.js"></script>
</body>
</html>