package io.muzoo.ssc.project.backend.repository;

import io.muzoo.ssc.project.backend.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepo extends JpaRepository<RestaurantEntity,Long> {
    List<RestaurantEntity> findTop10ByOrderByIdAsc();
}
