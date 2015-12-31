package two.game.model;

public class Point {
    private Double x;
    private Double y;

    public Point() {
    }

    public Point(Integer x, Integer y) {
        this.x = (double) x;
        this.y = (double) y;
    }

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != null ? !x.equals(point.x) : point.x != null) return false;
        return !(y != null ? !y.equals(point.y) : point.y != null);

    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }

    public static Point of(double x, double y) {
        return new Point(x, y);
    }

    public double distanceTo(Point target) {
        Double x2 = target.x;
        Double y2 = target.y;
        Double dx = Math.pow((x - x2), 2);
        Double dy = Math.pow((y - y2), 2);
        return Math.sqrt(dx + dy);
    }
}
