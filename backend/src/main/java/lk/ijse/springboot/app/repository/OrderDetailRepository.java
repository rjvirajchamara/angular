package lk.ijse.springboot.app.repository;


import lk.ijse.springboot.app.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDetailRepository extends JpaRepository<OrderDetails,Integer> {

    @Modifying(clearAutomatically = true)
    @Query("update Items set qty_on_hand=:qty_on_hand where code=:conde")
    void UpdateItem(@Param("qty_on_hand") double qty_on_hand, @Param("code") String code);

}
