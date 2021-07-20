package io.muzoo.ssc.project.backend;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
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
        User admin = userRepository.findFirstByUserName("admin");
        if(admin == null){
            admin = new User();
            admin.setUserName("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            //admin.setRole("USER");
            userRepository.save(admin);
        }
    }
}
