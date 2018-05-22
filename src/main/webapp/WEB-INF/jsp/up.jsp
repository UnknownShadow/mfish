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
    <script src="/assets/scripts/ajaxfileupload.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="/assets/layer/common/layui/css/layui.css" media="all">
</head>
<body>
    <script>
        //图片上传
        function uploadF(obj) {
            alert("1");
            $.ajaxFileUpload({
                type:"post",
                url:"/up",
                secureuri:false,// 一般设置为false
                fileElementId:"fil",// 文件上传表单的id <input type="file" id="fileUpload" name="file" />
                dataType:"text/html",
                data:{'id':'1'},
                success:function(msg){
                   alert(msg);
                },
                error:function(data){
                    console.log("服务器异常");
                }
            });
            return false;
        }
    </script>

    <label class='btn btn-danger' for='fil'>图片上传</label>
    <input type='file' accept='image/png,image/jpeg,image/gif,image/jpg' style='position:absolute;clip:rect(0 0 0 0);' onchange='uploadF()'  name='file'  id='fil' />

</body>
</html>

