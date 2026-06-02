import java.util.Scanner;

public class Prime {
    public static void main(String[] args) {
//        for (int i = 1 ; i <= 100 ; i ++){
//            System.out.println(i);
//        }
//    }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number:");
        int number = scanner.nextInt();
        if (number < 2){
            System.out.println(number + " is not prime");
        } else {
            boolean isPrime = true;
            for (int i = 2; i < number; i++) {
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.println(number + " is prime");
            } else {
                System.out.println(number + " is not prime");
            }
        }
    }
}