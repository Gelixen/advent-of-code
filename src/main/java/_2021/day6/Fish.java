package _2021.day6;

public class Fish {
    private int day;

    public Fish() {
        day = 8;
    }

    public Fish(int day) {
        this.day = day;
    }

    public boolean isReadyToSpawnNew() {
        return day == 0;
    }

    public void reset() {
        this.day = 6;
    }

    public void decrease() {
        day--;
    }
}
