package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		assertEquals("Should be 10000", Integer.valueOf(10000), SEK100.getAmount());
		assertEquals("Should be 1000", Integer.valueOf(1000), EUR10.getAmount());
		assertEquals("Should be 20000", Integer.valueOf(20000), SEK200.getAmount());
		assertEquals("Should be 2000", Integer.valueOf(2000), EUR20.getAmount());
		assertEquals("Should be 0", Integer.valueOf(0), SEK0.getAmount());
		assertEquals("Should be 0", Integer.valueOf(0), EUR0.getAmount());
		assertEquals("Should be -10000", Integer.valueOf(-10000), SEKn100.getAmount());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SEK100.getCurrency());
		assertEquals(EUR, EUR10.getCurrency());
		assertEquals(SEK, SEK200.getCurrency());
		assertEquals(EUR, EUR20.getCurrency());
		assertEquals(SEK, SEK0.getCurrency());
		assertEquals(EUR, EUR0.getCurrency());
		assertNotEquals(EUR, SEKn100.getCurrency());
	}

	@Test
	public void testToString() {
		assertEquals("100.00 SEK", SEK100.toString());
		assertEquals("10.00 EUR", EUR10.toString());
		assertEquals("200.00 SEK", SEK200.toString());
		assertEquals("20.00 EUR", EUR20.toString());
		assertEquals("0 EUR", EUR0.toString());
		assertEquals("0 SEK", SEK0.toString());
		assertEquals("-100.00 SEK", SEKn100.toString());
	}

	@Test
	public void testGlobalValue() {
		assertEquals(Double.valueOf(1500), SEK100.universalValue());
		assertEquals(Double.valueOf(1500), EUR10.universalValue());
		assertEquals(Double.valueOf(3000), SEK200.universalValue());
		assertEquals(Double.valueOf(3000), EUR20.universalValue());
		assertEquals(Double.valueOf(0), SEK0.universalValue());
		assertEquals(Double.valueOf(0), EUR0.universalValue());
		assertEquals(Double.valueOf(-1500), SEKn100.universalValue());
	}

	@Test
	public void testEqualsMoney() {
		assertTrue(SEK100.equals(EUR10));
		assertTrue(SEK200.equals(EUR20));
		assertTrue(SEK0.equals(EUR0));
		assertFalse(SEK100.equals(SEKn100));
	}

	@Test
	public void testAdd() {
		assertEquals(Integer.valueOf(20000), SEK100.add(EUR10).getAmount());
		assertEquals(Integer.valueOf(4000), EUR20.add(SEK200).getAmount());
		assertEquals(Integer.valueOf(0), SEK0.add(EUR0).getAmount());
		assertEquals(Integer.valueOf(0), SEK100.add(SEKn100).getAmount());
	}

	@Test
	public void testSub() {
		assertEquals(Integer.valueOf(0), SEK100.sub(EUR10).getAmount());
		assertEquals(Integer.valueOf(0), EUR20.sub(SEK200).getAmount());
		assertEquals(Integer.valueOf(0), SEK0.sub(EUR0).getAmount());
		assertEquals(Integer.valueOf(20000), SEK100.sub(SEKn100).getAmount());
	}

	@Test
	public void testIsZero() {
		assertFalse(SEK100.isZero());
		assertFalse(EUR10.isZero());
		assertFalse(SEK200.isZero());
		assertFalse(EUR20.isZero());
		assertTrue(SEK0.isZero());
		assertTrue(EUR0.isZero());
		assertFalse(SEKn100.isZero());
	}

	@Test
	public void testNegate() {
		assertEquals(Integer.valueOf(-10000), SEK100.negate().getAmount());
		assertEquals(Integer.valueOf(-1000), EUR10.negate().getAmount());
		assertEquals(Integer.valueOf(-20000), SEK200.negate().getAmount());
		assertEquals(Integer.valueOf(-2000), EUR20.negate().getAmount());
		assertEquals(Integer.valueOf(0), SEK0.negate().getAmount());
		assertEquals(Integer.valueOf(0), EUR0.negate().getAmount());
		assertEquals(Integer.valueOf(10000), SEKn100.negate().getAmount());
	}

	@Test
	public void testCompareTo() {
		assertEquals(-1, SEK100.compareTo(EUR20));
		assertEquals(1, EUR20.compareTo(SEK100));
		assertEquals(0, SEK0.compareTo(EUR0));
		assertEquals(1, SEK100.compareTo(SEKn100));
	}
}
