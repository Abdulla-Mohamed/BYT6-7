package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	Money swedish, danish;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika",SEK);
		SweBank.openAccount("Bob",SEK);
		Nordea.openAccount("Bob",SEK);
		DanskeBank.openAccount("Gertrud",DKK);
		swedish = new Money(1000,SEK);
		danish = new Money(2000,DKK);
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
		assertEquals("DanskeBank", DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
		assertEquals(DKK, DanskeBank.getCurrency());
		assertEquals(SEK, Nordea.getCurrency());
	}

	@Test(expected=AccountExistsException.class)
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		SweBank.openAccount("Ulrika", SEK);
		SweBank.openAccount("Bob", SEK);
		Nordea.openAccount("Bob", SEK);
		DanskeBank.openAccount("Gertrud", DKK);
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", swedish);
		SweBank.deposit("Bob", swedish);
		DanskeBank.deposit("Gertrud", danish);
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.withdraw("Ulrika", swedish);
		SweBank.withdraw("Bob", swedish);
		DanskeBank.withdraw("Gertrud", danish);
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", swedish);
		SweBank.deposit("Bob", swedish);
		DanskeBank.deposit("Gertrud", danish);

		assertEquals(swedish.getAmount(), SweBank.getBalance("Ulrika"));
		assertEquals(swedish.getAmount(), SweBank.getBalance("Bob"));
		assertEquals(danish.getAmount(), DanskeBank.getBalance("Gertrud"));
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.transfer("Ulrika", "Bob", swedish);
		assertEquals(swedish.getAmount(), SweBank.getBalance("Bob"));
		assertEquals(swedish.negate().getAmount(), SweBank.getBalance("Ulrika"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.addTimedPayment("Bob", "1", 1, 1, swedish, DanskeBank, "Gertrud");
		SweBank.tick();
		SweBank.removeTimedPayment("Bob", "1");
	}
}
