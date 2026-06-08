import java.util.Random;

public class ResizeableTest {
    public static void main(String[] args) {
// tạo mảng chứa 3 hình
        Shape[] shapes = {
                new Circle(5),
                new Rectangle(4, 6),
                new Square(5)
        };
// tạo đối tượng random
        Random random = new Random();
// duyet mảng
        for (Shape shape : shapes) {

            double percent = random.nextInt(100) + 1;

            System.out.println("==========");

            if (shape instanceof Circle) {

                Circle circle = (Circle) shape;

                System.out.println("Circle");
                System.out.println("Trước "
                        + circle.getArea());

                circle.resize(percent);

                System.out.println("Tăng "
                        + percent + "%");

                System.out.println("Sau "
                        + circle.getArea());

            } else if (shape instanceof Rectangle) {

                Rectangle rectangle = (Rectangle) shape;

                System.out.println(shape.getClass()
                        .getSimpleName());

                System.out.println("truoc "
                        + rectangle.getArea());

                rectangle.resize(percent);

                System.out.println("Tăng "
                        + percent + "%");

                System.out.println("Sau "
                        + rectangle.getArea());
            }
        }
    }
}