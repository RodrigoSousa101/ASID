package order_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import order_service.entity.ShippingOrder;

@Repository
public interface ShippingOrderRepository extends JpaRepository<ShippingOrder,Long>{
 
}
