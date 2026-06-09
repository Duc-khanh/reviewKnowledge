public class Cylinder {
    public static double getVolume(int radius, int height){
        // code ban đầu
//        double baseArea = Math.PI * radius * radius;
//        double perimeter = 2 * Math.PI  * radius;
//        double volume = perimeter * height + 2 * baseArea;
//        return volume;
        // code sau khi tach

        double baseArea = getBaseArea(radius);
        double perimeter = getPerimeter(radius);
        return getArea(height, perimeter, baseArea);
    }
    // sd phím tắt Ctrl + alt + M để tach pthuc

    private static double getArea(int height, double perimeter, double baseArea) {
        return perimeter * height + 2 * baseArea;
    }

    private static double getPerimeter(int radius) {
        return 2 * Math.PI  * radius;
    }

    private static double getBaseArea(int radius) {
        return Math.PI * radius * radius;
    }
}