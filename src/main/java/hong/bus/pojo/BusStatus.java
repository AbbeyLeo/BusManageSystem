package hong.bus.pojo;

public class BusStatus extends BusStatusKey {
    private String status;

    private Integer dr;

    private String ts;

    private String creater;

    private String createTime;

    private String modifier;

    private String modifyTime;

    public BusStatus(Long pkId, String busId, String status, Integer dr, String ts, String creater, String createTime, String modifier, String modifyTime) {
        super(pkId, busId);
        this.status = status;
        this.dr = dr;
        this.ts = ts;
        this.creater = creater;
        this.createTime = createTime;
        this.modifier = modifier;
        this.modifyTime = modifyTime;
    }

    public BusStatus() {
        super();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts == null ? null : ts.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime == null ? null : modifyTime.trim();
    }
}