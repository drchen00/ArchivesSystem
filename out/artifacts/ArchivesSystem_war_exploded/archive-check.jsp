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

    <%--dataTables--%>
    <link rel="stylesheet" href="assets/lib/DataTables/media/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="assets/lib/DataTables/Select/css/select.bootstrap.min.css">

    <!-- Metis core stylesheet -->
    <link rel="stylesheet" href="assets/css/main.css">

    <!-- metisMenu stylesheet -->
    <link rel="stylesheet" href="assets/lib/metisMenu/dist/metisMenu.css">

    <link rel="stylesheet" href="assets/css/theme.css">

    <!--Modernizr-->
    <script src="assets/lib/modernizr/modernizr.min.js"></script>
</head>
<body class="menu-affix">
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
                    <li>
                        <a href="archive-loan.jsp">
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
                    <li class="active">
                        <a href="#">
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
                    tfoot {
                        display: table-header-group;
                    }

                    tfoot input.form-control {
                        width: 100% !important;
                    }

                    tfoot input.date {
                        width: 160px !important;
                    }
                </style>

                <div class="row">
                    <div class="col-md-12">
                        <div class="box">
                            <header>
                                <h4>盘点记录</h4>
                            </header>
                            <div class="body">
                                <table class="table table-bordered" id="checkRecord">
                                    <thead>
                                    <tr>
                                        <th>编号</th>
                                        <th>盘点条件</th>
                                        <th>盘点内容</th>
                                        <th>盘点人</th>
                                        <th>开始时间</th>
                                        <th>结束时间</th>
                                    </tr>
                                    </thead>
                                </table>
                                <button id="newCheck" type="button" class="btn btn-primary">新建盘点</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="box" style="display: none">
                            <header>
                                <h5>新建盘点</h5>
                                <div class="toolbar">
                                    <div class="btn-group">
                                        <a href="#checkStart" data-toggle="collapse"
                                           class="btn btn-sm btn-default minimize-box">
                                            <i class="fa fa-angle-up"></i>
                                        </a>
                                        <a class="btn btn-danger btn-sm close-box">
                                            <i class="fa fa-times"></i>
                                        </a>
                                    </div>
                                </div>
                            </header>
                            <div id="checkStart" class="body collapse in" style="min-height: 420px">
                                <table class="table table-bordered" id="archivesTable" width="100%">
                                    <tfoot>
                                    <tr>
                                        <th><input type="hidden" placeholder="按编号搜索" class="form-control"></th>
                                        <th><input type="hidden" placeholder="按名字搜索" class="form-control"></th>
                                        <th><input type="hidden" placeholder="按标签搜索" class="form-control"></th>
                                        <th>
                                            <select id="status" class="form-control">
                                                <option value="0">在库</option>
                                            </select>
                                        </th>
                                        <th>
                                            <input id="startTime" type="text" placeholder="起始时间"
                                                   class="form-control date" readonly="readonly">
                                            <span>--</span>
                                            <input id="endTime" type="text" placeholder="结束时间" class="form-control date"
                                                   readonly="readonly">
                                        </th>
                                    </tr>
                                    </tfoot>
                                    <thead>
                                    <tr>
                                        <th>权证编号</th>
                                        <th>名字</th>
                                        <th>标签编号</th>
                                        <th>状态</th>
                                        <th>创建时间</th>
                                    </tr>
                                    </thead>
                                </table>
                                <button type="button" class="btn btn-primary">开始盘点</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row" id="demo-result">
                    <div class="col-md-12">
                        <div class="box" style="display: none">
                            <header>
                                <h5>编号盘点结果</h5>
                                <div class="toolbar">
                                    <div class="btn-group">
                                        <a href="#" data-toggle="collapse" class="btn btn-sm btn-default minimize-box">
                                            <i class="fa fa-angle-up"></i>
                                        </a>
                                        <a class="btn btn-danger btn-sm remove-row">
                                            <i class="fa fa-times"></i>
                                        </a>
                                    </div>
                                </div>
                            </header>
                            <div class="body collapse in" id="demo-body">
                                <table class="table table-bordered" width="100%">
                                    <thead>
                                    <tr>
                                        <th>权证编号</th>
                                        <th>权证名称</th>
                                        <th>盘点结果</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

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

<!--Bootstrap -->
<script src="assets/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/lib/moment/moment-with-locales.js"></script>
<script src="assets/lib/datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>

<%--dataTables--%>
<script src="assets/lib/DataTables/media/js/jquery.dataTables.min.js"></script>
<script src="assets/lib/DataTables/media/js/dataTables.bootstrap.min.js"></script>
<script src="assets/lib/DataTables/Select/js/dataTables.select.min.js"></script>

<!-- MetisMenu -->
<script src="assets/lib/metisMenu/dist/metisMenu.min.js"></script>

<!-- Screenfull -->
<script src="assets/lib/screenfull/screenfull.min.js"></script>

<!-- Metis core scripts -->
<script src="assets/js/core.min.js"></script>
<script>var userID = '<s:property value="#user.id"/>'</script>
<script src="assets/js/webSocket.js"></script>
<script src="assets/js/check.js"></script>
<script src="assets/js/archivesTable.js"></script>
</body>