import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ui.Console;


/**
 * author: radu
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("hello\n");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("config");

        Console console = context.getBean(Console.class);
        console.runConsole();
        System.out.println("bye");
    }
}
