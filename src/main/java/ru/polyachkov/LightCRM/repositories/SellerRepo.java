package ru.polyachkov.LightCRM.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.polyachkov.LightCRM.entities.Seller;

public interface SellerRepo extends JpaRepository<Seller, Long> {
}
