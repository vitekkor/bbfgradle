// Original bug: KT-36390

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

val path: Path? =
    if (true) {
        Paths.get("")
    } else {
        var appDataFolder = if (Paths.get("") != null) Paths.get("") else null
        if (appDataFolder == null || !Files.exists(appDataFolder)) { // [NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS] Type mismatch. 
            if (appDataFolder !== null && Files.exists(appDataFolder)) { // [NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS] Type mismatch. 
                Paths.get("")
            }
            Paths.get("")
        }
        Paths.get("")
    }

