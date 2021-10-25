// Original bug: KT-35797

class Builder {
    @set:JvmSynthetic // Hide from Java callers so setTitle can return Builder
    var title: String? = null

    fun setTitle(title: String) = apply { this.title = title }
}
