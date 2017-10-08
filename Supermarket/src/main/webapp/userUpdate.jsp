<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript">
        function save() {
            document.forms[0].submit();
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
                <li><a href="billList.jsp">账单管理</a></li>
                <li ><a href="${pageContext.request.contextPath}/ProviderServlet?action=getAllPro">供应商管理</a></li>
                <li id="active"><a href="${pageContext.request.contextPath}/UserServlet?action=getAllUser">用户管理</a></li>
                <li><a href="password.jsp">密码修改</a></li>
                <li><a href="login.jsp">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户修改页面</span>
        </div>
        <div class="providerAdd">
            <form action="${pageContext.request.contextPath}/UserServlet?action=updateUserInfo" method="post">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <input type="hidden" name="userId" value="${user.id}">
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName" placeholder="韩露" value="${user.userName}"/>
                </div>

                <div>
                    <label >用户性别：</label>
                    <select name="sex">
                        <c:if test="${user.gender==1}">
                            <option value="1" selected="selected">女</option>
                            <option value="2">男</option>
                        </c:if>
                        <c:if test="${user.gender==2}">
                            <option value="1">女</option>
                            <option value="2" selected="selected">男</option>
                        </c:if>
                    </select>
                </div>
                <div>
                    <label for="data">出生日期：</label>
                    <input type="text" name="date" id="data" placeholder="2016年2月1日" value="${date}"/>
                </div>
                <div>
                    <label for="userphone">用户电话：</label>
                    <input type="text" name="userphone" id="userphone" placeholder="13533667897" value="${user.phone}"/>
                </div>
                <div>
                    <label for="userAddress">用户地址：</label>
                    <input type="text" name="userAddress" id="userAddress" placeholder="北京" value="${user.address}"/>
                </div>
                <div>
                    <label>用户类别：</label>
                    <%--<input type="radio" name="userlei" value="1"/>管理员
                    <input type="radio" name="userlei" value="2"/>经理
                    <input type="radio" name="userlei" value="3"/>普通用户--%>
                    <c:if test="${user.userRole==1}">
                        <input type="radio" name="userlei" checked="checked" value="1"/>管理员
                        <input type="radio" name="userlei" value="2"/>经理
                        <input type="radio" name="userlei" value="3"/>普通用户
                    </c:if>
                    <c:if test="${user.userRole==2}">
                        <input type="radio" name="userlei" value="1"/>管理员
                        <input type="radio" name="userlei" checked="checked" value="2"/>经理
                        <input type="radio" name="userlei" value="3"/>普通用户
                    </c:if>
                    <c:if test="${user.userRole==3}">
                        <input type="radio" name="userlei" value="1"/>管理员
                        <input type="radio" name="userlei" value="2"/>经理
                        <input type="radio" name="userlei" value="3" checked="checked"/>普通用户
                    </c:if>
                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.jsp">返回</a>-->
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