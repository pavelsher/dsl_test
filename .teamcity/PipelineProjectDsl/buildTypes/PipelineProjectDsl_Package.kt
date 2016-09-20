package PipelineProjectDsl.buildTypes

import jetbrains.buildServer.configs.kotlin.v10.*

object PipelineProjectDsl_Package : BuildType({
    uuid = "b82b7cf1-e0d3-46db-8a78-bce6954dfe83"
    extId = "PipelineProjectDsl_Package"
    name = "Package"

    artifactRules = """C:\temp\app.war"""
    buildNumberPattern = "1.0.%build.counter%"

    params {
        param("teamcity.ui.runButton.caption", "Package")
    }

    dependencies {
        dependency(PipelineProjectDsl.buildTypes.PipelineProjectDsl_TestsLinux) {
            snapshot {
            }
        }
        dependency(PipelineProjectDsl.buildTypes.PipelineProjectDsl_TestsWindows) {
            snapshot {
            }
        }
    }
})
