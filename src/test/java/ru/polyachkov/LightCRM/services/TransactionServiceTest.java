package ru.polyachkov.LightCRM.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import ru.polyachkov.LightCRM.entities.Transaction;
import ru.polyachkov.LightCRM.entities.Seller;
import ru.polyachkov.LightCRM.additions.PaymentType;
import ru.polyachkov.LightCRM.repositories.TransactionRepo;
import ru.polyachkov.LightCRM.repositories.SellerRepo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private SellerRepo sellerRepo;

    private Seller testSeller;
    private static final LocalDateTime FIXED_DATE = LocalDateTime.of(2024, 10, 20, 12, 0, 0);

    @BeforeEach
    public void setUp() {
        testSeller = new Seller();
        testSeller.setName("Test Seller");
        testSeller.setContactInfo("test@example.com");
        testSeller.setRegistrationDate(FIXED_DATE);
        sellerRepo.save(testSeller);
    }

    @Test
    public void testGetAllTransactions() {
        Transaction transaction = createTransaction(new BigDecimal("100.00"), PaymentType.CARD, FIXED_DATE);
        List<Transaction> transactions = transactionService.getAllTransactions();
        assertThat(transactions).containsExactly(transaction);
    }

    @Test
    public void testGetTransactionById() {
        Transaction transaction = createTransaction(new BigDecimal("100.00"), PaymentType.CARD, FIXED_DATE);
        Transaction foundTransaction = transactionService.getTransactionById(transaction.getId());
        assertThat(foundTransaction).isEqualTo(transaction);
    }

    @Test
    public void testCreateTransaction() {
        Transaction newTransaction = createTransaction(new BigDecimal("200.00"), PaymentType.CASH, FIXED_DATE);
        Transaction createdTransaction = transactionService.createTransaction(newTransaction);
        assertThat(createdTransaction.getId()).isNotNull();
        assertThat(createdTransaction.getAmount()).isEqualTo(newTransaction.getAmount());
        assertThat(createdTransaction.getPaymentType()).isEqualTo(newTransaction.getPaymentType());
        assertThat(createdTransaction.getTransactionDate()).isEqualTo(newTransaction.getTransactionDate());
    }

    @Test
    public void testGetTransactionsBySeller() {
        Transaction transaction = createTransaction(new BigDecimal("100.00"), PaymentType.CARD, FIXED_DATE);
        List<Transaction> transactions = transactionService.getTransactionsBySeller(testSeller.getId());
        assertThat(transactions).containsExactly(transaction);
    }

    private Transaction createTransaction(BigDecimal amount, PaymentType paymentType, LocalDateTime transactionDate) {
        Transaction transaction = new Transaction();
        transaction.setSeller(testSeller);
        transaction.setAmount(amount);
        transaction.setPaymentType(paymentType);
        transaction.setTransactionDate(transactionDate);
        return transactionRepo.save(transaction);
    }
}
