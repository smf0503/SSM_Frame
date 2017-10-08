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
        //检查信息是否填写完整
        function check() {
            var providerId=$("[name=providerId]").val();
            var providerName=$("[name=providerName]").val();
            var people=$("[name=people]").val();
            var phone=$("[name=phone]").val();
            var address=$("[name=address]").val();
            var fax=$("[name=fax]").val();
            var describe=$("[name=describe]").val();
            var mess="";
            if(providerId==""){
                mess="请输入供应商编码";
            }
            if(providerName==""){
                mess="请输入供应商名称";
            }
            if(people==""){
                mess="请输入联系人";
            }
            if(phone==""){
                mess="请输入联系电话";
            }else{
                if (phone.length!=11){
                    mess="联系电话必须是11位";
                }
            }
            if(address==""){
                mess="请输入联系地址";
            }
            if(fax==""){
                mess="请输入传真";
            }
            if(describe==""){
                mess="请输入描述";
            }
            if(mess!=""){
                alert(mess);
                return false;
            }else {
                return true;
            }
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
                <li><a href="billList.jsp">账单管理</a></li>
                <li id="active"><a href="${pageContext.request.contextPath}/ProviderServlet?action=getAllPro">供应商管理</a></li>
                <li><a href="${pageContext.request.contextPath}/UserServlet?action=getAllUser">用户管理</a></li>
                <li><a href="password.jsp">密码修改</a></li>
                <li><a href="login.jsp">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面 >> 供应商添加页面</span>
        </div>
        <div class="providerAdd">
            <form action="${pageContext.request.contextPath}/ProviderServlet?action=addPro" method="post">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="">
                    <label for="providerId">供应商编码：</label>
                    <input type="text" name="providerId" id="providerId"/>
                </div>
                <div>
                    <label for="providerName">供应商名称：</label>
                    <input type="text" name="providerName" id="providerName"/>
                </div>
                <div>
                    <label for="people">联系人：</label>
                    <input type="text" name="people" id="people"/>

                </div>
                <div>
                    <label for="phone">联系电话：</label>
                    <input type="text" name="phone" id="phone"/>
                </div>
                <div>
                    <label for="address">联系地址：</label>
                    <input type="text" name="address" id="address"/>
                    <span></span>
                </div>
                <div>
                    <label for="fax">传真：</label>
                    <input type="text" name="fax" id="fax"/>
                    <span></span>
                </div>
                <div>
                    <label for="describe">描述：</label>
                    <input type="text" name="describe" id="describe"/>
                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="providerList.jsp">返回</a>-->
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