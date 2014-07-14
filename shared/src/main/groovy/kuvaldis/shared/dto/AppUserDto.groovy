package kuvaldis.shared.dto

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * User: NFadin
 * Date: 14.07.2014
 * Time: 12:42
 */
@EqualsAndHashCode
@ToString(includeNames = true)
class AppUserDto {
    Long id
    String username
    String password
    List<String> roles
}
