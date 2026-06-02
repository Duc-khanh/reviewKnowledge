import java.util.Scanner;

public class UsdToVND {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhâp tiền USD: ");
        double usd = scanner.nextDouble();
        double exchange = 23000;
        double vnd = usd * exchange ;
        System.out.println(vnd);
    }
}
