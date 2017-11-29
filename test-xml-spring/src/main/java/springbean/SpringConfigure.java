package springbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SpringConfigure {

    public @Bean Jaxb2Marshaller jaxb2Marshaller()//配置JAXB2Context
    {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();//创建JAXB上下文环境

        Map<String, Object> properties = new HashMap<String, Object>();//创建映射，用于设置Marshaller属性

        properties.put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);   //放置xml自动缩进属性

        //marshaller.setClassesToBeBound(Student.class, Teacher.class);//映射的xml类放入JAXB环境中
        marshaller.setPackagesToScan("domain");

        marshaller.setMarshallerProperties(properties);//设置Marshaller属性

        return marshaller;
    }


    public @Bean MarshalAndUnmarshalService marshalAndUnmarshalService() {

        return new MarshalAndUnmarshalService();//创建和使用JAXB
    }

}