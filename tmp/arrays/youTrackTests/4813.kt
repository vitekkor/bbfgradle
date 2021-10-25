// Original bug: KT-34049

fun main() {
    val projectExtId: String? = "id"
    val s = "https://teamcity.jetbrains.com/guestAuth/app/rest/builds/buildType:(id:${projectExtId ?: "Kotlin_dev_Compiler"})"
}
