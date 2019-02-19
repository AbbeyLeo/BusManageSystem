package hong.bus.pojo;

public class MalfunctionKey {
    private Long pkId;

    private String malfunctionCode;

    public MalfunctionKey(Long pkId, String malfunctionCode) {
        this.pkId = pkId;
        this.malfunctionCode = malfunctionCode;
    }

    public MalfunctionKey() {
        super();
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getMalfunctionCode() {
        return malfunctionCode;
    }

    public void setMalfunctionCode(String malfunctionCode) {
        this.malfunctionCode = malfunctionCode == null ? null : malfunctionCode.trim();
    }
}