import java.util.Arrays;
import java.util.Stack;

public class ReverseByStack {
    public static void main(String[] args) {
        reverseIntegerArray();
        reverseString();
    }

    public static void reverseIntegerArray() {
        int[] arr = {1, 2, 3, 4, 5};

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            stack.push(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = stack.pop();
        }

        System.out.println("Mảng sau khi đảo: " + Arrays.toString(arr));
    }

    public static void reverseString() {
        String str = "Nguyen Duc Khanh";

        String[] words = str.split(" "); // cắt chuoi tai dau cach

        Stack<String> wStack = new Stack<>();

        for (int i = 0; i < words.length; i++) {
            wStack.push(words[i]);
        }

        String output = "";

        while (!wStack.isEmpty()) {
            output += wStack.pop() + " ";
        }

        System.out.println("Chuỗi sau khi đảo: " + output.trim()); // xoa khoang trang cuoi
    }
}