public class Circle extends Shape implements Resizeable {
    private double radius;

    public Circle() {
        radius = 1.0;
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void resize(double percent) {
        radius += radius * percent / 100;
    }

    @Override
    public String toString() {
        return "Circle radius = " + radius;
    }
}


//    @Override
//    public String toString() {
//        return "A Circle with radius="
//                + getRadius()
//                + ", which is a subclass of "
//                + super.toString();
//    }
//}
