package org.typo3.chefci.v2.cookbook.stages

import org.typo3.chefci.helpers.JenkinsHelper
import org.typo3.chefci.helpers.Slack
import org.typo3.chefci.v2.shared.stages.AbstractStage

class GitCheckout extends AbstractStage {

    GitCheckout(Object script, JenkinsHelper jenkinsHelper, Slack slack) {
        super(script, 'Git Checkout', jenkinsHelper, slack)
    }

    @Override
    void execute() {
        script.stage(stageName) {
            script.node {
                script.checkout(script.scm)
                // we e.g. have a .kitchen.docker.yml left from the last run. Remove that.
                script.sh("git clean -fdx")
            }
        }
    }

}
