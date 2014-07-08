package kuvaldis.core.job

import groovy.util.logging.Slf4j
import kuvaldis.core.service.AppUserService
import kuvaldis.model.data.domain.AppUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * User: NFadin
 * Date: 16.04.14
 * Time: 13:07
 */
@Slf4j
@Component
class AddAdminJob extends AbstractDeployJob {

    @Autowired
    private AppUserService appUserService

    @Value('${common.admin.name}')
    private String adminName
    @Value('${common.admin.password}')
    private String adminPassword

    @Override
    @Transactional
    void runJob() throws Exception {
        if (!appUserService.find(adminName)) {
            appUserService.create(new AppUser(
                    username: adminName,
                    password: adminPassword,
                    roles: [AppUser.Role.ADMIN]
            ))
        }
    }
}
