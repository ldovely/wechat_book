package ls.eclair.wechat_book.entity;

import java.io.Serializable;

/**
 * @author lisbo
 * @description
 * @since 2017-12-6 17:18
 */


public class Menu implements Serializable {
    private static final long serialVersionUID = -7999551605430887725L;

    private Button[] button;

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }
}
