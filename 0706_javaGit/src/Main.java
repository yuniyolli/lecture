// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press ⌥⏎ with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");
        for(String arg: args) {
            System.out.println(arg);

        }
        String command = args[0];
        if (command.equals("add")) {
            System.out.println("여기에 add용 코드 작성");
        }
        else if (command.equals("commit")) {
            System.out.println("여기에 commit용 코드 작성");
        }
        else {
            System.out.println("invalid command");
        }

    }
}