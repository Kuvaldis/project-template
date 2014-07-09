package kuvaldis.security.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * @author Kuvaldis
 * Create date: 10.07.2014 0:19
 */
@Service
class UserService implements UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null
    }
}
