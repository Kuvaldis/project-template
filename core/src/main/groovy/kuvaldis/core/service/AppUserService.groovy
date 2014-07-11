package kuvaldis.core.service

import groovy.util.logging.Slf4j
import kuvaldis.model.data.domain.AppUser
import kuvaldis.model.data.repository.AppUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * User: NFadin
 * Date: 08.07.2014
 * Time: 16:14
 */
@Service
@Slf4j
@Transactional
class AppUserService {

    @Autowired
    AppUserRepository appUserRepository
    @Autowired
    PasswordEncoder appUserPasswordEncoder

    AppUser create(final AppUser appUser) {
        final AppUser userToSave = new AppUser(appUser)
        userToSave.password = appUserPasswordEncoder.encode(userToSave.password)
        appUserRepository.save(userToSave)
    }

    AppUser find(String username) {
        appUserRepository.findByUsername(username)
    }
}
