public class Editor {
    private String text;
    private int curX;
    private int curY;
    private int selectionWidth;

    public Editor(String text, int curX, int curY, int selectionWidth){
        this.text = text;
        this.curX = curX;
        this.curY = curY;
        this.selectionWidth = selectionWidth;
    }

    public Snapshot createSnapshot(){
        return new Snapshot(this,text,curX,curY,selectionWidth);
    }

    public String getText() {
        return text;
    }

    public int getCurX() {
        return curX;
    }

    public int getCurY() {
        return curY;
    }

    public int getSelectionWidth() {
        return selectionWidth;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCurX(int curX) {
        this.curX = curX;
    }

    public void setCurY(int curY) {
        this.curY = curY;
    }

    public void setSelectionWidth(int selectionWidth) {
        this.selectionWidth = selectionWidth;
    }
}
