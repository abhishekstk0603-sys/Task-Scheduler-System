import java.util.Scanner;

public class Main {

    static int[] taskId = new int[100];
    static String[] taskName = new String[100];
    static int[] priority = new int[100];
    static String[] status = new String[100];

    static int count = 0;

    static Scanner sc = new Scanner(System.in);

    // 1. Add Task
    static void addTask(String name, int p) {

        if (count >= 100) {
            System.out.println("Task limit reached!");
            return;
        }

        if (p < 1 || p > 3) {
            System.out.println("Invalid priority!");
            return;
        }

        count++;

        taskId[count - 1] = count;
        taskName[count - 1] = name;
        priority[count - 1] = p;
        status[count - 1] = "Pending";

        System.out.println("Task added: " + name);
    }

    // 2. View All Tasks
    static void viewTasks() {

        if (count == 0) {
            System.out.println("No tasks available!");
            return;
        }

        System.out.println("\nID\tTask Name\t\tPriority\tStatus");

        for (int i = 0; i < count; i++) {

            System.out.println(
                taskId[i] + "\t" +
                taskName[i] + "\t\t" +
                priority[i] + "\t\t" +
                status[i]
            );
        }
    }

    // 3. Execute Highest Priority Task
    static void executeTask() {

        int best = -1;

        for (int i = 0; i < count; i++) {

            if (status[i].equals("Pending")) {

                if (best == -1 || priority[i] < priority[best]) {
                    best = i;
                }
            }
        }

        if (best == -1) {
            System.out.println("No pending tasks!");
            return;
        }

        status[best] = "Completed";

        System.out.println(
            "Completed task: " + taskName[best]
        );
    }

    // 4. View Tasks By Status
    static void viewByStatus(String wantedStatus) {

        boolean found = false;

        for (int i = 0; i < count; i++) {

            if (status[i].equals(wantedStatus)) {

                System.out.println(
                    taskId[i] + " | " +
                    taskName[i] + " | Priority: " +
                    priority[i] + " | " +
                    status[i]
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No " + wantedStatus + " tasks!");
        }
    }

    // 5. Automatic Demo
    static void demo() {

        System.out.println("\n===== AUTOMATIC DEMO =====");

        addTask("Watch Movie", 3);
        addTask("Complete DSA Assignment", 1);
        addTask("Study AI ML", 2);

        System.out.println("\n--- All Tasks ---");
        viewTasks();

        System.out.println("\n--- Execute Next Task ---");
        executeTask();

        System.out.println("\n--- Tasks After Execution ---");
        viewTasks();

        System.out.println("\n===== DEMO FINISHED =====");
    }

    // 6. Main Function
    public static void main(String[] args) {

        demo();

        while (true) {

            System.out.println("\n===== TASK SCHEDULER SYSTEM =====");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Execute Next Task");
            System.out.println("4. View Pending Tasks");
            System.out.println("5. View Completed Tasks");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");

            String input = sc.nextLine();

            switch (input) {

                case "1":

                    System.out.print("Enter task name: ");
                    String name = sc.nextLine();

                    System.out.print(
                        "Priority (1=High, 2=Medium, 3=Low): "
                    );

                    try {

                        int p = Integer.parseInt(sc.nextLine());

                        addTask(name, p);

                    } catch (NumberFormatException e) {

                        System.out.println("Enter a valid number!");
                    }

                    break;

                case "2":

                    viewTasks();
                    break;

                case "3":

                    executeTask();
                    break;

                case "4":

                    viewByStatus("Pending");
                    break;

                case "5":

                    viewByStatus("Completed");
                    break;

                case "6":

                    System.out.println(
                        "Thank you for using Task Scheduler!"
                    );

                    sc.close();
                    return;

                default:

                    System.out.println("Invalid choice!");
            }
        }
    }
}
