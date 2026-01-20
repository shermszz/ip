import java.util.Scanner;

public class Shermszz {
    private static void printLine() {
        System.out.println("--------------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printLine();
        System.out.println("Hello! I'm Shermszz");
        System.out.println("What can I do for you?\n");
        printLine();

        while (true) {
            String echo = sc.nextLine();
            if (echo.equals("bye")) break;
            printLine();
            System.out.println("Shermszz echoed back: " + echo);
            printLine();
        }

        printLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        printLine();
    }
}
