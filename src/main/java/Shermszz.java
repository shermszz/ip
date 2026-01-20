import java.util.ArrayList;
import java.util.Scanner;

public class Shermszz {
    private static ArrayList<String> list; // To store a list of user's instructions

    private static void printLine() {
        System.out.println("--------------------------------------");
    }

    private static void printList() {
        int i = 1;
        if (list.isEmpty()) {
            System.out.println("The list is empty. Add something...");
            printLine();
        }
        for (String s : list) {
            System.out.println(i + ". " + s);
            i++;
        }
        System.out.println(); //Just for spacing
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        list = new ArrayList<>();
        printLine();
        System.out.println("Hello! I'm Shermszz");
        System.out.println("What can I do for you?\n");
        printLine();

        while (true) {
            String echo = sc.nextLine();
            if (echo.equals("bye")) break;
            else if (echo.equals("list"))  {
                printList();
            }
            else {
                printLine();
                list.add(echo);
                System.out.println("Shermszz added your command: " + echo);
                printLine();
            }
        }

        printLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        printLine();
    }
}
