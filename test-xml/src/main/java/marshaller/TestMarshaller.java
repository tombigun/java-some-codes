package marshaller;

import domain.PersonDTO;
import domain.StudentDTO;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class TestMarshaller {

    public static void main(String[] args) throws JAXBException {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setSchool("挖掘机学校");
        studentDTO.setScore(90);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setName("tom");
        personDTO.setAge(18);
        personDTO.setStudentDTO(studentDTO);



        // 1.这是线程不安全的
        StringWriter stringWriter1 = new StringWriter();

        JAXB.marshal(personDTO, stringWriter1);

        System.out.println(stringWriter1.toString());


        // 2.下面为线程安全
        StringWriter stringWriter2 = new StringWriter();

        //见JAXBContext.newInstance这个方法的逻辑，有多种方式可以配置第三方Factory，没有配置默认ContextFinder.PLATFORM_DEFAULT_FACTORY_CLASS(com.sun.xml.internal.bind.v2.ContextFactory)
        JAXBContext jaxbContext = JAXBContext.newInstance(PersonDTO.class, StudentDTO.class);
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);//格式化

        marshaller.marshal(personDTO, stringWriter2);

        System.out.println(stringWriter2.toString());


        // 用第三方的除了以下面的硬代码，也是要要PersonDTO类的包下配置jaxb.properties文件中javax.xml.bind.context.factory=org.eclipse.persistence.jaxb.JAXBContextFactory
        // 3.用第三方的Factory
        StringWriter stringWriter3 = new StringWriter();

        JAXBContext jc = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[] {PersonDTO.class, StudentDTO.class}, null);
        Marshaller jcMarshaller = jc.createMarshaller();
        jcMarshaller.marshal(personDTO, stringWriter3);

        System.out.println(stringWriter3.toString());
    }
}
