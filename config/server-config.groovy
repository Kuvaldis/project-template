// create your environment properties in server-dev-config.groovy
db {
    url = 'jdbc:postgresql://localhost:5432/project'
    username = 'postgres'
    password = 'postgres'
}

jpa {
    showSql = false
}

common {
    devMode = false
    admin {
        name = 'admin'
        password = 'admin'
    }
    usernameSessionAttr = 'username'
}

server {
    port = 8080
}