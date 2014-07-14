package kuvaldis.model.data.domain

import groovy.transform.AutoClone
import groovy.transform.AutoCloneStyle
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TupleConstructor

import javax.persistence.*

/**
 * @author Kuvaldis
 * Create date: 07.07.2014 23:19
 */
@Entity
@Table(name = 'app_user')
@SequenceGenerator(name = 'app_user_seq', sequenceName = 'app_user_seq')
@EqualsAndHashCode
@ToString(includeNames = true)
@AutoClone(style = AutoCloneStyle.COPY_CONSTRUCTOR)
@TupleConstructor
class AppUser {
    @Id
    @GeneratedValue(generator = 'app_user_seq', strategy = GenerationType.SEQUENCE)
    Long id
    String username
    @Column(name = 'pwd')
    String password
    @ElementCollection(targetClass = Role)
    @CollectionTable(name = 'app_user_role', joinColumns = @JoinColumn(name = 'app_user_id'))
    @Enumerated(EnumType.STRING)
    @Column(name = 'role_name')
    Set<Role> roles

    enum Role {
        USER, ADMIN
    }

    Map<String, ?> toMap() {
        [
                id: id,
                username: username,
                password: password,
                roles: roles*.name()
        ]
    }
}
