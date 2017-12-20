package ls.eclair.wechat_book.common.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import ls.eclair.wechat_book.entity.ImageMessage;
import ls.eclair.wechat_book.entity.TextMessage;
import ls.eclair.wechat_book.entity.VideoMessage;
import ls.eclair.wechat_book.entity.VoiceMessage;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lisbo
 * @description
 * @since 2017-12-7 14:14
 */


public class XmlParse {

    /**
     * 将对象转换成xml
     *
     * @param obj
     *            要转成xml的对象
     * @return xml格式的字符串
     * @throws JAXBException
     * @throws SAXException
     */
    public static String objToXml(Object obj,String xsdPath) throws JAXBException, SAXException {
        StringWriter sw = new StringWriter();
        JAXBContext jAXBContext;
        jAXBContext = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = jAXBContext.createMarshaller();
        marshaller.setListener(new Marshaller.Listener() {
            @Override
            public void beforeMarshal(Object source) {
                super.beforeMarshal(source);

                String BLANK_CHAR = "" ;
                Field[] fields = source.getClass().getDeclaredFields();
                for (Field f : fields) {
                    f.setAccessible(true);
                    //获取字段上注解<pre name="code" class="java">
                    try {
                        if (f.getType() == String.class && f.get(source) == null) {
                            f.set(source, BLANK_CHAR);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        if(xsdPath != null && !xsdPath.isEmpty()){
            // E:\\eclipse_luna\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp4\\webapps\\ggfwpt\\xsd/A06.xsd
            marshaller.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File(xsdPath)));
        }
        marshaller.marshal(obj, sw);
        return sw.toString();
    }

    /**
     * 将xml格式转换为对象
     * @param <T>
     *
     * @param xml
     *            xml字符串
     * @param xsdPath
     *            要转换成的对象
     * @return 转换后的对象
     * @throws Exception
     */
    public static <T> T xmlToObj(String xml, Class<T> clazz,String xsdPath) throws JAXBException, SAXException {
        JAXBContext jAXBContext = JAXBContext.newInstance(clazz);
        Unmarshaller um = jAXBContext.createUnmarshaller();
        if(xsdPath != null && !xsdPath.isEmpty()){
            um.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File(xsdPath)));
        }
        return (T) um.unmarshal(new StreamSource(new StringReader(xml)));
    }

    /**
     * 解析微信发来的请求（XML）
     *
     * @param request
     * @return Map<String, String>
     * @throws Exception
     */
    //@SuppressWarnings("unchecked")
    public static Map<String, String> parseXML(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        HashMap<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(request.getInputStream());
        // 得到xml根元素
        Element root = document.getRootElement();

        recursiveParseXML(root,map);

        inputStream.close();
        inputStream = null;

        return map;
    }

    private static void recursiveParseXML(Element root,HashMap<String, String> map){
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        //判断有没有子元素列表
        if(elementList.size() == 0){
            map.put(root.getName(), root.getText());
        }else{
            //遍历
            for (Element e : elementList){
                recursiveParseXML(e,map);
            }
        }
    }


    /**
     * 扩展xstream使其支持CDATA
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                //@SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
    /**
     * 文本消息对象转换成xml
     *
     * @param textMessage 文本消息对象
     * @return xml
     */
    public static String messageToXML(TextMessage textMessage) {
        xstream.alias("xml", TextMessage.class);
        return xstream.toXML(textMessage);
    }

    /**
     * 图片消息对象转换成xml
     *
     * @param imageMessage 图片消息对象
     * @return xml
     */
    public static String messageToXML(ImageMessage imageMessage) {
        xstream.alias("xml", ImageMessage.class);
        return xstream.toXML(imageMessage);
    }
    /**
     * 语音消息对象转换成xml
     *
     * @param voiceMessage 语音消息对象
     * @return xml
     */
    public static String messageToXML(VoiceMessage voiceMessage) {
        xstream.alias("xml", VoiceMessage.class);
        return xstream.toXML(voiceMessage);
    }

    /**
     * 视频消息对象转换成xml
     *
     * @param videoMessage 视频消息对象
     * @return xml
     */
    public static String messageToXML(VideoMessage videoMessage) {
        xstream.alias("xml", VideoMessage.class);
        return xstream.toXML(videoMessage);
    }
}
