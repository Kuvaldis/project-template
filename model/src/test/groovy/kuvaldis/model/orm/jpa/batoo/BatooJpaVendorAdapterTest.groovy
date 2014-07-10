package kuvaldis.model.orm.jpa.batoo

import spock.lang.Specification

/**
 * @author Kuvaldis
 * Create date: 10.07.2014 22:05
 */
class BatooJpaVendorAdapterTest extends Specification {
    def "persistence provider should be not null" () {
        given:
            def a = new BatooJpaVendorAdapter()
        when:
            def p = a.persistenceProvider
        then:
            p != null
    }

    def "jpa dialect should be not null" () {
        given:
            def a = new BatooJpaVendorAdapter()
        when:
            def d = a.jpaDialect
        then:
            d != null
    }

    def "jpa property map should not be null" () {
        given:
            def a = new BatooJpaVendorAdapter()
        when:
            def pm = a.jpaPropertyMap
        then:
            pm != null
    }

    def "jpa property org.batoo.jpa.ddl should be UPDATE if generate ddl is true" () {
        given:
            def a = new BatooJpaVendorAdapter()
            a.generateDdl = true
        when:
            def pm = a.jpaPropertyMap
        then:
            pm['org.batoo.jpa.ddl'] == 'UPDATE'
    }

    def "jpa property org.batoo.jpa.ddl should be null by default" () {
        given:
            def a = new BatooJpaVendorAdapter()
        when:
            def pm = a.jpaPropertyMap
        then:
            pm['org.batoo.jpa.ddl'] == null
    }

    def "jpa property org.batoo.jpa.sql_logging should be UPDATE if generate ddl is true" () {
        given:
            def a = new BatooJpaVendorAdapter()
            a.showSql = true
        when:
            def pm = a.jpaPropertyMap
        then:
            pm['org.batoo.jpa.sql_logging'] == 'STDOUT'
    }

    def "jpa property org.batoo.jpa.sql_logging should be null by default" () {
        given:
            def a = new BatooJpaVendorAdapter()
        when:
            def pm = a.jpaPropertyMap
        then:
            pm['org.batoo.jpa.sql_logging'] == null
    }
}
