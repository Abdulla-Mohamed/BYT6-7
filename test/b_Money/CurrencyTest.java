package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}
	
	@Test
	public void testGetRate() {
		assertEquals(Double.valueOf(0.15), SEK.getRate());
		assertEquals(Double.valueOf(0.20), DKK.getRate());
		assertEquals(Double.valueOf(1.5), EUR.getRate());
	}
	
	@Test
	public void testSetRate() {
		SEK.setRate(0.16);
		DKK.setRate(0.21);
		EUR.setRate(1.6);

		assertEquals(Double.valueOf(0.16), SEK.getRate());
		assertEquals(Double.valueOf(0.21), DKK.getRate());
		assertEquals(Double.valueOf(1.6), EUR.getRate());
	}
	
	@Test
	public void testGlobalValue() {
		assertEquals(Double.valueOf(150), SEK.universalValue(1000));
		assertEquals(Double.valueOf(400), DKK.universalValue(2000));
		assertEquals(Double.valueOf(4500), EUR.universalValue(3000));
	}
	
	@Test
	public void testValueInThisCurrency() {
		assertEquals(Double.valueOf(30000), SEK.valueInThisCurrency(3000, EUR));
		assertEquals(Double.valueOf(100), EUR.valueInThisCurrency(1000, SEK));
	}

}
