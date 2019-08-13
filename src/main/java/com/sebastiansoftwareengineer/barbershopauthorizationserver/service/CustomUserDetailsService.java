package com.sebastiansoftwareengineer.barbershopauthorizationserver.service;

import com.sebastiansoftwareengineer.barbershopauthorizationserver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return accountRepository.findByEmail(username)
                    .map(account->new User(account.getUsername(),
                            account.getPassword(),
                            account.getDetails().get("enabled"),
                            account.getDetails().get("accountNonExpired"),
                            account.getDetails().get("credentialsNonExpired"),
                            account.getDetails().get("accountNonLocked"),
                            AuthorityUtils.createAuthorityList("write", "read")
                            ))
                    .orElseThrow(()-> new UsernameNotFoundException("Email not found"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
