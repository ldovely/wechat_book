package ls.eclair.wechat_book.entity;

import java.io.Serializable;

/**
 * @author lisbo
 * @description
 * @since 2017-12-6 17:03
 */

public class Button implements Serializable {
    private static final long serialVersionUID = 6450990258340542165L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
