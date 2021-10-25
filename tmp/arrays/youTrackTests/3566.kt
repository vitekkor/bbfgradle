// Original bug: KT-38368

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.streams.toList

fun readDirectory(directory: Path): List<Path> {
    return try {
        Files.list(directory).toList()
    } catch (e: NoSuchFileException) { // kotlin.io.NoSuchFileException
        emptyList()
    }
}

fun main() {
    readDirectory(Paths.get("path"))
}
