package PipelineProjectDsl.buildTypes

import jetbrains.buildServer.configs.kotlin.v10.*

object PipelineProjectDsl_Compile : BuildType({
    uuid = "1b19873d-85c8-4c20-b3bc-ab67c2aedcd6"
    extId = "PipelineProjectDsl_Compile"
    name = "Compile"

    artifactRules = "**/*.jar"
})
