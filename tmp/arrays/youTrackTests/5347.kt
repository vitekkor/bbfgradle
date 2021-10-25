// Original bug: KT-22302

fun test(file: java.io.File) {
    val isInBuildSrc =
        generateSequence(file.parentFile) {
            if (true) {
                it.parentFile
            } else {
                null
            }
        }
            .takeWhile { it.isDirectory }
            .any { it.name == "buildSrc" }
}
