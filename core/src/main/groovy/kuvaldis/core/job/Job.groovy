package kuvaldis.core.job

/**
 * User: NFadin
 * Date: 08.07.2014
 * Time: 16:49
 */
interface Job {
    JobPriority getPriority()
    void runJob() throws Exception
}