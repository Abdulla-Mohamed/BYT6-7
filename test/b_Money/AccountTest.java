package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	Money swedish;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice", SEK);
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
		swedish = new Money(1000,SEK);
	}
	
	@Test
	public void testAddRemoveTimedPayment() {

		testAccount.addTimedPayment("1",1,2,swedish,SweBank,"Alice");
		assertTrue(testAccount.timedPaymentExists("1"));
		testAccount.removeTimedPayment("1");
		assertFalse(testAccount.timedPaymentExists("1"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("1",1,2,swedish,SweBank,"Alice");
		assertTrue(testAccount.timedPaymentExists("1"));
	}

	@Test
	public void testAddWithdraw() {
		testAccount.deposit(swedish);
		assertEquals(Integer.valueOf(10001000), testAccount.getBalance().getAmount());

		testAccount.withdraw(swedish);
		assertEquals(Integer.valueOf(10000000),testAccount.getBalance().getAmount());
	}
	
	@Test
	public void testGetBalance() {
		assertEquals(Integer.valueOf(10000000),testAccount.getBalance().getAmount());
	}
}
