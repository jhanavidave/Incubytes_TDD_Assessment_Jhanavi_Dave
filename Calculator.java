
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {

    public static void main(String[] args) {
        add("//;\n1;2");
    }

    public static int add(String numString) {
        if (numString.startsWith("//")) {
            String delimiter = numString.substring(2, numString.indexOf("\n"));
            numString = numString.substring(numString.indexOf("\n") + 1);
        }

        int sum = 0;
        ArrayList notAllowed = new ArrayList();
        int[] numStr = Arrays.stream(numString.split("[,; \n*'%]+")).mapToInt(Integer::parseInt).toArray();

        

        if (numString.isEmpty()) {
            return 0;
        }

        for (int i = 0; i < numStr.length; i++) {
            if (numStr[i] > 0 && numStr[i] < 1000) {
                sum = sum + numStr[i];
            } else if (numStr[i] < 0) {
                notAllowed.add(numStr[i]);
            }
        }

        System.out.println(sum);
        if (notAllowed.isEmpty() == false) {
            throw new IllegalArgumentException("negative numbers not allowed < " + notAllowed + " >");
            // System.out.println("negative numbers not allowed < " + notAllowed + " >");
        }

        return sum;
    }
}
