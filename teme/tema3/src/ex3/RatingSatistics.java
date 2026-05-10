package ex3;

public class RatingSatistics {
    private double sum = 0;
    private double avg = 0;
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;
    private int over4point5 = 0;
    private int count = 0;
    private String name;

    RatingSatistics() {
        this("No name");
    }

    RatingSatistics(String name) {
        this.name = name;
    }

    public RatingSatistics update(double rating) {
        this.count++;
        this.sum += rating;
        this.avg = this.sum / this.count;
        this.min = Math.min(this.min, rating);
        this.max = Math.max(this.max, rating);
        this.over4point5 += (rating >= 4.5) ? 1 : 0;

        return this;
    }

    public RatingSatistics merge(RatingSatistics other) {
        this.count += other.count;
        this.sum += other.sum;
        this.avg = this.sum / this.count;
        this.min = Math.min(this.min, other.min);
        this.max = Math.max(this.max, other.max);
        this.over4point5 += other.over4point5;

        return this;
    }
    public double getSum() {
        return sum;
    }

    public double getAvg() {
        return avg;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public int getOver4point5() {
        return over4point5;
    }

    public int getCount() {
        return count;
    }

    public String print(long timePassed) {

        return "=== " + this.name + " - " + timePassed + " miliseconds ===" +
                "\nSum of ratings: " + this.sum +
                "\nAvg of ratings: " + this.avg +
                "\nMin of ratings: " + this.min +
                "\nMax of ratings: " + this.max +
                "\nRstings over 4.5: " + this.over4point5 +
                "\n\n";
    }
}
