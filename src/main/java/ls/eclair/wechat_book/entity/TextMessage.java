package ls.eclair.wechat_book.entity;

/**
 * @author lisbo
 * @description
 * @since 2017-12-7 15:23
 */


public class TextMessage extends BaseMessage {

    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
