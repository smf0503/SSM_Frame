package cn.smf.entity;

import java.util.List;

/**
 * Created by ASUS on 2017/9/21.
 */
public class GradeNew {
    private String stuno;
    private String subjectname;
    private List<Grade> gradeList;

    public String getStuno() {
        return stuno;
    }

    public void setStuno(String stuno) {
        this.stuno = stuno;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }
}
