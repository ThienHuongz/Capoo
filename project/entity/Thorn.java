package project.entity;

public class Thorn extends object {
    private static final int totalImageOfThorn = 1;

    public Thorn(int x, int y) {
        super(x, y, totalImageOfThorn, "thorn");
    }

    public Thorn(int x, int y, int type) {
        super(x, y, totalImageOfThorn, "thorn2", "thorn");
    }

}
