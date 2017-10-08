package cn.smf.until;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ASUS on 2017/9/18.
 */
public class MyBatisUntil {
    static final String resource= "mybatis-config.xml";
    static InputStream is;
    static {
        try {
            //将硬盘上的xml文件变成一个输入流
            is= Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    //使用流对象作为参数创建一个会话工厂
    static SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
    public static SqlSession getSession(){
        //通过会化工厂创建会话对象 session就是程序员和数据交互的入口
        SqlSession session = factory.openSession();
        return session;
    }

}
