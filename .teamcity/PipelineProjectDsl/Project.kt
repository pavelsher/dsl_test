package PipelineProjectDsl

import PipelineProjectDsl.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.Project
import jetbrains.buildServer.configs.kotlin.v10.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v10.projectFeatures.VersionedSettings.*
import jetbrains.buildServer.configs.kotlin.v10.projectFeatures.versionedSettings

object Project : Project({
    uuid = "e2421449-e3c1-4ddf-ad68-3289e7d2db42"
    extId = "PipelineProjectDsl"
    parentId = "_Root"
    name = "Pipeline project (DSL)"

    buildType(PipelineProjectDsl_Package)
    buildType(PipelineProjectDsl_Deploy)
    buildType(PipelineProjectDsl_Compile)
    buildType(PipelineProjectDsl_TestsWindows)
    buildType(PipelineProjectDsl_TestsLinux)

    features {
        versionedSettings {
            id = "PROJECT_EXT_1"
            mode = VersionedSettings.Mode.ENABLED
            buildSettingsMode = VersionedSettings.BuildSettingsMode.USE_CURRENT_SETTINGS
            rootExtId = "HttpsGithubComPavelsherDslTest"
            showChanges = false
            settingsFormat = VersionedSettings.Format.KOTLIN
        }
    }
    buildTypesOrder = arrayListOf(PipelineProjectDsl.buildTypes.PipelineProjectDsl_Deploy, PipelineProjectDsl.buildTypes.PipelineProjectDsl_Package, PipelineProjectDsl.buildTypes.PipelineProjectDsl_TestsLinux, PipelineProjectDsl.buildTypes.PipelineProjectDsl_TestsWindows, PipelineProjectDsl.buildTypes.PipelineProjectDsl_Compile)
})
