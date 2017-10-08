<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript">
        //"上一页"按钮的单击事件
        $("[name=lastPage]").click(function () {
            var paths="${pageContext.request.contextPath }/NewsServlet?action=null&pageIndex=${page.pageIndex-1}";
            location.href=paths;
        })

        //"下一页"按钮的单击事件
        $("[name=nextPage]").click(function () {
            var paths="${pageContext.request.contextPath }/NewsServlet?action=null&pageIndex=${page.pageIndex+1}";
            location.href=paths;
        })
    </script>
</head>
<body>
    <form action="" method="post">
        <table border="1">
            <tr>
                <td>编号</td>
                <td>标题</td>
                <td>访问量</td>
                <td>评论次数</td>
            </tr>

            <c:forEach items="${page.lise}" var="news">
                <tr>
                    <td>${news.newsid}</td>
                    <td><a href="#">${news.newstitle}</a></td>
                    <td>${news.newscontene}</td>
                    <td>${news.clickcount}</td>
                </tr>
            </c:forEach>
        </table>
        <input type="text" value="${page.pageIndex }" name="page">&nbsp;&nbsp;</br>
        <input type="button" value="上一页" name="lastPage">&nbsp;</br>
        <input type="button" value="下一页" name="nextPage">
    </form>
</body>
</html>
