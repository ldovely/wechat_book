package ls.eclair.wechat_book.controller;

import ls.eclair.wechat_book.common.utils.XmlParse;
import ls.eclair.wechat_book.config.Messages;
import ls.eclair.wechat_book.services.ViewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;

/**
 * @author lisbo
 * @description
 * @since 2017-12-6 16:04
 */

@EnableAutoConfiguration
@RestController
public class HelloController {

    @Autowired
    private ViewServices viewServices;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @RequestMapping(value = "/token",method= RequestMethod.GET)
    public String tokenCheck(HttpServletRequest request, HttpServletResponse response){
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        return echostr;
    }

    @RequestMapping(value = "/token",method= RequestMethod.POST)
    public String doPost(HttpServletRequest request, HttpServletResponse response){
        String event = "Event";
        try {
            Map<String, String> requestMap = XmlParse.parseXML(request);

            event = requestMap.get("Event");
            if (Objects.equals(event,Messages.EVENT_TYPE_CLICK)){
                return event;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return event;
    }
}
