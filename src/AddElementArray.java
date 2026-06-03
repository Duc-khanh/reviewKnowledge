import java.util.Scanner;

public class AddElementArray {
    public static void main(String[] args) {

        int[] array = {10, 4, 6, 7, 8, 0, 0, 0, 0, 0};

        Scanner input = new Scanner(System.in);

        System.out.print("Nhap gia tri can chen: ");
        int x = input.nextInt();

        System.out.print("Nhap vi tri can chen: ");
        int index = input.nextInt();

        if (index < 0 || index >= array.length - 1) {
            System.out.println("Khong chen duoc phan tu vao mang");
        } else {


            for (int i = array.length - 1; i > index; i--) {
                array[i] = array[i - 1];
            }

            array[index] = x;

            System.out.println("Mang sau khi chen:");

            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
        }
    }
}