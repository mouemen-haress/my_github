pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MyGitHub"
include(":app")
include(":core")
include(":repos_list")
include(":repos_list:repos_list_presentation")
include(":repos_list:repos_list_domain")
include(":repos_list:repos_list_data")
include(":repos_details")
include(":repos_details:repos_details_presentation")
