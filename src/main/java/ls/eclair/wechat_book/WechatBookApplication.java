package ls.eclair.wechat_book;

import ls.eclair.wechat_book.common.utils.MenuCreateUtil;
import ls.eclair.wechat_book.common.utils.WeixinUtils;
import ls.eclair.wechat_book.controller.MenuController;
import ls.eclair.wechat_book.entity.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WechatBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatBookApplication.class , args);

    }
}
