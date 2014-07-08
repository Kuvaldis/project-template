package migration.changelog

databaseChangeLog {
    preConditions {
        dbms(type: 'postgresql')
    }

    include file: 'classpath:migration/changelog/changelog-0-1.groovy'
}