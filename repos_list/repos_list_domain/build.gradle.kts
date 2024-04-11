plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.moemen.repos_list_domain"
}

dependencies {
    implementation(project(Modules.core))

}