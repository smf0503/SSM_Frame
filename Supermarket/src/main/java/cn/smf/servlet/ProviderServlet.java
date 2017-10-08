package cn.smf.servlet;
import cn.smf.entity.Provider;
import cn.smf.service.IProviderService;
import cn.smf.service.impl.ProviderServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2017/9/21.
 */
@WebServlet(name = "ProviderServlet",urlPatterns = {"/ProviderServlet"})
public class ProviderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决乱码
        request.setCharacterEncoding("utf-8");
        //获取action的值
        String action = request.getParameter("action");
        System.out.println("action------------------"+action);
        IProviderService ipro=new ProviderServiceImpl();
        if("getAllPro".equals(action)){  //查询所有供应商信息
            Provider pro=null;
            List<Provider> allPro = ipro.getAllPro(pro);
            request.setAttribute("allPro",allPro);
            request.getRequestDispatcher("/providerList.jsp").forward(request,response);
        }else if("fuzzyQuery".equals(action)){  //模糊查询
            String temp = request.getParameter("fuzzyName");
            String fuzzyName=new String(temp.getBytes("iso-8859-1"),"utf-8");
            Provider p=new Provider();
            p.setProName(fuzzyName);
            List<Provider> fuzzyQuery = ipro.getAllPro(p);
            request.setAttribute("allPro",fuzzyQuery);
            request.getRequestDispatcher("/providerList.jsp").forward(request,response);
        }else if("addPro".equals(action)){  //添加供应商
            String providerId = request.getParameter("providerId");
            String providerName = request.getParameter("providerName");
            String people = request.getParameter("people");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String fax = request.getParameter("fax");
            String describe = request.getParameter("describe");
            Date date=new Date();
            Provider p=new Provider();
            p.setProCode(providerId);
            p.setProName(providerName);
            p.setProContact(people);
            p.setProPhone(phone);
            p.setProAddress(address);
            p.setProFax(fax);
            p.setProDesc(describe);
            p.setCreationDate(date);
            int count = ipro.addPro(p);
            if (count>0){
                request.getRequestDispatcher("/ProviderServlet?action=getAllPro").forward(request,response);
            }
        }else if ("viewPro".equals(action)){   //查看供应商信息
            int viewId= Integer.parseInt(request.getParameter("id"));
            System.out.println("viewId---------->"+viewId);
            Provider p=new Provider();
            p.setId(viewId);
            List<Provider> viewPro = ipro.getAllPro(p);
            request.setAttribute("viewPro",viewPro);
            request.getRequestDispatcher("/providerView.jsp").forward(request,response);
        }else if ("updatePro".equals(action)){    //根据id获取供应商信息,并且展示到供应商修改页面
            //接收从providerList页面传过来的用户id
            int id = Integer.parseInt(request.getParameter("id"));
            Provider p=new Provider();
            p.setId(id);
            List<Provider> proInfo = ipro.getAllPro(p);
            request.setAttribute("proInfo",proInfo);
            request.getRequestDispatcher("providerUpdate.jsp").forward(request,response);
        }else if ("updateProInfo".equals(action)){   //修改供应商信息
            int id = Integer.parseInt(request.getParameter("id"));
            String providerId = request.getParameter("providerId");
            String providerName = request.getParameter("providerName");
            String people = request.getParameter("people");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String fax = request.getParameter("fax");
            String describe = request.getParameter("describe");
            Provider p=new Provider();
            p.setId(id);
            p.setProCode(providerId);
            p.setProName(providerName);
            p.setProContact(people);
            p.setProPhone(phone);
            p.setProAddress(address);
            p.setProFax(fax);
            p.setProDesc(describe);
            int count = ipro.updateProInfo(p);
            if (count>0){
                request.getRequestDispatcher("/ProviderServlet?action=getAllPro").forward(request,response);
            }
        }else if ("delPro".equals(action)){  //删除供应商
            //拿到jsp页面传过来的delId
            int delId = Integer.parseInt(request.getParameter("delId"));
            int count = ipro.delPro(delId);
            if(count>0){
                request.getRequestDispatcher("/ProviderServlet?action=getAllPro").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    //字符串转换成date
    public Date convertDate(String temp){
        Date date=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date=dateFormat.parse(temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    //把日期转换成字符串类型
    public String convertString(List<Provider> pro){
        Date temp=new Date();
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        String date="";
        for (Provider p:pro){
            temp=p.getCreationDate();
            date = sim.format(temp);
        }
        return date;
    }

}
