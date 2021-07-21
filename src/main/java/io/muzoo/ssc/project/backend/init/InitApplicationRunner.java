package io.muzoo.ssc.project.backend.init;

import io.muzoo.ssc.project.backend.entity.UserEntity;
import io.muzoo.ssc.project.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitApplicationRunner implements ApplicationRunner {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //adding default admin
        UserEntity admin = userRepository.findFirstByUserName("admin");
        if(admin == null){
            admin = new UserEntity();
            admin.setUserName("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            //admin.setRole("USER");
            userRepository.save(admin);
        }
    }
}
