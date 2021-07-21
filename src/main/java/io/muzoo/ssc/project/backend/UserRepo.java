package io.muzoo.ssc.project.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {
    UserEntity findFirstByUserName(String username);
}
