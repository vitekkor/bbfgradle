// Original bug: KT-20707

enum class Build { Debug, Release }

fun applySomething(build: Build) = when (build) {
    Build.Debug -> "environmentDebug"
    Build.Release -> "environmentRelease"
}
