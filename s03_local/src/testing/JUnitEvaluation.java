package testing;

import eu.ase.poli.Auto;
import org.junit.Assert;
import org.junit.Test;

public class JUnitEvaluation {
    @Test
    public void testAutoSetDoorsN0Lt0() throws Exception {
        Auto a = new Auto();
        try {
            a.setDoorsNo(-5);
            Assert.fail("setDoorsNo accepts negative values");
        } catch (Exception e) {
            //ok!
            System.out.println("OK");
        }
    }

    @Test
    public void testSetDoorsNo() {
        Auto a = new Auto();
        try {
            a.setDoorsNo(4);
            Assert.assertEquals(4, a.getDoorsNo());
            System.out.println(a.getDoorsNo());
        } catch (Exception e) {

        }
    }
}
