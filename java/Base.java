public class Base {

    private final Point[][] base;
    private int sizeX;
    private int sizeY;
    private int version = 0;

    public Base(int x, int y) {
        this.sizeX = x;
        this.sizeY = y;
        this.base = new Point[this.sizeX][this.sizeY];
        this.setPoints();
    }

    public void setPoint(Point point) {

    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public void setPoints() {
        for (int x = 0; x < this.getSizeX(); x++) {
            for (int y = 0; y < this.getSizeY(); y++) {
                if (this.base[x][y] == null) {
                    this.addPoint(new Point(x, y));
                }
            }
        }
    }

    public Point getPoint(int x, int y) {
        return this.base[x][y];
    }

    public void addPoint(Point point) {
        point.setVersion(point.getVersion() + 1);
        this.base[point.getX()][point.getY()] = point;
    }

    public void getView() {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("---------------------------------------------------------------------------\n");
        for (int x = 0; x < this.getSizeX(); x++) {
            for (int y = 0; y < this.getSizeY(); y++) {
                sb.append("\t");
                sb.append(this.base[x][y].getPointType().getValue());
            }
            sb.append("\n");
        }
        sb.append("---------------------------------------------------------------------------\n");
        System.out.println(sb.toString());
        this.version++;
    }

    public void moveClouds() {
        for (int x = 0; x < this.getSizeX(); x++) {
            for (int y = 0; y < this.getSizeY(); y++) {
                if (this.getPoint(x, y).getPointType() == PointType.CLOUD) {
                    if (this.getPoint(x, y).getVersion() == this.version) {
                        this.moveCloud(this.getPoint(x, y));
                    } else {
                        this.getPoint(x, y).setX(x);
                        this.getPoint(x, y).setY(y);
                    }
                }
            }
        }
    }

    private void moveCloud(Point point) {
        this.moveCloudUp(point);
        this.moveCloudRight(point);
        this.moveCloudDown(point);
        this.moveCloudLeft(point);
        point.setVersion(point.getVersion() + 1);
    }

    private void moveCloudRight(Point point) {
        int y = point.getY() + 1;
        if (y < this.getSizeY() && (this.getPoint(point.getX(), y)).getPointType() != PointType.CLOUD) {
            Point newCloud = new Point(point.getX(), y, PointType.CLOUD);
            newCloud.setVersion(point.getVersion() + 1);
            this.base[point.getX()][y] = newCloud;
        }
    }

    private void moveCloudLeft(Point point) {
        int y = point.getY() - 1;
        if (y >= 0 && (this.getPoint(point.getX(), y)).getPointType() != PointType.CLOUD) {
            Point newCloud = new Point(point.getX(), y, PointType.CLOUD);
            newCloud.setVersion(point.getVersion() + 1);
            this.base[point.getX()][y] = newCloud;
        }
    }

    private void moveCloudUp(Point point) {
        int x = point.getX() - 1;
        if (x >= 0 && (this.getPoint(x, point.getY())).getPointType() != PointType.CLOUD) {
            Point newCloud = new Point(x, point.getY(), PointType.CLOUD);
            newCloud.setVersion(point.getVersion() + 1);
            this.base[x][point.getY()] = newCloud;
        }
    }

    private void moveCloudDown(Point point) {
        int x = point.getX() + 1;
        if (x < this.getSizeX() && (this.getPoint(x, point.getY())).getPointType() != PointType.CLOUD) {
            Point newCloud = new Point(x, point.getY(), PointType.CLOUD);
            newCloud.setVersion(point.getVersion() + 1);
            this.base[x][point.getY()] = newCloud;
        }
    }

    public int getTotalAirportsOpen() {
        int totalAirportsOpen = 0;
        for (int x = 0; x < this.getSizeX(); x++) {
            for (int y = 0; y < this.getSizeY(); y++) {
                if (this.base[x][y].getPointType() == PointType.AIRPORT) {
                    totalAirportsOpen++;
                }
            }
        }
        
        return totalAirportsOpen;
    }

}
