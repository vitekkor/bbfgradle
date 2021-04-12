// Original bug: KT-20433

import java.io.File

private fun helpersUpdateNeeded(connection: SftpChannel,
                                helpersRoots: Collection<String>,
                                remoteHelpersRoot: String,
                                updateOnSnapshot: Boolean): Boolean {
    val snapshot = PerformanceWatcher.takeSnapshot()
    try {
        return helpersRoots.any { path ->
            try {
                val helpersDir = connection.file(remoteHelpersRoot)
                if (!helpersDir.exists()) {
                    return true
                }
                val relativePath = relativePathsMap?.get(path)
                if (relativePath != null) {
                    val singleFile = helpersDir.child(relativePath)
                    if (!singleFile.exists()) {
                        return true
                    }
                    else
                        if (!singleFile.isDirectory) {
                            return false //TODO: check modification time?
                        }
                }
                val versionFile = helpersDir.child("VERSION_FILE")
                if (versionFile.exists()) {
                    versionFile.inputStream().use {
                        val content = String(it.readBytes(3))
                        val localContent = "123"
                        println("Remote helpers version is $content, local helpers version is $localContent")
                        if (content.isNotEmpty()) {
                            return localContent != content || (localContent.endsWith(".SNAPSHOT") && updateOnSnapshot)
                        }
                    }
                }
                return true
            }
            catch (e: Exception) {
                println("Failed to retrieve helpers version file $e")
            }
            true
        }
    }
    finally {
        snapshot.logResponsivenessSinceCreation("Helpers version check")
    }
}

private fun File.child(relativePath: String): File = File(this, relativePath)

val relativePathsMap = hashMapOf<String, String>()

object PerformanceWatcher {
    fun takeSnapshot() : Snapshot = Snapshot()
}

class Snapshot {
    fun logResponsivenessSinceCreation(s: String) = print(s)
}

class SftpChannel {
    fun file(s: String) = File(s)
}

fun main(args: Array<String>) {
    helpersUpdateNeeded(SftpChannel(), emptyList(), "", false)
}
