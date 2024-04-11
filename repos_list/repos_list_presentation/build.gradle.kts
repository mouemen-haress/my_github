plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "com.moemen.repos_list_presentation"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.reposListDomain))
    implementation(Coil.coilCompose)

}