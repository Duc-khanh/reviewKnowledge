public class Point2DTest {
    public static void main(String[] args) {

        Point2D point = new Point2D();

        System.out.println(point);

        point = new Point2D(2.5f, 3.5f);

        System.out.println(point);

        float[] arr = point.getXY();

        System.out.println(arr[0]);
        System.out.println(arr[1]);
    }
}
