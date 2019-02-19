package hong.bus.pojo;

public class RouteKey {
    private Long pkId;

    private String routeCode;

    public RouteKey(Long pkId, String routeCode) {
        this.pkId = pkId;
        this.routeCode = routeCode;
    }

    public RouteKey() {
        super();
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode == null ? null : routeCode.trim();
    }
}