package cn.smf.entity;

import java.util.List;

/**
 * Created by ASUS on 2017/9/23.
 */
public class Student {
    private Integer sid;
    private String sname;
    private List<Teacher> teas;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public List<Teacher> getTeas() {
        return teas;
    }

    public void setTeas(List<Teacher> teas) {
        this.teas = teas;
    }
}
