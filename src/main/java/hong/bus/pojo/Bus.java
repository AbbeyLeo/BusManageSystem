package hong.bus.pojo;

public class Bus extends BusKey {
    private String busName;

    private String busRouteCode;

    private String busRoute;

    private String busPlateNumber;

    private String busCreateTime;

    private String busProducer;

    private Integer busSize;

    private String busFuel;

    private String creator;

    private String createTime;

    private String modifier;

    private String modifyTime;

    private String ts;

    private Integer dr;

    private String canId;

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

    public Bus(Long pkId, String busId, String busName, String busRouteCode, String busRoute, String busPlateNumber, String busCreateTime, String busProducer, Integer busSize, String busFuel, String creator, String createTime, String modifier, String modifyTime, String ts, Integer dr, String canId, String def01, String def02, String def03, String def04, String def05, String def06, String def07, String def08, String def09, String def10) {
        super(pkId, busId);
        this.busName = busName;
        this.busRouteCode = busRouteCode;
        this.busRoute = busRoute;
        this.busPlateNumber = busPlateNumber;
        this.busCreateTime = busCreateTime;
        this.busProducer = busProducer;
        this.busSize = busSize;
        this.busFuel = busFuel;
        this.creator = creator;
        this.createTime = createTime;
        this.modifier = modifier;
        this.modifyTime = modifyTime;
        this.ts = ts;
        this.dr = dr;
        this.canId = canId;
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

    public Bus() {
        super();
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName == null ? null : busName.trim();
    }

    public String getBusRouteCode() {
        return busRouteCode;
    }

    public void setBusRouteCode(String busRouteCode) {
        this.busRouteCode = busRouteCode == null ? null : busRouteCode.trim();
    }

    public String getBusRoute() {
        return busRoute;
    }

    public void setBusRoute(String busRoute) {
        this.busRoute = busRoute == null ? null : busRoute.trim();
    }

    public String getBusPlateNumber() {
        return busPlateNumber;
    }

    public void setBusPlateNumber(String busPlateNumber) {
        this.busPlateNumber = busPlateNumber == null ? null : busPlateNumber.trim();
    }

    public String getBusCreateTime() {
        return busCreateTime;
    }

    public void setBusCreateTime(String busCreateTime) {
        this.busCreateTime = busCreateTime == null ? null : busCreateTime.trim();
    }

    public String getBusProducer() {
        return busProducer;
    }

    public void setBusProducer(String busProducer) {
        this.busProducer = busProducer == null ? null : busProducer.trim();
    }

    public Integer getBusSize() {
        return busSize;
    }

    public void setBusSize(Integer busSize) {
        this.busSize = busSize;
    }

    public String getBusFuel() {
        return busFuel;
    }

    public void setBusFuel(String busFuel) {
        this.busFuel = busFuel == null ? null : busFuel.trim();
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

    public String getCanId() {
        return canId;
    }

    public void setCanId(String canId) {
        this.canId = canId == null ? null : canId.trim();
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