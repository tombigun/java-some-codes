package springbean;

import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import domain.Student;
import domain.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.oxm.XmlMappingException;

public class MainTest {

    public static void main(String[] args) throws XmlMappingException, IOException {
/*
        //创建两个文件，分别输出不同的对象
        File file = new File("student.xml");
        File tFile = new File("teacher.xml");
        //创建学生

        Student student = new Student();
        student.setId(21231);
        student.setName("mary");

        Teacher teacher = new Teacher();
        teacher.setName("Lucy");

        //创建spring上下文（此时spring开始分析并创建单例类的对象）

        ApplicationContext ac = new AnnotationConfigApplicationContext(springbean.SpringConfigure.class);

        //获取到jaxb使用的工具

        springbean.MarshalAndUnmarshalService maus = ac.getBean(springbean.MarshalAndUnmarshalService.class);

        //将学生和教师数据编组到文件

        maus.getMarshaller().marshal(student, new StreamResult(file));

        maus.getMarshaller().marshal(teacher, new StreamResult(tFile));

        //将学生和教师信息解组到对象

        Student s = (Student) maus.getUnmarshaller().unmarshal(new StreamSource(file));

        Teacher t = (Teacher) maus.getUnmarshaller().unmarshal(new StreamSource(tFile));

        //输出学生和教师中的内容

        System.out.println(s.getName());

        System.out.println(t.getName());*/




        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfigure.class);
        MarshalAndUnmarshalService maus = ac.getBean(MarshalAndUnmarshalService.class);



        InputStream is = MainTest.class.getClassLoader().getResourceAsStream("student.xml");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i= -1; (i=is.read()) != -1; ){
            baos.write(i);
        }

        final String xml = baos.toString().trim();
        baos.close();
        is.close();

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
//                new springbean.MainTest().threadSafeByDefault(xml);
                new MainTest().spring(maus, xml);

            }).start();
        }


    }


    private void spring(MarshalAndUnmarshalService maus, String xml){
        try {
            long start = System.nanoTime();
            Student person2 = (Student)maus.getUnmarshaller().unmarshal(new StreamSource(new StringReader(xml)));

            System.out.println("2spend time: " + (System.nanoTime() - start));
            System.out.println(person2);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 2线程安全,default factory, 但会出现线程锁阻塞，导致不能高并发，
     */
    private void threadSafeByDefault(String xml){
        try {
            long start = System.nanoTime();
            JAXBContext jaxbContext = JAXBContext.newInstance(Student.class, Teacher.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Student person2 = (Student)unmarshaller.unmarshal(new StringReader(xml));
            System.out.println("2spend time: " + (System.nanoTime() - start));
            System.out.println(person2);
            System.out.println(jaxbContext);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}