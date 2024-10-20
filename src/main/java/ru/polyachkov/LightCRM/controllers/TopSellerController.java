package ru.polyachkov.LightCRM.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.polyachkov.LightCRM.entities.Seller;
import ru.polyachkov.LightCRM.services.TopSellerService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/top-sellers")
public class TopSellerController {

    private final TopSellerService topSellerService;

    @Autowired
    public TopSellerController(TopSellerService topSellerService) {
        this.topSellerService = topSellerService;
    }

    @GetMapping
    public ResponseEntity<Seller> getTopSeller(
            @RequestParam("date") LocalDateTime date,
            @RequestParam("period") TopSellerService.PeriodType periodType) {

        Seller topSeller = topSellerService.getTopSeller(date, periodType);
        if (topSeller != null) {
            return ResponseEntity.ok(topSeller);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/under-amount")
    public ResponseEntity<List<Seller>> getSellersWithTransactionsLessThan(
            @RequestParam("date") LocalDateTime date,
            @RequestParam("period") TopSellerService.PeriodType periodType,
            @RequestParam("amount") double amount) {

        List<Seller> sellers = topSellerService.getSellersWithTransactionsLessThan(date, periodType, amount);
        if (!sellers.isEmpty()) {
            return ResponseEntity.ok(sellers);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


}
