package ru.polyachkov.LightCRM.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.polyachkov.LightCRM.entities.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    @Query("SELECT t.seller, SUM(t.amount) FROM Transaction t " +
            "WHERE t.transactionDate BETWEEN :start AND :end " +
            "GROUP BY t.seller ORDER BY SUM(t.amount) DESC")
    List<Object[]> findTopSellersBetweenDates(@Param("start") LocalDateTime start,
                                              @Param("end") LocalDateTime end);

    @Query("SELECT t.seller, SUM(t.amount) FROM Transaction t " +
            "WHERE t.transactionDate BETWEEN :start AND :end " +
            "GROUP BY t.seller HAVING SUM(t.amount) < :amount ORDER BY SUM(t.amount) DESC")
    List<Object[]> findSellersWithTransactionsLessThan(@Param("start") LocalDateTime start,
                                                       @Param("end") LocalDateTime end,
                                                       @Param("amount") double amount);

    List<Transaction> findBySellerId(Long sellerId);
}
