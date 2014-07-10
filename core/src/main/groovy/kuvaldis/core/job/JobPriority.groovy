package kuvaldis.core.job

/**
 * User: NFadin
 * Date: 16.04.14
 * Time: 12:33
 */
enum JobPriority {

    SMALL(9),
    MEDIUM(4),
    BIG(0),
    DEFAULT(MEDIUM.priority)

    final Integer priority

    JobPriority(Integer priority) {
        this.priority = priority
    }
}