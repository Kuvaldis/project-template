package kuvaldis.security.util

import kuvaldis.model.data.domain.AppUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

/**
 * @author Kuvaldis
 * Create date: 11.07.2014 22:45
 */
final class RoleUtil {

    private RoleUtil() {}

    static Collection<? extends GrantedAuthority> toAuthorities(Collection<AppUser.Role> roles) {
         roles.collect {
             new SimpleGrantedAuthority(it.name())
         }
    }
}
