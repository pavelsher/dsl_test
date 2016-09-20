package PipelineProjectDsl.buildTypes

import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.ScriptBuildStep
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.ScriptBuildStep.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.script

object PipelineProjectDsl_TestsLinux : BuildType({
    uuid = "d4c009ab-cd35-41bc-8b64-65c4858ea431"
    extId = "PipelineProjectDsl_TestsLinux"
    name = "Tests (Linux)"

    steps {
        script {
            scriptContent = """echo "##teamcity[testStarted name='test1']"
echo "##teamcity[testFinished name='test1']"
echo "##teamcity[testStarted name='test2']"
echo "##teamcity[testFinished name='test2']"
echo "##teamcity[testStarted name='test3']"
echo "##teamcity[testFinished name='test3']"
echo "##teamcity[testStarted name='test4']"
echo "##teamcity[testFinished name='test4']""""
        }
    }

    dependencies {
        dependency(PipelineProjectDsl.buildTypes.PipelineProjectDsl_Compile) {
            snapshot {
            }
            artifacts {
                artifactRules = "**/*.jar => target"
            }
        }
    }
})
