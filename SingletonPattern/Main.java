package SingletonPattern;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        QueueEngine engine = QueueEngine.getEngine();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nPag-ibig Centralized Queuing System");
            System.out.println("1. Generate New Queue Number");
            System.out.println("2. Check Current Queue Status");
            System.out.println("3. Reset Queue to New Number");
            System.out.println("4. View All Desk Serving Numbers");
            System.out.println("5. Exit the System");
            System.out.print("Select option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(engine.issueNextTicket());
                    break;
                case 2:
                    System.out.println("Current queue number: " + engine.getLiveQueue());
                    break;
                case 3:
                    System.out.print("Enter new queue number: ");
                    int reset = sc.nextInt();
                    System.out.println(engine.rebaseQueue(reset));
                    break;
                case 4:
                    for (int i = 0; i < 3; i++) {
                        System.out.println("Desk " + (i + 1) + ": Now Serving " + engine.getDeskStatus(i));
                    }
                    break;
                case 5:
                    System.out.println("Exiting now...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}