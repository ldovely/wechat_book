package ls.eclair.wechat_book.entity;

/**
 * @author lisbo
 * @description
 * @since 2017-12-7 15:20
 */


public class ImageMessage extends BaseMessage {

    private Image Image;

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }
}
