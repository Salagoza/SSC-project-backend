package io.muzoo.ssc.project.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findFirstByUserName(String username);
}
