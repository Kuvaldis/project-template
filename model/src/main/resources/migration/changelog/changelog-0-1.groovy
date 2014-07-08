package migration.changelog

databaseChangeLog {
    changeSet(author: 'nfadin', id: '00000', context: 'design', dbms: 'postgresql') {
        createTable(tableName: 'users') {
            column(name: 'id', type: 'INT')
        }
    }
}
