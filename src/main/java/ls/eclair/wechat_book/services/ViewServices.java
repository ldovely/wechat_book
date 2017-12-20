package ls.eclair.wechat_book.services;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author lisbo
 * @description
 * @since 2017-12-7 15:43
 */

@Service
public class ViewServices {

    //private static final String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=https%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base&state=123#wechat_redirect";

    public String  viewClick(){
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdbc602bd584c5a5a&redirect_uri=";
        String redirect_url ="http://120.79.21.202/index.html";

        try {
            url += URLEncoder.encode(redirect_url,"UTF-8");
            url += "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(url);
        return url;
    }
}
