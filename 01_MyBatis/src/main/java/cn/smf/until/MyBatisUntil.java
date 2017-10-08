package cn.smf.until;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ASUS on 2017/9/16.
 */
public class MyBatisUntil {
    static String resource="mybatis-config.xml";
    static InputStream is;
    static SqlSessionFactory factory;
        static {
        try {
                //将硬盘上的一个xml文件变成一个输入流
                is= Resources.getResourceAsStream(resource);
                //使用流对象作为参数创建一个会话工厂
                factory=new SqlSessionFactoryBuilder().build(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
    };
    public static SqlSession getSession(){
        //通过会话工厂创建会话对象 session就是程序员和数据库交互的入口
        return factory.openSession(true);
    }
}
