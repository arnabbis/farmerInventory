package com.farmerManagement.farmer.repository;

import com.farmerManagement.farmer.entity.vegetableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface vegetableRepository extends JpaRepository<vegetableEntity, Integer> {
    @Query ("SELECT v FROM vegetableEntity  v WHERE v.vegetableName = :name")
    List<vegetableEntity> findByName(@Param("name") String name);

    @Modifying
    @Query("UPDATE vegetableEntity v SET v.vegetableQuantity = :quantity WHERE v.id = :id")
    void updateQuantity(@Param("id") Integer id, @Param("quantity") int quantity);

}
