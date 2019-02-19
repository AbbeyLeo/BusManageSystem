package hong.bus.pojo;

public class WeightKey {
    private Long pkId;

    private String weightCode;

    public WeightKey(Long pkId, String weightCode) {
        this.pkId = pkId;
        this.weightCode = weightCode;
    }

    public WeightKey() {
        super();
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode == null ? null : weightCode.trim();
    }
}