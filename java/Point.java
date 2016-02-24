public class Point {

    private int x;
    private int y;
    private PointType pointType;
    private int version = 0;

    public Point(int x, int y, PointType pointType) {
        this.x = x;
        this.y = y;
        this.pointType = pointType == null ? PointType.VOID : pointType;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.pointType = PointType.VOID;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public PointType getPointType() {
        return pointType;
    }

    public void setPointType(PointType pointType) {
        this.pointType = pointType;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
