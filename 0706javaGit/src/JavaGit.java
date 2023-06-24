public class JavaGit {
    public static void main(String[] args) {
        for(String arg: args) {
            System.out.print(arg + " ");

        }
        String command = args[0];
        if (command.equals("add")) {
            System.out.println("add changes to git:");
        }
        else if (command.equals("commit -m")) {
            System.out.println("commit with message: ");
        }
        else if (command.equals("commit")) {
            System.out.println("no message specified");
        }
        else {
            System.out.println("no message specified");
        }
    }
}
