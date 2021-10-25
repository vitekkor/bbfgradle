// Original bug: KT-36390

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.PosixFilePermissions


val logsFolder: Path? =
        if (true) {
            val applicationSupport = Paths.get(System.getProperty("user.home"),
                    "Library/Logs/Visual Watermark")
            if (!Files.exists(applicationSupport)) {
                val perms = PosixFilePermissions
                        .fromString("rwxrwxrw-")
                val attrs = PosixFilePermissions
                        .asFileAttribute(perms)
                Files.createDirectory(applicationSupport, attrs)
            }
            applicationSupport
        } else {
            var appDataFolder = if (System.getProperty("LOCALAPPDATA") != null)
                Paths.get(System.getProperty("LOCALAPPDATA"))
            else
                null
//            @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
            if (appDataFolder == null || !Files.exists(appDataFolder)) {
                appDataFolder = null
                val folders = listOf(System.getProperty("USERPROFILE"),
                        System.getProperty("user.home"), System.getProperty("user.dir"))
                for (folder in folders) {
                    if (folder != null) {
                        appDataFolder = Paths.get(folder, "AppData\\Local")
                        if (appDataFolder !== null && Files.exists(appDataFolder))
                            break
                    }
                }
            }
            if (appDataFolder == null) {
                val tempFolder = System.getProperty("java.io.tmpdir")
                if (tempFolder != null && File("java.io.tmpdir").exists())
                    appDataFolder = Paths.get(tempFolder)
            }
            appDataFolder?.resolve("Visual Watermark")
        }
