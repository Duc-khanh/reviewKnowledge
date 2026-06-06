public class Point3DTest {
    public static void main(String[] args) {

        Point3D point = new Point3D();

        System.out.println(point);

        point = new Point3D(1.0f, 2.0f, 3.0f);

        System.out.println(point);

        float[] arr = point.getXYZ();

        for (float value : arr) {
            System.out.print(value + " ");
        }
    }
}