import Model.Polynomial;
import Model.PolynomialModel;
import org.junit.*;

import static org.junit.Assert.*;

public class JUnitTest {

    private static PolynomialModel m;
    private static int nrTesteExecutate = 0;
    private static int nrTesteCuSucces = 0;

    public JUnitTest()
    {
        System.out.println("Constructor inaintea fiecarui test!");
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("O singura data inaintea executiei setului de teste din clasa!");
        m = new PolynomialModel();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("O singura data dupa terminarea executiei setului de teste din clasa!");
        System.out.println("S-au executat " + nrTesteExecutate + " teste din care "+ nrTesteCuSucces + " au avut succes!");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("Incepe un nou test!");
        nrTesteExecutate++;
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("S-a terminat testul curent!");
    }

    @Test
    public void testResetGetValue() {

        nrTesteCuSucces++;
    }

    @Test
    public void testAdd() {
        Polynomial a = new Polynomial("5x^2+2-x^3");
        Polynomial b = new Polynomial("-x^2+x");
        m.add(a,b);
        Polynomial r = m.getResult();
        Polynomial result = new Polynomial("2+x+4x^2-x^3");
        r.sort();
        assertEquals(result.toString(),r.toString());
        nrTesteCuSucces++;
    }

    @Test
    public void testSub() {
        Polynomial a = new Polynomial("5x^2+2-x^3");
        Polynomial b = new Polynomial("-x^2+x");
        m.sub(a,b);
        Polynomial r = m.getResult();
        Polynomial result = new Polynomial("2-x+6x^2-x^3");
        r.sort();
        assertEquals(result.toString(),r.toString());
        nrTesteCuSucces++;
    }

    @Test
    public void testMul() {
        Polynomial a = new Polynomial("5x^2+2-x^3");
        Polynomial b = new Polynomial("-x^2+x");
        m.mul(a,b);
        Polynomial r = m.getResult();
        Polynomial result = new Polynomial("2x-2x^2+5x^3+3x^4+x^5");
        r.sort();
        assertEquals(result.toString(),r.toString());
        nrTesteCuSucces++;
    }

    @Test
    public void testDiv() {
        Polynomial a = new Polynomial("5x^2+2-x^3");
        Polynomial b = new Polynomial("-x^2+x");
        m.div(a,b);
        Polynomial r = m.getResult();
        Polynomial result = new Polynomial("-4+x");
        r.sort();
        b.sort();
        Polynomial reminder = new Polynomial("2+4x");
        assertEquals(result.toString(),r.toString());
        assertEquals(reminder.toString(),b.toString());
        nrTesteCuSucces++;
    }

    @Test
    public void testDeriv() {
        Polynomial a = new Polynomial("5x^2+2-x^3");
        m.deriv(a);
        Polynomial r = m.getResult();
        Polynomial result = new Polynomial("10x-3x^2");
        r.sort();
        assertEquals(result.toString(),r.toString());
        nrTesteCuSucces++;
    }

    @Test
    public void testIntegr() {
        Polynomial a = new Polynomial("5x^2+2-x^3");
        m.inte(a);
        Polynomial r = m.getResult();
        Polynomial result = new Polynomial("2x+1.6666666666666667x^3-0.25x^4");
        r.sort();
        assertEquals(result.toString(),r.toString());
        nrTesteCuSucces++;
    }

}
