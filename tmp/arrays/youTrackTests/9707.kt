// Original bug: KT-7962

import java.nio.file.FileSystems
import java.nio.file.Path

class KT7962 {
    fun get(): String? {
        val url = this.javaClass.classLoader.getResource(".")
        val uri = url.toURI()
        val path: Path
        FileSystems.newFileSystem(uri, emptyMap<String, Any>(), this.javaClass.classLoader).use { fileSystem ->

            // Compile succeeds when the following line is commented
            path = fileSystem.getPath(".")
            return null!!
        }
    }
}


