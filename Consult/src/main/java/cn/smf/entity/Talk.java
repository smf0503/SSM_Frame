package cn.smf.entity;

import java.util.Date;

public class Talk {
    private Long tid;

    private String content;

    private Date talktime;

    private Long nid;

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getTalktime() {
        return talktime;
    }

    public void setTalktime(Date talktime) {
        this.talktime = talktime;
    }

    public Long getNid() {
        return nid;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }
}