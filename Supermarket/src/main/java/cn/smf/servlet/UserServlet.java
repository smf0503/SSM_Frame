package cn.smf.servlet;

import cn.smf.dao.IUserDao;
import cn.smf.entity.User;
import cn.smf.service.IUserService;
import cn.smf.service.impl.UserServiceImpl;
import cn.smf.until.AgeByBirthday;
import cn.smf.until.MyBatisUntil;
import cn.smf.until.UserPage;
import org.apache.ibatis.session.SqlSession;

import javax.jws.soap.SOAPBinding;
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
import java.util.Map;

/**
 * Created by ASUS on 2017/9/19.
 */

public class UserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决乱码
        request.setCharacterEncoding("utf-8");
        //获取action的值
        String action=request.getParameter("action");
        System.out.println("action------------------"+action);
        IUserService ius=new UserServiceImpl();
        if("login".equals(action)){  //登录
            String name=request.getParameter("username");
            String pwd=request.getParameter("password");
            User user=new User();
            user.setUserCode(name);
            user.setUserPassword(pwd);
            User login = ius.login(user);
            if (login!=null){
                //当前登录的用户密码
                request.getSession().setAttribute("getUserPassword",login.getUserPassword());
                //当前登录的用户id
                request.getSession().setAttribute("getId",login.getId());
                //当前登录的用户名称
                request.getSession().setAttribute("getUserName",login.getUserName());
                request.getRequestDispatcher("/welcome.jsp").forward(request,response);
            }else{
                response.sendRedirect("/login.jsp");
            }
        }else if ("getAllUser".equals(action)){  //查询所有用户分页
            User user=null;
            UserPage page=new UserPage();
            //每页显示的记录数
            int size=1;
            //总记录数
            int totalRecords;
            String temp = request.getParameter("fuzzyName");
            String fuzzyName=null;
            if (temp==null||temp.equals("")){
                size=2;
                totalRecords=ius.getTotalCount(temp);
            }else{
                fuzzyName=new String(temp.getBytes("ISO-8859-1"),"utf-8");
                request.setAttribute("fuzzyName",fuzzyName);
                size=1;
                totalRecords=ius.getTotalCount(fuzzyName);
            }
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
            List<User> allUser=null;
            if (fuzzyName==null||fuzzyName.equals("")){
                allUser = ius.getAllUser((index-1)*size,size);
            }else{
                allUser = ius.fuzzyQuery(fuzzyName,(index-1)*size,size);
            }
            convertAge(allUser);
            page.setList(allUser);
            request.getSession().setAttribute("uname",fuzzyName);
            request.setAttribute("page",page);
            request.getRequestDispatcher("/userList.jsp").forward(request,response);
        }else if("addUser".equals(action)){  //添加用户
            String userCode=request.getParameter("userCode");
            String userName = request.getParameter("userName");
            String userpassword = request.getParameter("userpassword");
            String sex = request.getParameter("sex");
            String temp = request.getParameter("date");
            String userphone = request.getParameter("userphone");
            String userAddress = request.getParameter("userAddress");
            String userleio = request.getParameter("userlei");
            //把字符串转换成date类型
            Date date=convertDate(temp);
            //声明User对象
            User user=new User();
            user.setUserCode(userCode);
            user.setUserName(userName);
            user.setUserPassword(userpassword);
            user.setGender(Integer.parseInt(sex));
            user.setBirthday(date);
            user.setPhone(userphone);
            user.setAddress(userAddress);
            user.setUserRole(Integer.parseInt(userleio));
            //调用添加的方法
            int count = ius.addUser(user);
            if (count>0){
                request.getRequestDispatcher("/UserServlet?action=getAllUser").forward(request,response);
            }
        }else if("updateUser".equals(action)){  //根据id获取用户信息,并且展示到用户修改页面
            //接收从userList页面传过来的用户id
            int id = Integer.parseInt(request.getParameter("id"));
            User info = ius.getInfoById(id);
            //把日期转换成字符串类型
            Date temp=new Date();
            SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
            String date="";
            temp= info.getBirthday();
            date = sim.format(temp);

            request.setAttribute("date",date);
            request.setAttribute("user",info);
            request.getRequestDispatcher("/userUpdate.jsp").forward(request,response);
        }else if ("updateUserInfo".equals(action)){  //修改用户信息
            String userId = request.getParameter("userId");
            String userName=request.getParameter("userName");
            String sex=request.getParameter("sex");
            String temp=request.getParameter("date");
            String userphone=request.getParameter("userphone");
            String userAddress=request.getParameter("userAddress");
            String userlei=request.getParameter("userlei");
            //把字符串转换成date类型
            Date date=convertDate(temp);
            User user=new User();
            user.setId(Integer.parseInt(userId));
            user.setUserName(userName);
            user.setGender(Integer.parseInt(sex));
            user.setBirthday(date);
            user.setPhone(userphone);
            user.setAddress(userAddress);
            user.setUserRole(Integer.parseInt(userlei));
            int count = ius.updateUser(user);
            if (count>0){
                request.getRequestDispatcher("/UserServlet?action=getAllUser").forward(request,response);
            }
        }else if ("delUser".equals(action)){  //删除用户
            //拿到jsp页面传过来的delId
            int delId = Integer.parseInt(request.getParameter("delId"));
            int count = ius.delUser(delId);
            if (count>0){
                request.getRequestDispatcher("/UserServlet?action=getAllUser").forward(request,response);
            }
        }else if ("viewUser".equals(action)){   //查看用户信息
            int viewId= Integer.parseInt(request.getParameter("id"));
            User info = ius.getInfoById(viewId);
            request.setAttribute("info",info);
            request.getRequestDispatcher("/userView.jsp").forward(request,response);
        }else if ("updatePwd".equals(action)){  //修改密码
            //拿到新密码
            String newPassword = request.getParameter("newPassword");
            //拿到当前登录的用户id
            int id = Integer.parseInt(request.getParameter("getId"));
            User user=new User();
            user.setUserPassword(newPassword);
            user.setId(id);
            int count = ius.updateUser(user);
            if (count>0){
                //修改密码成功后回到登录页面,请重新登录
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    //转换年龄
    public void convertAge(List<User> allUser){
        for(User u:allUser){
            Date date=u.getBirthday();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = dateFormat.format(date);
            try {
                int getagee = AgeByBirthday.getagee(format);
                u.setAge(getagee);
            } catch (Exception e) {
            }
        }
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
    public String convertString(List<User> user1){
        Date temp=new Date();
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        String date="";
        for (User u:user1){
            temp=u.getBirthday();
            date = sim.format(temp);
        }
        return date;
    }


}
