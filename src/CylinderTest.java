public class CylinderTest {
    public static void main(String[] args) {
        Cylinder cylinder1 = new Cylinder();
        System.out.println(cylinder1);

        Cylinder cylinder2 = new Cylinder(3.5);
        System.out.println(cylinder2);

        Cylinder cylinder3 = new Cylinder(3.5, 5.0);
        System.out.println(cylinder3);

        Cylinder cylinder4 = new Cylinder(3.5, "green", 5.0);
        System.out.println(cylinder4);
    }
}