package order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import order_service.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}