package SopraAJC.NotreProjet.services;

import SopraAJC.NotreProjet.models.Compte;
import SopraAJC.NotreProjet.models.CompteUserDetails;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CompteRepository compteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Compte> opt = compteRepository.findByUsername(username);

        if (opt.isPresent()){
            return new CompteUserDetails(opt.get());
        }

        throw new UsernameNotFoundException("");

    }
}
