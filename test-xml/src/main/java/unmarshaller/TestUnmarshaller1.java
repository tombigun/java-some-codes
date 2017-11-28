package unmarshaller;

import domain.PersonDTO;
import domain.StudentDTO;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

public class TestUnmarshaller1 {


    public static void main(String[] args) throws JAXBException, IOException {
        InputStream is = TestUnmarshaller1.class.getClassLoader().getResourceAsStream("test.xml");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i= -1; (i=is.read()) != -1; ){
            baos.write(i);
        }

        final String xml = baos.toString().trim();
        baos.close();
        is.close();


        for (int i = 0; i < 1000; i++) {
//            new Thread(() -> new TestUnmarshaller1().threadUnsafe(xml)).start();
            new Thread(() -> new TestUnmarshaller1().threadSafeByDefault(xml)).start();
//            new Thread(() -> new TestUnmarshaller1().threadSafeByOther(xml)).start();

        }


    }

    /**
     * 1线程不安全
     */
    private void threadUnsafe(String xml){
        long start = System.nanoTime();
        PersonDTO personDTO = JAXB.unmarshal(new StringReader(xml), PersonDTO.class);
        System.out.println("1spend time: " + (System.nanoTime() - start));
        System.out.println(personDTO);
    }

    /**
     * 2线程安全,default factory, 但会出现线程锁阻塞，导致不能高并发，
     */
    private void threadSafeByDefault(String xml){
        try {
            long start = System.nanoTime();
            JAXBContext jaxbContext = JAXBContext.newInstance(PersonDTO.class, StudentDTO.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            PersonDTO person2 = (PersonDTO)unmarshaller.unmarshal(new StringReader(xml));
            System.out.println("2spend time: " + (System.nanoTime() - start));
            System.out.println(person2);
            System.out.println(jaxbContext);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 3线程安全，用第三方
     */
    private void threadSafeByOther(String xml) {
        try {
            long start = System.nanoTime();
            JAXBContext jc = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[] {PersonDTO.class, StudentDTO.class}, null);
            Unmarshaller unmarshaller2 = jc.createUnmarshaller();
            PersonDTO person3 = (PersonDTO)unmarshaller2.unmarshal(new StringReader(xml));
            System.out.println("3spend time: " + (System.nanoTime() - start));
            System.out.println(person3);
            System.out.println(jc);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
