
import java.util.Arrays;

public class AddString {

    public static void main(String[] args) {
        String numString = "1,2;3";
        int num = 0;
        int[] numStr = Arrays.stream(numString.split("[,; ]+")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < numStr.length; i++) {
            num = num + numStr[i];
        }
        System.out.println(num);
    }
}
