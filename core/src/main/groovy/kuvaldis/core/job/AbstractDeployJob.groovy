package kuvaldis.core.job

/**
 * User: NFadin
 * Date: 16.04.14
 * Time: 12:33
 */
abstract class AbstractDeployJob implements DeployJob {
    @Override
    JobPriority getPriority() {
        JobPriority.DEFAULT
    }
}
