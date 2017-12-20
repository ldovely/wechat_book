package ls.eclair.wechat_book.entity;

/**
 * @author lisbo
 * @description
 * @since 2017-12-6 17:16
 */


public class CommonButton extends Button {
    private static final long serialVersionUID = 8727021479582750411L;

    private String type;
    private String key;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
