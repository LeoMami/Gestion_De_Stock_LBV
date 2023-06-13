package com.LabelVie.GestionStockLbv.config;
import com.LabelVie.GestionStockLbv.Entity.User;
import com.LabelVie.GestionStockLbv.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         User user =userRepository.findByEmail(email);
         if(user != null){
            return new CostumUserDetails(user);
         }
         throw new UsernameNotFoundException("Utilisateur introuvable !");
    }
}