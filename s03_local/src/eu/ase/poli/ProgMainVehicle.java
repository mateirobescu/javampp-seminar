package eu.ase.poli;

public class ProgMainVehicle {

    public static void main(String[] args) {
        Auto a = null;
        try {
            a = new Auto(1200, 5);
            System.out.println(a.display());
        } catch (Exception e) {
            throw new RuntimeException();
        }
        Vehicle v = null;
        Plane p = new Plane(1500, 12, 2);
        v = a;
        System.out.println(v.display());
        v = p;
        System.out.println(v.display());

//        Vehicle v0 = null;
//        p = (Plane) v0;
//        p.test();
//        p = a;

        Movement m = null;
        try {
            m = new Auto(1900, 4);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        m.start_engine();
        m.stop_engine();
        Auto aa = (Auto) m;
        System.out.println(aa.display());

    }
}
