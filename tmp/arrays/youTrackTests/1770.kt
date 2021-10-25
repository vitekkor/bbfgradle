// Original bug: KT-17556

import java.io.File

fun copyOrMove(source: File?, target: File, move: Boolean) {
    source?.apply {
        if (move) {
            renameTo(target)
        } else {
            copyTo(target, true)
        }
    }
}
