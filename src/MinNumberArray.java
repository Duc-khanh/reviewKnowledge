
public class MinNumberArray {
    public static void main(String[] args) {
        int[] array ={ 19,39,6,8,4,98,25};
        int index = minValue(array);
        System.out.println(array[index]);
    }
    public static int minValue(int[] array) {
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[index]) {
                index = i;
            }
        }
        return index;
    }
}
