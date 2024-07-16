package com.farmerManagement.farmer.repository;

import com.farmerManagement.farmer.entity.vegetableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface vegetableRepository extends JpaRepository<vegetableEntity, Integer> {
    @Query ("SELECT v FROM vegetableEntity  v WHERE v.vegetableName = :name")
    List<vegetableEntity> findByName(@Param("name") String name);

}
