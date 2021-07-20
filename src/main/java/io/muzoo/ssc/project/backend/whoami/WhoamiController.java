package io.muzoo.ssc.project.backend.whoami;

import io.muzoo.ssc.project.backend.User;
import io.muzoo.ssc.project.backend.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhoamiController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/api/whoami")
    public WhoamiDTO whoami() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            if (principal != null && principal instanceof org.springframework.security.core.userdetails.User) {
                //user logged in
                org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;
                User u = userRepo.findFirstByUserName(user.getUsername());

                return WhoamiDTO.builder()
                        .loggedIn(true)
                        .name(u.getUserName())
                        .role(u.getRole())
                        .username(u.getUserName())
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //user is not logged in
        return WhoamiDTO.builder()
                .loggedIn(false)
                .build();
    }
}
