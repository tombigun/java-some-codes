import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * 查看运行期类来源的JAR包
 */
public class ClassLocationUtils {

    public static String where(final Class clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("null input: clazz");
        }

        URL result = null;
        final String clsAsResource = clazz.getName().replace('.', '/').concat(".class");
        final ProtectionDomain pd = clazz.getProtectionDomain();
        if (pd != null) {
            final CodeSource cs = pd.getCodeSource();
            if (cs != null) {
                result = cs.getLocation();
            }

            if (result != null) {
                if ("file".equals(result.getProtocol())) {
                    try {
                        if (result.toExternalForm().endsWith(".jar") || result.toExternalForm().endsWith(".zip")) {
                            result = new URL("jar:".concat(result.toExternalForm()).concat("!/").concat(clsAsResource));
                        } else if (new File(result.getFile()).isDirectory()) {
                            result = new URL(result, clsAsResource);
                        }
                    } catch (MalformedURLException ignore) {
                    }
                }
            }
        }

        if (result == null) {
            final ClassLoader clsLoader = clazz.getClassLoader();
            result = clsLoader != null ? clsLoader.getResource(clsAsResource) : ClassLoader.getSystemResource(clsAsResource);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(where(ClassLocationUtils.class));
    }
}
