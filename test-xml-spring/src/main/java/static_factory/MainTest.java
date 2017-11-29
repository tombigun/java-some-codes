package static_factory;

import domain.Student;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainTest {

    public static void main(String[] args) throws IOException {


        InputStream is = springbean.MainTest.class.getClassLoader().getResourceAsStream("student.xml");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i= -1; (i=is.read()) != -1; ){
            baos.write(i);
        }

        final String xml = baos.toString().trim();
        baos.close();
        is.close();


        Student student = XmlJaxb2Factory.unmarshal(Student.class, xml);
        System.out.println(student);

        String aa = XmlJaxb2Factory.marshal(student);
        System.out.println(aa);

    }
}
