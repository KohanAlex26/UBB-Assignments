public class Command {
    private Snapshot backup;
    private Editor editor;

    public Command(Editor editor) {
        this.editor = editor;
    }

    public void makeBackup(){
        backup = editor.createSnapshot();
    }

    public void undo(){
        if(backup != null){
            backup.restore();
        }
    }


}
