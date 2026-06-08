public class Rectangle extends Shape implements Resizeable {
    private double width;
    private double height;

    public Rectangle() {
        width = 1;
        height = 1;
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }

    @Override
    public void resize(double percent) {
        width += width * percent / 100;
        height += height * percent / 100;
    }
}