// Original bug: KT-27460

/**
 * [true] if url can be retrieved to get a direct image url
 */
inline val String.isIndirectImageUrl: Boolean
    get() {
        return contains("/photo/view_full_size/") && contains("fbid=")
    }
