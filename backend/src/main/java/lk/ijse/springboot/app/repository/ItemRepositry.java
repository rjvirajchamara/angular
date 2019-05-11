package lk.ijse.springboot.app.repository;

import lk.ijse.springboot.app.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepositry extends JpaRepository<Items,String> {

    @Query("SELECT count(i.code) FROM Items i")
    long getTotalItems();

    @Modifying(clearAutomatically = true)
    @Query("update Items set qty_on_hand=:qty_on_hand where code=:code")
    void UpdateItem(@Param("qty_on_hand") double qty_on_hand,@Param("code") String code);

}
