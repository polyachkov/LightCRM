package ru.polyachkov.LightCRM.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.polyachkov.LightCRM.entities.Seller;
import ru.polyachkov.LightCRM.services.SellerService;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    // Список всех продавцов
    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers() {
        List<Seller> sellers = sellerService.getAllSellers();
        return ResponseEntity.ok(sellers);
    }

    // Инфо о конкретном продавце
    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) {
        Seller seller = sellerService.getSellerById(id);
        return seller != null ? ResponseEntity.ok(seller) : ResponseEntity.notFound().build();
    }

    // Создать нового продавца
    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) {
        Seller createdSeller = sellerService.createSeller(seller);
        return ResponseEntity.ok(createdSeller);
    }

    // Обновить информацию о продавце
    @PutMapping("/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable Long id, @RequestBody Seller sellerDetails) {
        Seller updatedSeller = sellerService.updateSeller(id, sellerDetails);
        return updatedSeller != null ? ResponseEntity.ok(updatedSeller) : ResponseEntity.notFound().build();
    }

    // Удалить продавца
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        boolean isDeleted = sellerService.deleteSeller(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
