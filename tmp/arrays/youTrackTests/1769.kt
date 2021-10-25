// Original bug: KT-17556

import java.io.File

fun copyOrMove(source: File?, target: File, move: Boolean) {
    source?.let {
        if (move) {
            it.renameTo(target)        // Warning: IMPLICIT_CAST_TO_ANY
        } else {
            it.copyTo(target, true)    // Warning: IMPLICIT_CAST_TO_ANY
        }
    }
}
