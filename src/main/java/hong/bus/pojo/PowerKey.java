package hong.bus.pojo;

public class PowerKey {
    private Long pkId;

    private String powerCode;

    public PowerKey(Long pkId, String powerCode) {
        this.pkId = pkId;
        this.powerCode = powerCode;
    }

    public PowerKey() {
        super();
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getPowerCode() {
        return powerCode;
    }

    public void setPowerCode(String powerCode) {
        this.powerCode = powerCode == null ? null : powerCode.trim();
    }
}