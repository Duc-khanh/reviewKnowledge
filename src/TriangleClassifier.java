public class TriangleClassifier {

    public static String classifyTriangle(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return "không phải là tam giác";
        }

        if (a + b <= c || a + c <= b || b + c <= a) {
            return "không phải là tam giác";
        }

        if (a == b && b == c) {
            return "tam giác đều";
        }

        if (a == b || a == c || b == c) {
            return "tam giác cân";
        }

        return "tam giác thường";
    }
}