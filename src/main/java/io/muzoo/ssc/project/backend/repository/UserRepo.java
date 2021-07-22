package io.muzoo.ssc.project.backend.repository;

import io.muzoo.ssc.project.backend.entity.RestaurantEntity;
import io.muzoo.ssc.project.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {
    UserEntity findFirstByUserName(String username);
}
