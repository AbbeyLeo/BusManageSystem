package hong.bus.pojo;

public class BusStatusKey {
    private Long pkId;

    private String busId;

    public BusStatusKey(Long pkId, String busId) {
        this.pkId = pkId;
        this.busId = busId;
    }

    public BusStatusKey() {
        super();
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId == null ? null : busId.trim();
    }
}