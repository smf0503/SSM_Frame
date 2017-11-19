<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
    <script type="text/javascript">
        //检查信息是否填写完整
        function check(){
            var userCode=$("[name=userCode]").val();
            var userName=$("[name=userName]").val();
            var userpassword=$("[name=userPassword]").val();
            var userRemi=$("[name=userRemi]").val();
            var sex=$("[name=gender]").val();
            var date=$("[name=birthday]").val();
            var userphone=$("[name=phone]").val();
            var userAddress=$("[name=address]").val();
            var userRole=$("[name=userRole]:checked").val();
            var mess="";
            if(userCode.length<=0){
                mess="请输入用户编码";
            }
            if(userName.length<=0){
                mess="请输入用户名称";
            }
            if(userpassword.length<=0){
                mess="请输入密码";
            }
            if(userRemi.length<=0){
                mess="请输入确认密码";
            }
            if(userRemi==userpassword){
                if(!(userpassword.length>=6&&userpassword.length<=20)){
                    mess="密码长度必须大于6位小于20位";
                }
            }else{
                mess="两次密码不一致";

            }
            if(sex==""){
                mess="请选择性别";
            }
            if($("[name=date]").val()==null){
                mess="请输入出生日期";
            }
            if(userphone.length<=0){
                mess="请输入用户电话";
            }
            if(userAddress.length<=0){
                mess="请输入用户地址";
            }
            if(userRole==undefined){
                mess="请选择用户类型";
            }
            if(mess.length>0){
                alert(mess);
                return false;
            }else {
                return true;
            }
        }
        function save() {
            var flag=check();
            if(flag){
                $("#myForm").submit();
            }
        }

    </script>
</head>
<body>
<!--头部-->
<header class="publicHeader">
    <h1>超市账单管理系统</h1>

    <div class="publicHeaderR">
        <p><span>下午好！</span><span style="color: #fff21b"> Admin</span> , 欢迎你！</p>
        <a href="login.html">退出</a>
    </div>
</header>
<!--时间-->
<section class="publicTime">
    <span id="time">2015年1月1日 11:11  星期一</span>
    <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
</section>
<!--主体内容-->
<section class="publicMian ">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list">
                <li><a href="${pageContext.request.contextPath}/billList.jsp">账单管理</a></li>
                <li ><a href="${pageContext.request.contextPath}/providerList.jsp">供应商管理</a></li>
                <li id="active"><a href="${pageContext.request.contextPath}/userList.jsp">用户管理</a></li>
                <li><a href="${pageContext.request.contextPath}/password.jsp">密码修改</a></li>
                <li><a href="${pageContext.request.contextPath}/login.jsp">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
            <form action="${pageContext.request.contextPath}/addUser" method="post" id="myForm">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="">
                    <label for="userId">用户编码：</label>
                    <input type="text" name="userCode" id="userId"/>
                </div>
                <div>
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName"/>
                </div>
                <div>
                    <label for="userpassword">用户密码：</label>
                    <input type="text" name="userPassword" id="userpassword"/>
                </div>
                <div>
                    <label for="userRemi">确认密码：</label>
                    <input type="text" name="userRemi" id="userRemi"/>
                </div>
                <div>
                    <label >用户性别：</label>
                    <select name="gender">
                        <option value="2">男</option>
                        <option value="1">女</option>
                    </select>
                    <span></span>
                </div>
                <div>
                    <label for="data">出生日期：</label>
                    <input type="date" name="date" id="data" datatype="yyyy-MM-dd"/>
                </div>
                <div>
                    <label for="userphone">用户电话：</label>
                    <input type="text" name="phone" id="userphone"/>
                </div>
                <div>
                    <label for="userAddress">用户地址：</label>
                    <input type="text" name="address" id="userAddress"/>
                </div>
                <div>
                    <label >用户类别：</label>
                    <input type="radio" name="userRole" value="1"/>管理员
                    <input type="radio" name="userRole" value="2"/>经理
                    <input type="radio" name="userRole" value="3"/>普通用户

                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.html">返回</a>-->
                    <input type="button" value="保存" onclick="save()"/>
                    <input type="button" value="返回" onclick="history.back(-1)"/>
                </div>
            </form>
        </div>

    </div>
</section>
<footer class="footer">
    版权归北大青鸟
</footer>
<script src="js/time.js"></script>

</body>
</html>