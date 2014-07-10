package kuvaldis.model.orm.jpa.batoo

import org.batoo.jpa.core.BatooPersistenceProvider
import org.springframework.orm.jpa.JpaDialect
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter

import javax.persistence.spi.PersistenceProvider

/**
 * @author Kuvaldis
 * Create date: 07.07.2014 23:03
 */
class BatooJpaVendorAdapter extends AbstractJpaVendorAdapter {

    private final jpaPersistenceProvider = new BatooPersistenceProvider()
    private final jpaDialect = new BatooJpaDialect()

    final String persistenceProviderRootPackage = 'org.batoo.jpa'

    @Override
    PersistenceProvider getPersistenceProvider() {
        jpaPersistenceProvider
    }

    @Override
    JpaDialect getJpaDialect() {
        jpaDialect
    }

    @Override
    Map<String, ?> getJpaPropertyMap() {
        final jpaProperties = [:]
        if (generateDdl) {
            jpaProperties << ['org.batoo.jpa.ddl': 'UPDATE']
        }
        if (showSql) {
            jpaProperties << ['org.batoo.jpa.sql_logging': 'STDOUT']
        }
        jpaProperties
    }
}
