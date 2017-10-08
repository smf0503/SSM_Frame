<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript">
        function check() {
            var pwd=$("[name=pwd]").val();
            var oldPwd=$("[name=oldPassword]").val();
            var newPwd=$("[name=newPassword]").val();
            var reNewPwd=$("[name=reNewPassword]").val();
            var mess="";
            if(oldPwd==""){
                mess="请输入旧密码";
            }
            if(pwd!=oldPwd){
                mess="旧密码输入错误,请重新输入";
            }
            if(newPwd==""){
                mess="请输入新密码";
            }
            if(reNewPwd==""){
                mess="请输入确认密码";
            }
            if(newPwd!=reNewPwd){
                mess="两次输入密码不一致";
            }
            if(mess!=""){
                alert(mess);
                return false;
            }
            return true;
        }
        function save() {
            var flag=check();
            if(flag){
                document.forms[0].submit();
            }
        }
    </script>
</head>
<body>
<!--头部-->
    <header class="publicHeader">
        <h1>超市账单管理系统</h1>
        <div class="publicHeaderR">
            <p><span>下午好！</span><span style="color: #fff21b">${getUserName}</span> , 欢迎你！</p>
            <a href="login.jsp">退出</a>
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
                    <li ><a href="billList.jsp">账单管理</a></li>
                    <li><a href="${pageContext.request.contextPath}/ProviderServlet?action=getAllPro">供应商管理</a></li>
                    <li><a href="${pageContext.request.contextPath}/UserServlet?action=getAllUser">用户管理</a></li>
                    <li id="active"><a href="password.jsp">密码修改</a></li>
                    <li><a href="login.jsp">退出系统</a></li>
                </ul>
            </nav>
        </div>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>密码修改页面</span>
            </div>
            <div class="providerAdd">
                <form action="${pageContext.request.contextPath}/UserServlet?action=updatePwd" method="post">
                    <!--div的class 为error是验证错误，ok是验证成功-->
                    <!--当前登录的用户密码-->
                    <input type="hidden" value="${getUserPassword}" name="pwd">
                    <!--当前登录的用户id-->
                    <input type="hidden" value="${getId}" name="getId">
                    <div class="">
                        <label for="oldPassword">旧密码：</label>
                        <input type="password" name="oldPassword" id="oldPassword" required/>
                    </div>
                    <div>
                        <label for="newPassword">新密码：</label>
                        <input type="password" name="newPassword" id="newPassword" required/>
                    </div>
                    <div>
                        <label for="reNewPassword">确认新密码：</label>
                        <input type="password" name="reNewPassword" id="reNewPassword" required/>
                    </div>
                    <div class="providerAddBtn">
                        <!--<a href="#">保存</a>-->
                        <input type="button" value="保存" onclick="save()"/>
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