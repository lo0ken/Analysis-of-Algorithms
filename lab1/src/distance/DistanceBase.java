package distance;

abstract class DistanceBase {
    private int distance;
    protected String firstWord;
    protected String secondWord;
    long start;

    public DistanceBase(String firstWord, String secondWord) {
        start = Runtime.getRuntime().freeMemory();

        this.firstWord = firstWord;
        this.secondWord = secondWord;
    }


    public int calculate() {
        if (checkWorlds())
            distance = calculateDistance();

        long end = Runtime.getRuntime().freeMemory();
        long memoTaken = start - end;
        System.out.println(memoTaken);
        return distance;
    }

    protected abstract int calculateDistance();

    private boolean checkWorlds() {
        if (firstWord.isEmpty() && secondWord.isEmpty()) {
            distance = 0;
            return false;
        }
        else if (firstWord.isEmpty() || secondWord.isEmpty()) {
            distance = 1;
            return false;
        }
        return true;
    }
}
