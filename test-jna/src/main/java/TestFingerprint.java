import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestFingerprint {

    public interface JNATestDll extends StdCallLibrary {
        TestFingerprint.JNATestDll instanceDll  = (TestFingerprint.JNATestDll) Native.loadLibrary("JNATestDLL", TestJna.JNATestDll.class);

        int getSize(IntByReference width, IntByReference height);

        //int __stdcall LIVESCAN_GetFPBmpData(unsigned char *pBmpData)。
        int LIVESCAN_GetFPBmpData(ByteByReference pBmpData);
    }

    public static void main(String[] args) {
        IntByReference width = new IntByReference();
        IntByReference height = new IntByReference();

        int resp = JNATestDll.instanceDll.getSize(width, height);

        int size = width.getValue() * height.getValue();
        ByteByReference data = new ByteByReference();
        Memory memory = new Memory(size);
        data.setPointer(memory);

        resp = JNATestDll.instanceDll.LIVESCAN_GetFPBmpData(data);

        byte[] bytes = memory.getByteArray(0, size);

        byte[] imagebytes = creatImagebytes(bytes, width.getValue(), height.getValue());
        try {
            Files.write(Paths.get("C:\\test.bmp"), imagebytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 把无符号的字符RGB数组转为图片byte数组
     * @param ubytes
     * @param width
     * @param height
     * @return
     */
    private static byte[] creatImagebytes(byte[] ubytes, int width, int height) {
        if (ubytes == null)
            return null;

        int length = ubytes.length;
        int[] rgbs = new int[length];
        for (int i = 0; i < length; i++) {
            rgbs[i] = ubytes[i];
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            bImage.setRGB(0, 0, width, height, rgbs, 0, width);
            ImageIO.write(bImage, "bmp", os);
            return os.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("组装指纹图像出错" + e.getMessage());
        } finally {
            try {
                os.close();
            } catch (IOException e) {
            }
        }
    }
}
