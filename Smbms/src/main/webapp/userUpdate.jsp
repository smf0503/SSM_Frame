<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
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
                <li><a href="billList.html">账单管理</a></li>
                <li ><a href="providerList.html">供应商管理</a></li>
                <li id="active"><a href="userList.html">用户管理</a></li>
                <li><a href="password.html">密码修改</a></li>
                <li><a href="login.html">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户修改页面</span>
        </div>
        <div class="providerAdd">
            <form action="${pageContext.request.contextPath}/updateUser">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <input type="hidden" name="userId" value="${updateInfo.id}">
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName" placeholder="韩露" value="${updateInfo.userName}"/>
                </div>

                <div>
                    <label >用户性别：</label>
                    <select name="">
                        <c:if test="${updateInfo.gender==1}">
                            <option value="1" selected="selected">女</option>
                            <option value="2">男</option>
                        </c:if>
                        <c:if test="${updateInfo.gender==2}">
                            <option value="1">女</option>
                            <option value="2" selected="selected">男</option>
                        </c:if>
                    </select>
                </div>
                <div>
                    <label for="data">出生日期：</label>
                    <input type="text" name="data" id="data" placeholder="2016年2月1日" value="<fmt:formatDate value="${updateInfo.birthday}" type="date"/>"/>
                </div>
                <div>
                    <label for="userphone">用户电话：</label>
                    <input type="text" name="userphone" id="userphone" placeholder="13533667897" value="${updateInfo.phone}"/>
                </div>
                <div>
                    <label for="userAddress">用户地址：</label>
                    <input type="text" name="userAddress" id="userAddress" placeholder="北京" value="${updateInfo.address}"/>
                </div>
                <div>
                    <label >用户类别：</label>
                    <c:if test="${updateInfo.userRole==1}">
                        <input type="radio" name="userlei" checked="checked" value="1"/>管理员
                        <input type="radio" name="userlei" value="2"/>经理
                        <input type="radio" name="userlei" value="3"/>普通用户
                    </c:if>
                    <c:if test="${updateInfo.userRole==2}">
                        <input type="radio" name="userlei" value="1"/>管理员
                        <input type="radio" name="userlei" checked="checked" value="2"/>经理
                        <input type="radio" name="userlei" value="3"/>普通用户
                    </c:if>
                    <c:if test="${updateInfo.userRole==3}">
                        <input type="radio" name="userlei" value="1"/>管理员
                        <input type="radio" name="userlei" value="2"/>经理
                        <input type="radio" name="userlei" value="3" checked="checked"/>普通用户
                    </c:if>
                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.html">返回</a>-->
                    <input type="button" value="保存" onclick="history.back(-1)"/>
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