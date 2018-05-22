<html lang="en">
<head>
    <title>聚牛网络|版本管理</title>
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
        <li class="layui-nav-item">
            <a href="/paid_records">付费玩家记录</a>
        </li>

        <li class="layui-nav-item"><a href="/integral_manage">玩家积分管理</a></li>

        <li class="layui-nav-item">
            <a href="/status_editor?page=1&limit=20">消息管理</a>
        </li>
        <li class="layui-nav-item">
            <a href="/competition_list?page=1&limit=20&proxy_date=&proxy_date_end=">比赛管理</a>
        </li>
        <li class="layui-nav-item">
            <a href="/reward_pending?page=1&start_date=&end_date=&user_id=">比赛发奖管理</a>
        </li>
        <li class="layui-nav-item"><a href="/log_output?page=1&limit=20&user_id=">玩家日志查询</a></li>
        <li class="layui-nav-item layui-this"><a href="/version_history?page=1">版本管理</a></li>
    </ul>

    <div style="padding: 20px;margin-top: 60px;margin-bottom: 100px;">
        <a href="/version_configuration" style="position: absolute;right: 50px;margin-top: -50px;" class="btn btn-primary">配置新版本</a>

        <div class="" >     <#--table-responsive-->
            <table class="table table-hover" >
                <tr class="active success">
                    <th width="5%">版本号</th>
                    <th width="5%">平台</th>
                    <th width="5%">下载地址</th>
                    <th width="5%">强制更新</th>
                    <th width="20%">更新说明</th>
                    <th width="10%">操作人</th>
                    <th width="10%">操作时间</th>
                    <th width="10%">删除</th>
                </tr>
                <#list versionsList as zhi>
                    <tr>
                        <td>${zhi.ver}</td>
                        <td>
                            <#if zhi.device_type==1> <span>IOS</span> </#if>
                            <#if zhi.device_type==2> <span>Android</span> </#if>
                            <#if zhi.device_type==3> <span>全平台</span> </#if>
                        </td>
                        <td>${zhi.down_url}</td>
                        <td>
                            <#if zhi.force==0> <span>非强制</span> </#if>
                            <#if zhi.force==1> <span>强制</span> </#if>
                        </td>
                        <td>${zhi.content}</td>
                        <td>${zhi.nickName}</td>
                        <td>
                            ${zhi.created_at?datetime}
                        </td>
                        <td>
                            <a href="javascript:;" class="btn btn-danger del" alt="${zhi.id}">删除</a>
                        </td>
                    </tr>
                </#list>
            </table>
        </div>
        <div class="col-md-4 col-md-offset-8" style="margin-top: 50px;">
            <table>
                <tr>
                    <td>
                        <a href="javaScript:mofen(1)"><img src="bs/images/img_firstpage.gif" width="16" height="16" /></a>&nbsp;
                        <a href="javaScript:mofen(${page}-1)"><img src="bs/images/img_prevpage.gif" width="11" height="16" /></a>&nbsp;${page }/${total }&nbsp;
                    </td>
                    <td align="right">
                        <a href="javaScript:mofen(${page}+1)"><img src="bs/images/img_nextpage.gif" width="12" height="16" /></a>&nbsp;
                        <a href="javaScript:mofen(${total})"><img src="bs/images/img_lastpage.gif" width="16" height="16" /></a>
                        <input type="hidden" value="${total}" id="hidd"/>
                    </td>
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
        var page = parseInt(page);
        if(page<=1){
            page=1;
        }else if(page>=total){
            page=total;
        }
        window.location.href="/version_history?page="+page;
    }
    
    $(".del").click(function () {
        var id = $(this).attr("alt");

        var obj="您确定要删此版本记录";
        var r=confirm(obj);
        if (r==true){
            window.location.href="/version_del?id="+id;
        }else{
            return false;
        }
    });
</script>
</html>
