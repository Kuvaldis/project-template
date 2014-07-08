package migration.changelog

databaseChangeLog {
    changeSet(author: 'nfadin', id: '0.1.00001.01', context: 'design', dbms: 'postgresql') {
        createTable(tableName: 'app_user') {
            column(name: 'id', type: 'INT') {
                constraints(nullable: false)
            }
            column(name: 'username', type: 'VARCHAR(100)') {
                constraints(nullable: false)
            }
            column(name: 'password', type: 'VARCHAR(200)') {
                constraints(nullable: false)
            }
        }
    }
    changeSet(author: 'nfadin', id: '0.1.00001.02') {
        addPrimaryKey(tableName: 'app_user', columnNames: 'id', constraintName: 'app_user_pk')
    }
    changeSet(author: 'nfadin', id: '0.1.00001.03') {
        createSequence(sequenceName: 'app_user_seq', startValue: '1', incrementBy: '1')
    }
    changeSet(author: 'nfadin', id: '0.1.00001.04', context: 'design', dbms: 'postgresql') {
        createTable(tableName: 'app_user_role') {
            column(name: 'app_user_id', type: 'INT') {
                constraints(nullable: false)
            }
            column(name: 'role', type: 'VARCHAR(100)') {
                constraints(nullable: false)
            }
        }
    }
    changeSet(author: "nfadin", id: "0.1.00001.05") {
        addForeignKeyConstraint(baseTableName: 'app_user_role', baseColumnNames: 'app_user_id',
                referencedTableName: 'app_user', referencedColumnNames: 'id',
                constraintName: 'app_user_role_app_user_fk')
    }
}
