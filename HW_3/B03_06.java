package HW_3;

public class B03_06 {
    static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double distance(Point p) {
            return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
        }
    }

    static class Quadrilateral {
        Point a, b, c, d;

        public Quadrilateral(Point a, Point b, Point c, Point d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        public double getPerimeter() {
            return a.distance(b) + b.distance(c) + c.distance(d) + d.distance(a);
        }

        public String getType() {
            double ab = a.distance(b);
            double bc = b.distance(c);
            double cd = c.distance(d);
            double da = d.distance(a);

            double ac = a.distance(c);
            double bd = b.distance(d);

            if (Math.abs(ab - bc) < 1e-6 && Math.abs(bc - cd) < 1e-6 && Math.abs(cd - da) < 1e-6
                    && Math.abs(ac - bd) < 1e-6) {
                return "Квадрат";
            }

            if (Math.abs(ab - cd) < 1e-6 && Math.abs(bc - da) < 1e-6
                    && Math.abs(ac - bd) < 1e-6) {
                return "Прямокутник";
            }

            if (Math.abs(ab - bc) < 1e-6 && Math.abs(bc - cd) < 1e-6 && Math.abs(cd - da) < 1e-6) {
                return "Ромб";
            }

            return "Довільний";
        }
    }

    static class QuadrilateralUtils {
        public static void countTypes(Quadrilateral[] quads) {
            int squares = 0, rectangles = 0, rhombs = 0, others = 0;

            for (Quadrilateral q : quads) {
                String type = q.getType();
                switch (type) {
                    case "Квадрат": squares++; break;
                    case "Прямокутник": rectangles++; break;
                    case "Ромб": rhombs++; break;
                    default: others++;
                }
            }

            System.out.println("Квадратів: " + squares);
            System.out.println("Прямокутників: " + rectangles);
            System.out.println("Ромбів: " + rhombs);
            System.out.println("Довільних: " + others);
        }

        public static Quadrilateral getMaxPerimeter(Quadrilateral[] quads) {
            Quadrilateral maxQuad = quads[0];
            for (Quadrilateral q : quads) {
                if (q.getPerimeter() > maxQuad.getPerimeter()) {
                    maxQuad = q;
                }
            }
            return maxQuad;
        }
    }

    public static void main(String[] args) {
        Quadrilateral q1 = new Quadrilateral(
                new Point(0, 0), new Point(0, 2),
                new Point(2, 2), new Point(2, 0)); // квадрат

        Quadrilateral q2 = new Quadrilateral(
                new Point(0, 0), new Point(0, 3),
                new Point(5, 3), new Point(5, 0)); // прямокутник

        Quadrilateral q3 = new Quadrilateral(
                new Point(0, 0), new Point(2, 2),
                new Point(4, 0), new Point(2, -2)); // ромб

        Quadrilateral q4 = new Quadrilateral(
                new Point(0, 0), new Point(1, 3),
                new Point(4, 2), new Point(3, -1)); // довільний

        Quadrilateral[] arr = { q1, q2, q3, q4 };

        QuadrilateralUtils.countTypes(arr);

        Quadrilateral maxQ = QuadrilateralUtils.getMaxPerimeter(arr);
        System.out.println("Найбільший периметр = " + maxQ.getPerimeter() + " (" + maxQ.getType() + ")");
    }
}
