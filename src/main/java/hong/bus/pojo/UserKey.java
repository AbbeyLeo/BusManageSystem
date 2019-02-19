package hong.bus.pojo;

public class UserKey {
    private Long pkId;

    private String userId;

    public UserKey(Long pkId, String userId) {
        this.pkId = pkId;
        this.userId = userId;
    }

    public UserKey() {
        super();
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}