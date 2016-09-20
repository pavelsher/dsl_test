package PipelineProjectDsl.buildTypes

import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.ScriptBuildStep
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.ScriptBuildStep.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.script

object PipelineProjectDsl_TestsWindows : BuildType({
    uuid = "5471059f-6554-4bf7-9907-de3759964278"
    extId = "PipelineProjectDsl_TestsWindows"
    name = "Tests (Windows)"

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
        }
    }
})
