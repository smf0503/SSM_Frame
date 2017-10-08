package cn.smf.servlet;

import cn.smf.entity.News;
import cn.smf.service.INewsService;
import cn.smf.service.impl.NewsServiceImpl;
import cn.smf.until.NewsPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by ASUS on 2017/9/26.
 */
public class NewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决乱码
        request.setCharacterEncoding("utf-8");
        //获取action的值
        String action=request.getParameter("action");
        System.out.println("action------------------"+action);
        INewsService ins=new NewsServiceImpl();
        if ("".equals(action)||action==null){
            System.out.println("进入----------->");
            NewsPage page=new NewsPage();
            //每页显示的记录数
            int size=2;
            //总记录数
            int totalRecords;
            totalRecords=ins.getTotalCount();
            System.out.println("totalRecords----------->"+totalRecords);
            //总页数
            int totalPages=totalRecords%size==0?totalRecords/size:totalRecords/size+1;
            //当前页码
            int index=1;
            if(request.getParameter("pageIndex")!=null&&request.getParameter("pageIndex")!=""){
                index=Integer.parseInt(request.getParameter("pageIndex"));
            }
            if(index>=totalPages){
                index=totalPages;
            }
            if(index<=0){
                index=1;
            }
            page.setPageSize(size);
            page.setTotalRecords(totalRecords);
            page.setTotalPages(totalPages);
            page.setPageIndex(index);
            List<News> allNews=null;
            allNews=ins.getAllNews((index-1)*size,size);
            page.setList(allNews);
            request.setAttribute("page",page);
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
