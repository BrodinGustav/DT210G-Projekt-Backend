package DT210G_Projekt.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import DT210G_Projekt.model.User;
import DT210G_Projekt.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

      //Injecterar Dependencies
    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println("Loading user by email: " + email);

        
        //Hämtar User från DB
        Optional<User> userRespository = userRepository.findByEmail(email);
        
        //Om User ej hittas
        if(userRespository.isEmpty())
            throw new UsernameNotFoundException("Hittade ingen användare med email = " + email);
        
            //Returnera en User Details object genom den fetchade User informationen
        User user = userRespository.get();
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),    //räknas som username enligt Spring Security
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER"))); //Sätter specifik role till User
    }
}

