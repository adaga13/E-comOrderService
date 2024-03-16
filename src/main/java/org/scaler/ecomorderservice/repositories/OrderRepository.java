package org.scaler.ecomorderservice.repositories;

import org.scaler.ecomorderservice.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Override
    Optional<Order> findById(Long orderId);

    List<Order> findAllByUserId(Long userId);
}
