// Original bug: KT-19666

interface Taggable {
    val tag: String
}

fun Any.log() {
    val tag = if (this is Taggable) {
        tag
    } else {
        this::class.java.simpleName
    }
}
