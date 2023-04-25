package project.entity;

public class Gate extends object {
    private static final int totalImageOfGate = 2;
    public boolean checkTouch = false;

    public Gate(int x, int y) {
        super(x, y, totalImageOfGate, "gate");
    }

}
