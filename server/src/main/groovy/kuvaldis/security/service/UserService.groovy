package kuvaldis.security.service

import groovy.util.logging.Slf4j
import kuvaldis.core.service.AppUserService
import kuvaldis.model.data.domain.AppUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import static kuvaldis.security.util.RoleUtil.toAuthorities

/**
 * @author Kuvaldis
 * Create date: 10.07.2014 0:19
 */
@Service
@Slf4j
class UserService implements UserDetailsService {

    @Autowired
    AppUserService appUserService

    @Transactional
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        def appUser = appUserService.find(username)
        if (!appUser) {
            log.info("User '$username' wasn't found")
            return null
        }
        userDetails(appUser)
    }

    static UserDetails userDetails(AppUser appUser) {
        new User(
                appUser.username,
                appUser.password,
                toAuthorities(appUser.roles)
        )
    }
}
