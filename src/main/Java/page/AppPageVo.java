package page;

public class AppPageVo<T>  extends PageVo{
    private int appId;
    private int appVersionId;
    private String appUuid ;

    public String getAppUuid() {
        return appUuid;
    }

    public void setAppUuid(String appUuid) {
        this.appUuid = appUuid;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getAppVersionId() {
        return appVersionId;
    }

    public void setAppVersionId(int appVersionId) {
        this.appVersionId = appVersionId;
    }
}
