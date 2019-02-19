package hong.bus.pojo;

public class Malfunction extends MalfunctionKey {
    private String malfunctionName;

    private String malfunctionType;

    private String malfunctionBus;

    private String malfunctionDetail;

    private String creator;

    private String createTime;

    private String modifier;

    private String modifyTime;

    private String ts;

    private Integer dr;

    private String def01;

    private String def02;

    private String def03;

    private String def04;

    private String def05;

    private String def06;

    private String def07;

    private String def08;

    private String def09;

    private String def10;

    public Malfunction(Long pkId, String malfunctionCode, String malfunctionName, String malfunctionType, String malfunctionBus, String malfunctionDetail, String creator, String createTime, String modifier, String modifyTime, String ts, Integer dr, String def01, String def02, String def03, String def04, String def05, String def06, String def07, String def08, String def09, String def10) {
        super(pkId, malfunctionCode);
        this.malfunctionName = malfunctionName;
        this.malfunctionType = malfunctionType;
        this.malfunctionBus = malfunctionBus;
        this.malfunctionDetail = malfunctionDetail;
        this.creator = creator;
        this.createTime = createTime;
        this.modifier = modifier;
        this.modifyTime = modifyTime;
        this.ts = ts;
        this.dr = dr;
        this.def01 = def01;
        this.def02 = def02;
        this.def03 = def03;
        this.def04 = def04;
        this.def05 = def05;
        this.def06 = def06;
        this.def07 = def07;
        this.def08 = def08;
        this.def09 = def09;
        this.def10 = def10;
    }

    public Malfunction() {
        super();
    }

    public String getMalfunctionName() {
        return malfunctionName;
    }

    public void setMalfunctionName(String malfunctionName) {
        this.malfunctionName = malfunctionName == null ? null : malfunctionName.trim();
    }

    public String getMalfunctionType() {
        return malfunctionType;
    }

    public void setMalfunctionType(String malfunctionType) {
        this.malfunctionType = malfunctionType == null ? null : malfunctionType.trim();
    }

    public String getMalfunctionBus() {
        return malfunctionBus;
    }

    public void setMalfunctionBus(String malfunctionBus) {
        this.malfunctionBus = malfunctionBus == null ? null : malfunctionBus.trim();
    }

    public String getMalfunctionDetail() {
        return malfunctionDetail;
    }

    public void setMalfunctionDetail(String malfunctionDetail) {
        this.malfunctionDetail = malfunctionDetail == null ? null : malfunctionDetail.trim();
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

    public String getDef01() {
        return def01;
    }

    public void setDef01(String def01) {
        this.def01 = def01 == null ? null : def01.trim();
    }

    public String getDef02() {
        return def02;
    }

    public void setDef02(String def02) {
        this.def02 = def02 == null ? null : def02.trim();
    }

    public String getDef03() {
        return def03;
    }

    public void setDef03(String def03) {
        this.def03 = def03 == null ? null : def03.trim();
    }

    public String getDef04() {
        return def04;
    }

    public void setDef04(String def04) {
        this.def04 = def04 == null ? null : def04.trim();
    }

    public String getDef05() {
        return def05;
    }

    public void setDef05(String def05) {
        this.def05 = def05 == null ? null : def05.trim();
    }

    public String getDef06() {
        return def06;
    }

    public void setDef06(String def06) {
        this.def06 = def06 == null ? null : def06.trim();
    }

    public String getDef07() {
        return def07;
    }

    public void setDef07(String def07) {
        this.def07 = def07 == null ? null : def07.trim();
    }

    public String getDef08() {
        return def08;
    }

    public void setDef08(String def08) {
        this.def08 = def08 == null ? null : def08.trim();
    }

    public String getDef09() {
        return def09;
    }

    public void setDef09(String def09) {
        this.def09 = def09 == null ? null : def09.trim();
    }

    public String getDef10() {
        return def10;
    }

    public void setDef10(String def10) {
        this.def10 = def10 == null ? null : def10.trim();
    }
}