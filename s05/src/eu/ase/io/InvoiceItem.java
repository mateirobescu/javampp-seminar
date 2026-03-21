package eu.ase.io;

public class InvoiceItem {
    private String description;
    private int unit;
    private double price;

    public InvoiceItem(String description, int unit, double price) {
        this.description = description;
        this.unit = unit;
        this.price = price;
    }

    public String getDesription() {
        return description;
    }

    public int getUnit() {
        return this.unit;
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "InvoiceItem - " + this.description + ", price: " + this.price + ", units: " + this.unit;
    }

}
