package ru.polyachkov.LightCRM.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.polyachkov.LightCRM.entities.Seller;
import ru.polyachkov.LightCRM.repositories.SellerRepo;

import java.util.List;

@Service
public class SellerService {

    private final SellerRepo sellerRepo;

    @Autowired
    public SellerService(SellerRepo sellerRepo) {
        this.sellerRepo = sellerRepo;
    }


    public List<Seller> getAllSellers() {
        return sellerRepo.findAll();
    }


    public Seller getSellerById(Long id) {
        return sellerRepo.findById(id).orElse(null);
    }


    public Seller createSeller(Seller seller) {
        return sellerRepo.save(seller);
    }


    public Seller updateSeller(Long id, Seller sellerDetails) {
        return sellerRepo.findById(id).map(seller -> {
            seller.setName(sellerDetails.getName());
            return sellerRepo.save(seller);
        }).orElse(null);
    }


    public boolean deleteSeller(Long id) {
        return sellerRepo.findById(id).map(seller -> {
            sellerRepo.delete(seller);
            return true;
        }).orElse(false);
    }
}
