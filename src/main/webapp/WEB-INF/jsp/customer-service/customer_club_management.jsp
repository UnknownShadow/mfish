<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>潮尚玩|客服-俱乐部管理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
    <script src="assets/scripts/jquery-1.9.1.min.js"></script>

    <link rel="stylesheet" type="text/css" href="/assets/layer/common/layui/css/layui.css" media="all">
</head>
<style> a:hover{text-decoration: none;} </style>
<body>

    <div style="padding: 20px;margin-bottom: 100px;">
        <form action="#" method="post" name="formSer">
            <div>
                <div style="width: 135px;">
                    <label>&nbsp;</label>
                    <select class="form-control" id="findStatus">
                        <option value="1" id="playID">玩家ID查询</option>
                        <option value="2" id="clubID">俱乐部ID查询</option>
                    </select>
                    <div class="input-group" style="margin-top: -34px;margin-left: 145px;margin-bottom: 10px;">
                        <input type="text" id="userId" value="${userId}" style="width: 170px;" class="form-control" placeholder="请输入查询的ID..." maxlength="12">
                        <span class="input-group-btn"><input type="button" id="qurey" class="btn btn-primary" value="查询"></span>
                    </div>
                </div>
            </div>
        </form>

        <input type="hidden" value="${queryStatus}" id="status"/>

        <input type="hidden" value="${limit}" id="y">
        <div class="row " style="margin-top: -20px;">
            <div class="col-md-3 pull-right" style="margin-bottom: 15px;">
                <select class="form-control" id="limit">
                    <option value="10" id="ten">每页显示10条</option>
                    <option value="20" id="fifteen">每页显示20条</option>
                    <option value="50" id="twenty">每页显示50条</option>
                    <option value="100" id="twentyFive">每页显示100条</option>
                </select>
            </div>
        </div>

        <div class="" ><%--table-responsive "--%>
            <table class="table table-hover" >
                <tr class="active success">
                    <th>俱乐部ID</th>
                    <th>创建者昵称（ID）</th>
                    <th>创建时间</th>
                    <th>游戏类型</th>
                    <%--<th>名称</th>--%>
                    <th>当前人数</th>
                    <th>总局数</th>
                    <th>当日局数</th>
                    <%--<th>详情</th>--%>
                </tr>
                <c:forEach items="${clubs}" var="zhi" >
                    <tr>
                        <td>${zhi.code}</td>
                        <td>${zhi.nickname}（${zhi.creator_id}）</td>
                        <td>
                            <fmt:parseDate value="${zhi.created_at}" pattern="yyyy-MM-dd HH:mm:ss" var="joindate"/>
                            <fmt:formatDate value='${joindate }' pattern='yyyy-MM-dd HH:mm:ss'/>
                        </td>
                        <td>
                            <c:if test="${zhi.gamekind==1}">
                                鱼虾蟹
                            </c:if>
                            <c:if test="${zhi.gamekind==2}">
                                上游
                            </c:if>
                            <c:if test="${zhi.gamekind==3}">
                                激K
                            </c:if>
                            <c:if test="${zhi.gamekind==4}">
                                麻将
                            </c:if>
                        </td>
                        <%--<td>${zhi.name}</td>--%>
                        <td>${zhi.members}</td>
                        <td>${zhi.game_times}</td>
                        <td>${zhi.today_game_times}</td>
                        <%--<td>
                            <a href="javascript:;" class="btn btn-primary check" alt="${zhi.id}">查看详情</a>
                        </td>--%>
                    </tr>
                </c:forEach>
            </table>
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
</body>
<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/assets/layer/common/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['jquery','layer','element','laypage'],function(){
        window.jQuery = window.$ = layui.jquery;
        window.layer = layui.layer;
    });

    function mofen(page){
        var total = document.getElementById("hidd").value;
        var user_id = document.getElementById("userId").value;
        var queryStatus = document.getElementById("findStatus").value;
        var limit = document.getElementById("y").value;
        var page = parseInt(page);
        if(page<=1){
            page=1;
        }else if(page>=total){
            page=total;
        }
        window.location.href="/club_management?page="+page+"&queryId="+user_id+"&queryStatus="+queryStatus+"&limit="+limit+"&status=1";
    }


    $(function () {
        var status = $("#status").val();
        if(status == "2"){
            $("#clubID").attr("selected","true");
            $("#playID").removeAttr("selected");
        }else{
            $("#playID").attr("selected","true");
            $("#clubID").removeAttr("selected");
        }

        $(".check").click(function () {
            var id = $(this).attr("alt");
            window.location.href="/club_details?page=1&limit=20&club_id="+id;
        });


        //按回车键操作
        $("#userId").keydown(function (event) {
            var keyCode = event.keyCode;
            if(keyCode == 13){
                $("#qurey").click();
            }
        });
        $("#qurey").click(function () {
            var queryStatus = $("#findStatus").val();
            var user_id = $("#userId").val();
            var limit = $("#y").val();
            formSer.action="/club_management?page=1&queryId="+user_id+"&queryStatus="+queryStatus+"&limit="+limit+"&status=1";
            formSer.submit();
        });


        $("#limit").change(function () {
            var limit = $(this).val();
            var userId = $("#userId").val();
            var queryStatus = $("#findStatus").val();
            window.location.href="/club_management?page=1&queryId="+userId+"&queryStatus="+queryStatus+"&limit="+limit+"&status=1";
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

</script>
</html>
