package io.muzoo.ssc.project.backend.repository;

import io.muzoo.ssc.project.backend.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RestaurantRepo extends JpaRepository<RestaurantEntity,Long> {

}
