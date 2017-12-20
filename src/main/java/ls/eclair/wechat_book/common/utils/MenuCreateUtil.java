package ls.eclair.wechat_book.common.utils;

import ls.eclair.wechat_book.entity.Button;
import ls.eclair.wechat_book.entity.CommonButton;
import ls.eclair.wechat_book.entity.ComplexButton;
import ls.eclair.wechat_book.entity.Menu;

/**
 * @author lisbo
 * @description
 * @since 2017-12-6 17:09
 */


public class MenuCreateUtil {

    private MenuCreateUtil(){}

    /**
     * 组装菜单数据
     *
     * @return
     */
    public static Menu getMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("微信书");
        btn11.setType("view");
        btn11.setKey("11");
        btn11.setUrl("http://120.79.21.202/makeBook");

        CommonButton btn12 = new CommonButton();
        btn12.setName("了解微信书");
        btn12.setType("view");
        btn12.setKey("12");
        btn12.setUrl("http://120.79.21.202/index.html");

        CommonButton btn13 = new CommonButton();
        btn13.setName("送好友一本");
        btn13.setType("click");
        btn13.setKey("13");


        CommonButton btn21 = new CommonButton();
        btn21.setName("日记");
        btn21.setType("click");
        btn21.setKey("21");

        CommonButton btn22 = new CommonButton();
        btn22.setName("微博");
        btn22.setType("click");
        btn22.setKey("22");

        CommonButton btn23 = new CommonButton();
        btn23.setName("故事");
        btn23.setType("click");
        btn23.setKey("23");

        CommonButton btn24 = new CommonButton();
        btn24.setName("台历");
        btn24.setType("click");
        btn24.setKey("24");


        CommonButton btn31 = new CommonButton();
        btn31.setName("体现");
        btn31.setType("click");
        btn31.setKey("31");

        CommonButton btn32 = new CommonButton();
        btn32.setName("书架");
        btn32.setType("click");
        btn32.setKey("32");

        CommonButton btn33 = new CommonButton();
        btn33.setName("客服");
        btn33.setType("click");
        btn33.setKey("33");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("微信书");
        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13 });

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("更多心书");
        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24 });

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("我的");
        mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33 });

        Menu menu = new Menu();
        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

        return menu;
    }
}
