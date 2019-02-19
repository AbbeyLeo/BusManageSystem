package hong.bus.pojo;

public class CanKey {
    private Long pkId;

    private String canId;

    public CanKey(Long pkId, String canId) {
        this.pkId = pkId;
        this.canId = canId;
    }

    public CanKey() {
        super();
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getCanId() {
        return canId;
    }

    public void setCanId(String canId) {
        this.canId = canId == null ? null : canId.trim();
    }
}