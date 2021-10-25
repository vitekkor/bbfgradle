// Original bug: KT-27994

import java.net.URI
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

fun foo(path: Path) {
    return path.resolve(Paths.get("gradle", "wrapper", "gradle-wrapper.properties"))
        .let { p ->
            if (Files.exists(p)) {
                p.toFile().useLines { lines ->
                    lines
                        .filter { it.contains("(?:distributionUrl).*(?:=)".toRegex()) }
                        .map { it.split("=".toRegex(), 2)[1] }
                        .map { it.replace("\\\\".toRegex(), "") }
                        .firstOrNull()
                        ?.let(URI::create)
                }
            } else {
                null
            }
        }
}

fun main(args: Array<String>) {
    foo(Paths.get(""))
}
