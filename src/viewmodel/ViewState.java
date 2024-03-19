package viewmodel;


public class ViewState {
    private int number;
    private boolean remove;

    public ViewState() {
        this.remove = false;
        this.number = 0;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public void removeNumber() {
        this.number = 0;
    }

}