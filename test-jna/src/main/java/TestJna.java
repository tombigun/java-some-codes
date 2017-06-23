import com.sun.jna.*;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.*;

import java.util.List;
import java.util.Arrays;

/**
 * 运行参数需要加上：-Djava.library.path=./dll
 * Created by tombigun on 2017/6/23.
 */
public class TestJna {

	public static class MyStructure extends Structure {
		public int left;
		public int top;
		public int right;
		public int bottom;

		protected List getFieldOrder() {
			return Arrays.asList(new String[] { "left","top","right","bottom"});
		}
	}

	public interface JNATestDll extends StdCallLibrary {
		JNATestDll instanceDll  = (JNATestDll)Native.loadLibrary("JNATestDLL",JNATestDll.class);


		int methodFunction1(byte[] arg);
		int methodFunction2(int arg);
		int methodFunction3(int arg1, MyStructure rect);
		int methodFunction4(ByteByReference arg);

        PointerByReference methodFunction5();
		int methodFunction6(PointerByReference arg);

	}

	public static void main(String[] args) {

		byte[] a = new byte[10];
		JNATestDll.instanceDll.methodFunction1(a);

		JNATestDll.instanceDll.methodFunction2(1);

		MyStructure mystr = new MyStructure();
		JNATestDll.instanceDll.methodFunction3(1, mystr);

		int memLen = 10;
        Memory memory = new Memory(memLen);
        ByteByReference byteByReference = new ByteByReference();
        byteByReference.setPointer(memory);
        JNATestDll.instanceDll.methodFunction4(byteByReference);
        byte[] bytes = memory.getByteArray(0, memLen);

        PointerByReference pointerByReference = JNATestDll.instanceDll.methodFunction5();
        JNATestDll.instanceDll.methodFunction6(pointerByReference);
    }

}
