package hong.bus.pojo;

public class DriverKey {
    private Long pkId;

    private String driverCode;

    public DriverKey(Long pkId, String driverCode) {
        this.pkId = pkId;
        this.driverCode = driverCode;
    }

    public DriverKey() {
        super();
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getDriverCode() {
        return driverCode;
    }

    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode == null ? null : driverCode.trim();
    }
}