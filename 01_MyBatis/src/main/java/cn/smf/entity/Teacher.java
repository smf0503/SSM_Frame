package cn.smf.entity;

import java.util.List;

/**
 * Created by ASUS on 2017/9/23.
 */
public class Teacher {
    private Integer tid;
    private String tname;
    private List<Student> stus;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public List<Student> getStus() {
        return stus;
    }

    public void setStus(List<Student> stus) {
        this.stus = stus;
    }
}
