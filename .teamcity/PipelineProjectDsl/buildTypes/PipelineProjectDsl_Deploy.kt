package PipelineProjectDsl.buildTypes

import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.ScriptBuildStep
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.ScriptBuildStep.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.script

object PipelineProjectDsl_Deploy : BuildType({
    uuid = "141f110c-8b99-40a0-ba5d-f06a83db696f"
    extId = "PipelineProjectDsl_Deploy"
    name = "Deploy"

    buildNumberPattern = "%build.counter% (%dep.PipelineProjectDsl_Package.build.number%)"

    params {
        password("password", "zxx775d03cbe80d301b", label = "Deployment password", display = ParameterDisplay.PROMPT)
        param("teamcity.ui.runButton.caption", "Deploy")
    }

    steps {
        script {
            scriptContent = """echo "##teamcity[buildStatus text='Successfully deployed']""""
        }
    }

    dependencies {
        dependency(PipelineProjectDsl.buildTypes.PipelineProjectDsl_Package) {
            snapshot {
                reuseBuilds = ReuseBuilds.NO
            }
        }
    }
})
