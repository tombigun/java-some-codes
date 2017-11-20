import java.util.Arrays;

public class TestArray {
    public static void main(String[] args) {
        char[] aa = {'a','b','c'};
        char[] bb = aa;

        aa[1] = 'x';

        System.out.println(Arrays.toString(bb));
    }
}
