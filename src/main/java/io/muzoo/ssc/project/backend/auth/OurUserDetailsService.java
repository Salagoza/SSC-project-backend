package io.muzoo.ssc.project.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OurUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepoitory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        io.muzoo.ssc.project.backend.User user = userRepoitory.findFirstByUserName(username);
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());

        if(user != null){
            return User.withUsername(user.getUserName())
                    .password(user.getPassword())
                    .authorities("dfasdfasd").build();
        }else{
            throw new UsernameNotFoundException("Invalid username or password!!!!");
        }
    }
}
