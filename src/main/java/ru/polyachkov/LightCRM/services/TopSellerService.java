package ru.polyachkov.LightCRM.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.polyachkov.LightCRM.entities.Seller;
import ru.polyachkov.LightCRM.repositories.TransactionRepo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TopSellerService {

    private final TransactionRepo transactionRepo;

    @Autowired
    public TopSellerService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public Seller getTopSeller(LocalDateTime date, PeriodType periodType) {
        LocalDateTime start = getStartDate(date, periodType);
        LocalDateTime end = getEndDate(start, periodType);

        List<Object[]> results = transactionRepo.findTopSellersBetweenDates(start, end);

        if (!results.isEmpty()) {

            return (Seller) results.get(0)[0];
        }

        return null;
    }

    private LocalDateTime getStartDate(LocalDateTime date, PeriodType periodType) {
        return switch (periodType) {
            case DAY -> date.truncatedTo(ChronoUnit.DAYS);
            case MONTH -> date.withDayOfMonth(1).truncatedTo(ChronoUnit.DAYS);
            case QUARTER ->
                    date.withDayOfMonth(1).withMonth(((date.getMonthValue() - 1) / 3) * 3 + 1).truncatedTo(ChronoUnit.DAYS);
            case YEAR -> date.withDayOfYear(1).truncatedTo(ChronoUnit.DAYS);
            default -> throw new IllegalArgumentException("Unsupported period type: " + periodType);
        };
    }

    private LocalDateTime getEndDate(LocalDateTime start, PeriodType periodType) {
        return switch (periodType) {
            case DAY -> start.plusDays(1);
            case MONTH -> start.plusMonths(1);
            case QUARTER -> start.plusMonths(3);
            case YEAR -> start.plusYears(1);
            default -> throw new IllegalArgumentException("Unsupported period type: " + periodType);
        };
    }

    public List<Seller> getSellersWithTransactionsLessThan(LocalDateTime date, PeriodType periodType, double amount) {
        LocalDateTime start = getStartDate(date, periodType);
        LocalDateTime end = getEndDate(start, periodType);

        List<Object[]> results = transactionRepo.findSellersWithTransactionsLessThan(start, end, amount);

        return results.stream()
                .map(result -> (Seller) result[0])
                .toList();
    }


    public enum PeriodType {
        DAY, MONTH, QUARTER, YEAR
    }
}
