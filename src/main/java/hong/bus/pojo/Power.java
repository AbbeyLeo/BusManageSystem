package hong.bus.pojo;

public class Power extends PowerKey {
    private String powerName;

    private String powerDescription;

    private String creator;

    private String createTime;

    private String modifier;

    private String modifyTime;

    private String ts;

    private Integer dr;

    public Power(Long pkId, String powerCode, String powerName, String powerDescription, String creator, String createTime, String modifier, String modifyTime, String ts, Integer dr) {
        super(pkId, powerCode);
        this.powerName = powerName;
        this.powerDescription = powerDescription;
        this.creator = creator;
        this.createTime = createTime;
        this.modifier = modifier;
        this.modifyTime = modifyTime;
        this.ts = ts;
        this.dr = dr;
    }

    public Power() {
        super();
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName == null ? null : powerName.trim();
    }

    public String getPowerDescription() {
        return powerDescription;
    }

    public void setPowerDescription(String powerDescription) {
        this.powerDescription = powerDescription == null ? null : powerDescription.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
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

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts == null ? null : ts.trim();
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }
}