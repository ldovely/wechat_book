package ls.eclair.wechat_book.controller;

import ls.eclair.wechat_book.common.utils.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lisbo
 * @description
 * @since 2017-12-6 16:48
 */

@Controller
@EnableAutoConfiguration
public class MenuController {

    private static Logger log = LoggerFactory.getLogger(MenuController.class);

    @RequestMapping(value = "/makeBook",method = RequestMethod.GET)
    public String makeBook(HttpServletRequest request, HttpServletResponse response){
        String url = "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdbc602bd584c5a5a&redirect_uri=zxing.vip/oauth?response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect";

        return url;
    }

    @RequestMapping(value = "/oauth", method = RequestMethod.GET)
    public String weixinOAuth(HttpServletRequest request, HttpServletResponse response, Model model) {
        //得到code
        String CODE = request.getParameter("code");
        log.error("code:"+CODE);
        String APPID = "wxdbc602bd584c5a5a";
        String SECRET = "c6c1ed639ce4ef702f67e1c79a354d17";
        //换取access_token 其中包含了openid
        String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code".replace("APPID", APPID).replace("SECRET", SECRET).replace("CODE", CODE);
        //URLConnectionHelper是一个模拟发送http请求的类
        String jsonStr = HttpRequest.sendGet(URL,null);
        //System.out.println(jsonStr);
        //out.print(jsonStr);
        //JSONObject jsonObj = new JSONObject(jsonStr);
        log.error("jsonStr:"+jsonStr);
        //String openid = jsonObj.get("openid").toString();
        //有了用户的opendi就可以的到用户的信息了
        //地址为https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
        //得到用户信息之后返回到一个页面
        //model.addAttribute("user", wechatUser);
        return jsonStr;
    }
}
