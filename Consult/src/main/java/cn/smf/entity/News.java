package cn.smf.entity;

public class News {
    private Long newsid;

    private String newstitle;

    private String newscontene;

    private Long clickcount;

    public Long getNewsid() {
        return newsid;
    }

    public void setNewsid(Long newsid) {
        this.newsid = newsid;
    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle == null ? null : newstitle.trim();
    }

    public String getNewscontene() {
        return newscontene;
    }

    public void setNewscontene(String newscontene) {
        this.newscontene = newscontene == null ? null : newscontene.trim();
    }

    public Long getClickcount() {
        return clickcount;
    }

    public void setClickcount(Long clickcount) {
        this.clickcount = clickcount;
    }
}