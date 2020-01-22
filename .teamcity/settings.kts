import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.MavenBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot
import java.util.concurrent.TimeUnit.MINUTES
import java.util.concurrent.TimeUnit.SECONDS
version = "2019.2"
project {
    subProject {
        name = "Maven"
        id = RelativeId(name.toId())
        createVcsRootAndMavenDeployBuild(this, "maven-core", "https://gitbox.apache.org/repos/asf/maven.git")
        createVcsRootAndMavenDeployBuild(this, "maven-clean-plugin", "https://gitbox.apache.org/repos/asf/maven-clean-plugin.git")
        createVcsRootAndMavenDeployBuild(this, "maven-resources-plugin", "https://gitbox.apache.org/repos/asf/maven-resources-plugin.git")
        createVcsRootAndMavenDeployBuild(this, "maven-compiler-plugin", "https://gitbox.apache.org/repos/asf/maven-compiler-plugin.git")
        createVcsRootAndMavenDeployBuild(this, "maven-surefire-plugin / maven-failsafe-plugin", "https://gitbox.apache.org/repos/asf/maven-surefire.git", "%linux.java8.oracle.64bit%")
        createVcsRootAndMavenDeployBuild(this, "maven-checkstyle-plugin", "https://gitbox.apache.org/repos/asf/maven-checkstyle-plugin.git")
        createVcsRootAndMavenDeployBuild(this, "maven-javadoc-plugin", "https://gitbox.apache.org/repos/asf/maven-javadoc-plugin.git")
        createVcsRootAndMavenDeployBuild(this, "maven-jaxb2-plugin", "https://github.com/highsource/maven-jaxb2-plugin.git")
    }
    subProject {
        name = "JaCoCo"
        id = RelativeId(name.toId())
        createVcsRootAndMavenDeployBuild(this, "jacoco", "https://github.com/jacoco/jacoco.git")
    }
}
fun createVcsRootAndMavenDeployBuild(project: Project, buildName: String, gitRepoUrl: String, javaHome: String = "%linux.java11.openjdk.64bit%") {
    val camelCaseId = buildName
            .replace(" ", "-")
            .replace(Regex("""[^A-Za-z0-9-]"""), "")
            .split('-')
            .joinToString("", transform = String::capitalize)
    val vcsRoot = GitVcsRoot {
        id = AbsoluteId("${camelCaseId}_Git".toId(project.id.toString()))
        name = "$buildName (Git)"
        pollInterval = SECONDS.convert(30, MINUTES).toInt()
        url = gitRepoUrl
    }
    project.vcsRoot(vcsRoot)
    project.buildType {
        id = AbsoluteId(camelCaseId.toId(project.id.toString()))
        name = buildName
        vcs {
            root(vcsRoot)
        }
        steps {
            maven {
                goals = "clean deploy"
                runnerArgs = """
                    -DskipTests
                    -DskipITs
                    -DaltDeploymentRepository=gradle-artifactory::default::https://repo.gradle.org/gradle/ext-snapshots-local
                    -Dartifactory.username=%foo%
                    -Dartifactory.password=%bar%
                """.trimIndent()
                mavenVersion = custom {
                    path = "%teamcity.tool.maven.3.6.3%"
                }
                userSettingsSelection = "settings.xml for Gradle Artifactory"
                localRepoScope = MavenBuildStep.RepositoryScope.BUILD_CONFIGURATION
                jdkHome = javaHome
            }
        }
        triggers {
            vcs {
                id = "vcsTrigger"
            }
        }
        requirements {
            contains("teamcity.agent.jvm.os.name", "Linux")
        }
    }
}
