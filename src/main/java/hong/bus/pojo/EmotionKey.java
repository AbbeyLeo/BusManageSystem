package hong.bus.pojo;

public class EmotionKey {
    private Long pkId;

    private String emotionCode;

    public EmotionKey(Long pkId, String emotionCode) {
        this.pkId = pkId;
        this.emotionCode = emotionCode;
    }

    public EmotionKey() {
        super();
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getEmotionCode() {
        return emotionCode;
    }

    public void setEmotionCode(String emotionCode) {
        this.emotionCode = emotionCode == null ? null : emotionCode.trim();
    }
}