package eu.ase.io;

import java.util.ArrayList;
import java.util.List;

public class ProgMainOOPIO {

    static void main() {
        String[] descriptions = new String[] {"T-Shirt", "Mug", "Pen"};
        double[] prices = new double[] {19.99, 8.76, 15.89};
        int[] units = new int[] {12, 8, 9};

        Invoice invoice = new Invoice(descriptions, prices, units);
        invoice.saveInvoiceToFile("invoice1.txt");
        double total = invoice.readInvoiceFromFile("invoice1.txt");
        System.out.println("TOTAL: " + total);

        // ==============================================================

        List<InvoiceItem> items = new ArrayList<>();
        items.add(new InvoiceItem("T-Shirt", 12, 19.99));
        items.add(new InvoiceItem("Mug", 8, 8.76));
        items.add(new InvoiceItem("Pen", 9, 15.89));

        InvoiceWIthItems invoiceWIthItems = new InvoiceWIthItems(items);
        invoiceWIthItems.saveToFile("test_items.txt");
        var returnedItems = invoiceWIthItems.readFromFile("test_items.txt");

        for (InvoiceItem it : returnedItems) {
            System.out.println(it);
        }
    }
}
