package eu.ase.io;

import java.io.*;

public class Invoice {
    private String[] description;
    private double[] prices;
    private int[] units;

    public Invoice(String[] description, double[] prices, int[] units) {
        this.description = description;
        this.prices = prices;
        this.units = units;
    }

    public void saveInvoiceToFile(String fileName) {
        DataOutputStream out = null;

        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            out = new DataOutputStream(bos);

            for(int i = 0; i < description.length; i++){
                out.writeUTF(description[i]);
                out.writeDouble(prices[i]);
                out.writeInt(units[i]);
            }

            out.close();

        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    public double readInvoiceFromFile(String fileName) {
        double total = 0.0;

        try {
            DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));

            String desc;
            double price;
            int unit;
            try {
                while(true) {
                    desc = dis.readUTF();
                    price = dis.readDouble();
                    unit = dis.readInt();

                    total += price * unit;
                }
            } catch (EOFException ex) {
                dis.close();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return total;
    }

}
