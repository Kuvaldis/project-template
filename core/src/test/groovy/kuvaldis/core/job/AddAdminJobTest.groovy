package kuvaldis.core.job

import kuvaldis.core.service.AppUserService
import kuvaldis.model.data.domain.AppUser
import spock.lang.Specification

/**
 * User: NFadin
 * Date: 09.07.2014
 * Time: 17:25
 */
class AddAdminJobTest extends Specification {

    private adminName = 'admin'
    private adminPassword = 'admin'

    def "do not create admin if exists"() {
        given:
            def appUserService = Mock(AppUserService)
            appUserService.find(adminName) >> new AppUser()
            def j = job(appUserService)
        when:
            j.runJob()
        then:
            0 * appUserService.create(_)
    }

    def "create admin if not exists"() {
        given:
            def appUserService = Mock(AppUserService)
            appUserService.find(adminName) >> null
            def j = job(appUserService)
        when:
            j.runJob()
        then:
            1 * appUserService.find(_)
        then:
            1 * appUserService.create(_)
    }

    private AddAdminJob job(AppUserService appUserService) {
        new AddAdminJob(
                adminName: adminName,
                adminPassword: adminPassword,
                appUserService: appUserService
        )
    }
}
