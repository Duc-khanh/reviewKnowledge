import java.util.Scanner;

public class MainQuadraticEquation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap a: ");
        double a = sc.nextDouble();

        System.out.print("Nhap b: ");
        double b = sc.nextDouble();

        System.out.print("Nhap c: ");
        double c = sc.nextDouble();
        QuadraticEquation equation =
                new QuadraticEquation(a, b, c);

        double delta = equation.getDiscriminant();

        if (delta > 0) {
            System.out.println("Root 1: "
                    + equation.getRoot1());
            System.out.println("Root 2: "
                    + equation.getRoot2());
        } else if (delta == 0) {
            System.out.println("Root: "
                    + equation.getRoot1());
        } else {
            System.out.println("Phương trình không có nghiệm");
        }
    }
}
