package static_factory;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class XmlJaxb2Factory {
    private static final Map<String, Jaxb2Marshaller> marshallers = new ConcurrentHashMap<>();

    private static Jaxb2Marshaller getJaxb2Marshaller(Class<?> clazz){
        if (marshallers.containsKey(clazz.getName())){
            return marshallers.get(clazz.getName());
        }

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();//创建JAXB上下文环境
        Map<String, Object> properties = new HashMap<>();//创建映射，用于设置Marshaller属性
        properties.put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);   //放置xml自动缩进属性
        marshaller.setClassesToBeBound(clazz);//映射的xml类放入JAXB环境中
        marshaller.setMarshallerProperties(properties);//设置Marshaller属性

        marshallers.put(clazz.getName(), marshaller);
        return marshaller;
    }

    public static <T> T unmarshal(Class<T> clazz, String xml){
        Jaxb2Marshaller marshaller = getJaxb2Marshaller(clazz);
        return (T)marshaller.unmarshal(new StreamSource(new StringReader(xml)));
    }

    public static String marshal(Object o){
        Jaxb2Marshaller marshaller = getJaxb2Marshaller(o.getClass());

        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(o, new StreamResult(stringWriter));
        return stringWriter.toString();
    }
}
