<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>聚牛网络|活动详情</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
    <script src="assets/scripts/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/assets/layer/common/layui/css/layui.css" media="all">
</head>
<style>a:hover{text-decoration: none;}</style>
<body>

    <ul class="layui-nav" lay-filter="">
        <li class="layui-nav-item layui-this"><a href="/proxy_overview">马王活动配置</a></li>
        <li class="layui-nav-item"><a href="/proxy_manage">添加删除代理</a></li>
        <li class="layui-nav-item"><a href="/intent_proxy?page=1">意向代理</a></li>
    </ul>

    <div style="padding: 20px;">
        <div style="margin-left: 20px;margin-right: 20px;">
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-hover table-bordered">
                        <tr class="active success">
                            <th>代理总数</th>
                        </tr>
                        <tr>
                            <td>${proxy}</td>
                        </tr>
                    </table>

                    <div class="row">
                        <div class="col-md-6">
                            <form action="#" method="post" class="navbar-form navbar-left" name="formSer">
                                <div class="input-group">
                                    <input type="text" id="userId" value="${userId}" class="form-control" placeholder="请输入用户ID..." maxlength="12">
                                    <span class="input-group-btn"><input type="button" id="qurey" class="btn btn-primary" value="查询"></span>
                                </div>
                            </form>
                        </div>
                    </div>

                    <input type="hidden" value="${limit}" id="y">

                    <div class="row ">
                        <div class="col-md-3 pull-right" style="margin-bottom: 15px;">
                           <select class="form-control" id="limit">
                               <option value="10" id="ten">每页显示10条</option>
                               <option value="20" id="fifteen">每页显示20条</option>
                               <option value="50" id="twenty">每页显示50条</option>
                               <option value="100" id="twentyFive">每页显示100条</option>
                           </select>
                        </div>
                    </div>

                    <table class="table table-hover">
                        <tr class="active success">
                            <th>玩家ID</th>
                            <th>玩家昵称</th>
                            <th>用户身份</th>
                            <th>钻石数量</th>
                            <th>积分数量</th>
                            <th>上级代理</th>
                            <th>额外赠送调整</th>
                            <th>操作</th>
                        </tr>
                           <c:forEach items="${proxyLists}" var="zhi">
                                <tr>
                                    <td>${zhi.users_id}</td>
                                    <td>${zhi.nickname}</td>
                                    <td>
                                        <c:if test="${zhi.user_status==2}">代理</c:if>
                                        <c:if test="${zhi.user_status==3}">玩家</c:if>
                                    </td>
                                    <td>${zhi.diamond}</td>
                                    <td>${zhi.total_integral}</td>
                                    <td>
                                        <c:if test="${zhi.fatherproxy_id==1}">系统代理</c:if>
                                        <c:if test="${zhi.fatherproxy_id!=1 && zhi.fatherproxy_id!=0}">ID:${zhi.fatherproxy_id}，昵称：${zhi.proxyNickname}</c:if>
                                    </td>
                                    <td>
                                        <c:if test="${zhi.adjuster != ''}">${zhi.adjuster}</c:if>
                                        <c:if test="${zhi.adjuster == ''}"> --- </c:if>
                                        <c:if test="${zhi.adjuster == null}"> --- </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${zhi.user_status==2}">
                                            <a href="#" class="btn btn-primary giveDimond" alt="${zhi.users_id}" >设置赠送比例</a>
                                        </c:if>
                                    </td>
                                </tr>
                           </c:forEach>
                    </table>
                </div>
            </div>

            <div class="col-md-4 col-md-offset-8" style="margin-top: 50px;">
                <table>
                    <tr>
                        <td>
                            <a href="javaScript:mofen(1)"><img src="bs/images/img_firstpage.gif" width="16" height="16" /></a>&nbsp;
                            <a href="javaScript:mofen(${page-1 })"><img src="bs/images/img_prevpage.gif" width="11" height="16" /></a>&nbsp;${page }/${total }&nbsp;
                        </td>
                        <td align="right">
                            <a href="javaScript:mofen(${page+1 })"><img src="bs/images/img_nextpage.gif" width="12" height="16" /></a>&nbsp;
                            <a href="javaScript:mofen(${total })"><img src="bs/images/img_lastpage.gif" width="16" height="16" /></a>
                            <input type="hidden" value="${total}" id="hidd"/>
                        </td>
                        <td>&nbsp;&nbsp;&nbsp;</td>
                        <td>共 ${count} 条记录</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</body>
<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/assets/layer/common/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['jquery','layer','element','laypage'],function(){
        window.jQuery = window.$ = layui.jquery;
        window.layer = layui.layer;
    });


    $(function () {
        //按回车键操作
        $("#userId").keydown(function (event) {
            var keyCode = event.keyCode;
            if(keyCode == 13){
                $("#qurey").click();
            }
        });
        $("#qurey").click(function () {
            var userId = $("#userId").val();
            formSer.action="/proxy_preview?page=1&limit=&userId="+userId;
            formSer.submit();
        });


        $(".giveDimond").click(function () {
            var user_id = $(this).attr("alt");
            window.location.href="/bestowal_adjustment?user_id="+user_id;
        });


        $("#limit").change(function () {
            var limit = $(this).val();
            var userId = $("#userId").val();
            window.location.href="/proxy_preview?page=1&limit="+limit+"&userId="+userId;
        });

        var y = $("#y").val();
        if(y == 20){
            $("#fifteen").attr("selected","true");
            $("#ten").removeAttr("selected");
            $("#twentyFive").removeAttr("selected");
            $("#twenty").removeAttr("selected");
        }else if(y == 50){
            $("#twenty").attr("selected","true");
            $("#fifteen").removeAttr("selected");
            $("#ten").removeAttr("selected");
            $("#twentyFive").removeAttr("selected");
        }else if(y == 100){
            $("#twentyFive").attr("selected","true");
            $("#twenty").removeAttr("selected");
            $("#fifteen").removeAttr("selected");
            $("#ten").removeAttr("selected");
        }else{
            $("#ten").attr("selected","true");
            $("#fifteen").removeAttr("selected");
            $("#twenty").removeAttr("selected");
            $("#twentyFive").removeAttr("selected");
        }

    });


    function mofen(page){
        var total = document.getElementById("hidd").value;
        var userId = document.getElementById("userId").value;
        var y = document.getElementById("y").value;
        var page = parseInt(page);
        if(page<=1){
            page=1;
        }else if(page>=total){
            page=total;
        }
        window.location.href="/proxy_preview?limit="+y+"&page="+page+"&userId="+userId;
    }

</script>

</html>

