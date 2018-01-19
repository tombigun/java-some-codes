import com.tombigun.spi.PersonService;

import java.util.ServiceLoader;

/**
 * @author tombigun
 * @version 1.0
 * @date 2018/1/19
 */
public class TestCase {


    public static void main(String[] args) {
        ServiceLoader<PersonService> loaders = ServiceLoader.load(PersonService.class);

        for (PersonService service : loaders) {
            System.out.println(service.getName());
        }
    }
}
