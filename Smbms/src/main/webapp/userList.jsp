<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css"/>--%>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/easyUI/themes/icon.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/easyUI/themes/default/easyui.css"/>

    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>--%>

    <script type="text/javascript" src="${pageContext.request.contextPath}/easyUI/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyUI/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyUI/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
        var url="";
        function load() {
            $('#test').datagrid({
                title:'用户管理',     //布局的标题名称
                iconCls:'icon-save',  //图标样式
                width:'100%',
                height:450,
                nowrap: true,
                striped: true,
                url:"${pageContext.request.contextPath}/userList?userName="+$("[name=uname]").val(),
                sortName: 'code',
                sortOrder: 'desc',
                pageSize:2,
                idField:'id',  //指示哪个字段是标识字段。
                pageList: [2, 5, 10, 15],
                frozenColumns:[[
                    {field:'ck',checkbox:true},  //控制复选框是否显示
                  /*  { field: 'id', width: 50, hidden: true },*/
                    {title:'用户编码',field:'userCode',width:80,sortable:true}
                ]],
                columns:[[ //Column是一个数组对象，它的每个元素也是一个数组。元素数组的元素是一个配置对象，它定义了每个列的字段。
                    {field:'userName',title:'用户名称',width:80}, //title 标题文本
                    {field:'gender',title:'性别',width:50,  //field：列的字段名
                        formatter:function(value){
                            if(value==2){
                                return "男";
                            }else{
                                return "女";
                            }
                        }
                    },
                    {field:'birthday',title:'年龄',width:50,
                        formatter:function(value){
                            return jsGetAge(value);
                        }
                    },
                    {field:'phone',title:'电话',width:100},
                    {field:'address',title:'地址',width:180},
                    {field:'userRole',title:'用户类型',width:200,
                        formatter:function(value){
                            if(value==1){
                                return "系统管理员";
                            }else if(value==2){
                                return "经理";
                            }else{
                                return "普通员工";
                            }
                        }
                    }
                ]],
                pagination:true, //rows: 每页显示的数据量 page:第几页  显示分页
                rownumbers:true, //带有行号的列
                singleSelect:false,
                toolbar:[{
                    text:'添加',
                    iconCls:'icon-add',
                    handler:function(){
                        openUserAddDialog();
                    }
                },{
                    id: 'btnDelete',
                    text:'批量删除',
                    iconCls:'icon-remove',
                    disabled:false,
                    handler:function(){
                        doDelete();
                    }
                },{
                    text:'修改',
                    iconCls:'icon-edit',
                    disabled:false,
                    handler:function(){
                        openUserModifyDialog();
                    }
                }]
            });
        }
        $(function () {
            load();
            $('#add').dialog({
                title:'添加用户',
                collapsible:false,
                resizable:true,
                //小弹层的OK
                buttons:[{
                    text:'保存',
                    iconCls:'icon-ok',
                    handler:function(){
                        saveUser();
                    }
                }, {
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#add').dialog('close');
                    }
                }]
            });
            $('#add').dialog('close');
        })
        var btnServlet=function () {
            load();
            $('#test').datagrid({
                pageNumber:1
            });
        }

        //年龄
        function jsGetAge(strBirthday) {
            var returnAge;
            var strBirthdayArr=strBirthday.split("-");
            var birthYear = strBirthdayArr[0];
            var birthMonth = strBirthdayArr[1];
            var birthDay = strBirthdayArr[2];
            var d = new Date();
            var nowYear = d.getFullYear();
            var nowMonth = d.getMonth() + 1;
            var nowDay = d.getDate();
            if(nowYear == birthYear){
                returnAge = 0;//同年 则为0岁
            }else{
                var ageDiff = nowYear - birthYear ; //年之差
                if(ageDiff > 0){
                    if(nowMonth == birthMonth){
                        var dayDiff = nowDay - birthDay;//日之差
                        if(dayDiff < 0){
                            returnAge = ageDiff - 1;
                        }else{
                            returnAge = ageDiff ;
                        }
                    }else{
                        var monthDiff = nowMonth - birthMonth;//月之差
                        if(monthDiff < 0){
                            returnAge = ageDiff - 1;
                        } else{
                            returnAge = ageDiff ;
                        }
                    } }else{
                    returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
                }
            }
            return returnAge;//返回周岁年龄
        }

        //添加用户信息
        function openUserAddDialog() {
            $('#add').dialog('open');
            url = "${pageContext.request.contextPath}/addUser";
        }

        //删除用户信息
        function  doDelete() {
            var selectRows = $("#test").datagrid("getSelections");   //选中行
            if (selectRows!=0) {
                //提示用户是否真的删除数据
                $.messager.confirm("确认消息", "您确定要删除此信息么？", function (r) {
                    if (r) {
                        var delIds = "";
                        for (var i = 0; i < selectRows.length; i++) {
                            delIds += selectRows[i].id + ",";
                        }
                        delIds = delIds.substr(0, delIds.length - 1);
                        alert("strIds---->"+delIds);
                        $.post("/delUser", {ids: delIds}, function (data) {
                            if (data == true) {
                                $("#test").datagrid("reload");  //刷新
                                $("#test").datagrid("clearSelections");
                            } else {
                                $.messager.alert("删除失败", data);
                            }
                        })
                    }
                })
            }else{
                $.messager.alert("小可爱提示","请选择一条要删除的数据！");
                return;
            }
        }

        //修改用户信息
        function openUserModifyDialog(){
            var selectedRows = $("#test").datagrid("getSelections");  //拿到当前选中的行
            if (selectedRows.length!=1){
                $.messager.alert("小可爱提示","请选择一条要修改的数据！");
                return;
            }
            var row = selectedRows[0];
            $("#add").dialog("open").dialog("setTitle","修改用户信息");
            $("#myForm").form("load",row);
            url="/updateUser/"+row.id;
        }

        //保存用户信息
        function saveUser() {
            $.ajax({
                url: url,
                type:"post",
                data:$("#myForm").serialize(),
                success: function (result) {
                    if (result == true) {
                        $.messager.alert("小可爱提示", "保存成功！");
                        resetValue();
                        $("#test").datagrid("reload");
                        $("#add").dialog("close");
                    } else {
                        $.messager.alert("小可爱提示", "保存失败！");
                    }
                }
            })
        }

        //修改后清空输入框内的内容
        function resetValue() {
            $("#userId").val('');
            $("#userName").val('');
            $("#userpassword").val('');
            $("#gender").val('');
            $("#data").val('');
            $("#userphone").val('');
            $("#userAddress").val('');
            $("#roleName").val('');
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
                    <li><a href="billList.html">账单管理</a></li>
                    <li><a href="providerList.html">供应商管理</a></li>
                    <li  id="active"><a href="userList.html">用户管理</a></li>
                    <li><a href="password.html">密码修改</a></li>
                    <li><a href="login.html">退出系统</a></li>
                </ul>
            </nav>
        </div>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>用户管理页面</span>
            </div>
            <div class="search">
                <span>用户名：</span>
                <input type="text" placeholder="请输入用户名" name="uname"/>
                <input type="button" onclick="btnServlet()" value="查询"/>
                <a href="userAdd.jsp">添加用户</a>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0" id="test">

            </table>

            <div class="pagination" id="pagination"></div>
        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%--添加用户--%>
<div id="add" class="providerAdd">
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
            <input type="password" name="userPassword" id="userpassword"/>
        </div>
        <div>
            <label for="userRemi">确认密码：</label>
            <input type="password" name="userRemi" id="userRemi"/>
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
            <label id="roleName">用户类别：</label>
            <input type="radio" name="userRole" value="1"/>管理员
            <input type="radio" name="userRole" value="2"/>经理
            <input type="radio" name="userRole" value="3"/>普通用户
        </div>
    </form>
</div>

    <footer class="footer">
        版权归北大青鸟
    </footer>

</body>
</html>