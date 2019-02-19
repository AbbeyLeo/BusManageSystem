package hong.bus.pojo;

public class StationKey {
    private Long pkId;

    private String stationCode;

    public StationKey(Long pkId, String stationCode) {
        this.pkId = pkId;
        this.stationCode = stationCode;
    }

    public StationKey() {
        super();
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode == null ? null : stationCode.trim();
    }
}