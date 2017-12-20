package ls.eclair.wechat_book.common.utils;

import ls.eclair.wechat_book.entity.AccessToken;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author lisbo
 * @description
 * @since 2017-12-6 18:03
 */

@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        // 第三方用户唯一凭证
        String appId = "wxdbc602bd584c5a5a";
        // 第三方用户唯一凭证密钥
        String appSecret = "c6c1ed639ce4ef702f67e1c79a354d17";

       // 调用接口获取access_token
        AccessToken at = WeixinUtils.getAccessToken(appId , appSecret);

        if (null != at) {
            // 调用接口创建菜单
            int result = WeixinUtils.createMenu(MenuCreateUtil.getMenu() , at.getToken());

            // 判断菜单创建结果
            if (0 == result) {
                System.out.println("菜单创建成功！");
            } else {
                System.out.println("菜单创建失败，错误码：" + result);
            }
        }
    }
}
