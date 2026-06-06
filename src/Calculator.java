public class Calculator {
    public int tinhGia(int giaCoBan) {
        return giaCoBan;
    }
    public double tinhGia(double giaCoBan, double thue) {
        return giaCoBan + thue;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.tinhGia(1000));
        System.out.println(calculator.tinhGia(1000, 5000));
    }

}
