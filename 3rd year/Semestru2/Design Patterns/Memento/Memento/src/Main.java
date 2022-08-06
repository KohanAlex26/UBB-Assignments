public class Main {
    public static void main(String[] args) {
        Editor editor = new Editor("text",100,100,500);
        Command command = new Command(editor);

        command.makeBackup();

        editor.setText("text2");
        System.out.println(editor.getText());

        command.undo();

        System.out.println(editor.getText());
    }
}
