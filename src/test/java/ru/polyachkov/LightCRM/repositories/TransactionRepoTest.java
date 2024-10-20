package ru.polyachkov.LightCRM.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.polyachkov.LightCRM.entities.Seller;
import ru.polyachkov.LightCRM.entities.Transaction;
import ru.polyachkov.LightCRM.additions.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class TransactionRepoTest {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private SellerRepo sellerRepo;

    @Test
    public void testFindBySellerId() {

        LocalDateTime fixedDateTime = LocalDateTime.of(2024, 10, 20, 23, 19, 43, 532347000);


        Seller testSeller = Seller.builder()
                .name("Test Seller")
                .contactInfo("test@example.com")
                .registrationDate(fixedDateTime)
                .build();
        sellerRepo.save(testSeller);


        Transaction transaction = new Transaction();
        transaction.setSeller(testSeller);
        transaction.setAmount(new BigDecimal("100.00"));
        transaction.setPaymentType(PaymentType.CARD);
        transaction.setTransactionDate(fixedDateTime);

        transactionRepo.save(transaction);


        assertThat(transactionRepo.findBySellerId(testSeller.getId())).containsExactly(transaction);
    }

}
