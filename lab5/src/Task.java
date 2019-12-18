public class Task {
    private int id;
    private boolean isLast;
    public long[] timeStart;
    public long[] timeEnd;

    public Task(int id, boolean isLast) {
        this.id = id;
        this.isLast = isLast;
        timeStart = new long[Main.COUNT_CONVEYORS];
        timeEnd = new long[Main.COUNT_CONVEYORS];
    }

    public int getId() {
        return id;
    }

    public boolean isLast() {
        return isLast;
    }
}
