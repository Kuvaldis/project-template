package kuvaldis.core.service

import groovy.util.logging.Slf4j
import kuvaldis.model.data.domain.AppUser
import kuvaldis.model.data.repository.AppUserRepository
import org.springframework.beans.factory.annotation.Autowired
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
    private AppUserRepository appUserRepository

    void create(AppUser appUser) {
        appUserRepository.save(appUser)
    }

    AppUser find(String username) {
        appUserRepository.findByUsername(username)
    }
}
