package kuvaldis.model.data.repository

import kuvaldis.model.data.domain.AppUser
import org.springframework.data.jpa.repository.JpaRepository

/**
 * User: NFadin
 * Date: 08.07.2014
 * Time: 16:05
 */
interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username)
}
