import java.util.Scanner;

public class Simulator {

    private Base base;
    private int days = 1;
    private int dayFirstAirportClosed;
    private int dayAllAirportsClosed;
    private final int AIPORT_QUANTITY = 4;

    public Simulator() {
        this.setup();
        this.init();        
        this.start();
        this.getResults();
    }

    private void setup() {
        Scanner scanner = new Scanner(System.in);
        int x = 0;
        int y = 0;
        System.out.println("-----------------------------");
        System.out.println(">> Size (x,y): ");

        while (x < 7) {
            System.out.print("> X (> 7): ");
            x = scanner.nextInt();
        }

        while (y < 8) {
            System.out.print("> Y (> 8): ");
            y = scanner.nextInt();
        }
        scanner.close();
        this.base = new Base(x, y);
    }

    private void init() {
        this.base.addPoint(new Point(0, 2, PointType.CLOUD));
        this.base.addPoint(new Point(0, 6, PointType.CLOUD));
        this.base.addPoint(new Point(0, 7, PointType.CLOUD));
        this.base.addPoint(new Point(1, 1, PointType.CLOUD));
        this.base.addPoint(new Point(1, 2, PointType.CLOUD));
        this.base.addPoint(new Point(2, 0, PointType.CLOUD));
        this.base.addPoint(new Point(2, 1, PointType.CLOUD));
        this.base.addPoint(new Point(2, 2, PointType.CLOUD));
        this.base.addPoint(new Point(3, 1, PointType.CLOUD));
        this.base.addPoint(new Point(4, 1, PointType.CLOUD));

        this.base.addPoint(new Point(2, 4, PointType.AIRPORT));
        this.base.addPoint(new Point(2, 7, PointType.AIRPORT));
        this.base.addPoint(new Point(4, 6, PointType.AIRPORT));
        this.base.addPoint(new Point(5, 3, PointType.AIRPORT));
    }

    private void nextDay() {
        this.base.moveClouds();
        this.base.getView();
    }

    private void start() {
        System.out.println("---------- DAY " + this.days + " ------------");
        this.base.getView();
        this.days++;
                
        int totalAirportsOpen = 1;
        
        while (totalAirportsOpen > 0) {
            System.out.println("---------- DAY " + this.days + " ------------");
            this.nextDay();

            totalAirportsOpen = this.base.getTotalAirportsOpen();
            if (totalAirportsOpen != AIPORT_QUANTITY && this.dayFirstAirportClosed <= 0) {
                this.dayFirstAirportClosed = this.days;
            } else if (totalAirportsOpen == 0) {
                this.dayAllAirportsClosed = this.days;
            }
            this.days++;
        }
    }

    public void getResults() {
        System.out.println(">> First airport closed: Day " + this.dayFirstAirportClosed);
        System.out.println(">> All airports closed: Day " + this.dayAllAirportsClosed);
    }
}
