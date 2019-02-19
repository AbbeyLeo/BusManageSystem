package hong.bus.pojo;

public class BusKey {
    private Long pkId;

    private String busId;

    public BusKey(Long pkId, String busId) {
        this.pkId = pkId;
        this.busId = busId;
    }

    public BusKey() {
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