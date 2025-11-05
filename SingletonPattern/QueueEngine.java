package SingletonPattern;

public class QueueEngine {
    private static QueueEngine engine;
    private int masterCounter;
    private final String[] activeTickets = new String[3];
    private int roundRobin = 0;
    private static final String PREFIX = "A";
    private static final int FORMAT_WIDTH = 3;

    private QueueEngine() {
        masterCounter = 1;
        java.util.Arrays.fill(activeTickets, formatNumber(0));
    }

    public static synchronized QueueEngine getEngine() {
        if (engine == null) {
            engine = new QueueEngine();
        }
        return engine;
    }

    public synchronized String issueNextTicket() {
        String ticket = formatNumber(masterCounter);
        activeTickets[roundRobin] = ticket;
        String desk = "Desk " + (roundRobin + 1);
        roundRobin = (roundRobin + 1) % 3;
        masterCounter++;
        return String.format("Your queue number: %s (%s)", ticket, desk);
    }

    public synchronized String getLiveQueue() {
        return formatNumber(masterCounter);
    }

    public synchronized String rebaseQueue(int base) {
        if (base < 1) return "Error: Number must be >= 1";
        masterCounter = base + 1;
        java.util.Arrays.fill(activeTickets, formatNumber(base));
        roundRobin = 0;
        return String.format("Queue reset to %s", formatNumber(base));
    }

    public synchronized String getDeskStatus(int desk) {
        if (desk >= 0 && desk < 3) {
            return activeTickets[desk];
        }
        return "Invalid Desk";
    }

    private String formatNumber(int num) {
        return PREFIX + String.format("%0" + FORMAT_WIDTH + "d", num);
    }
}