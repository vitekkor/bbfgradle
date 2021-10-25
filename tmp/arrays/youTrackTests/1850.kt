// Original bug: KT-43606

annotation class ShortcutAction {
    companion object {
        const val NEW_CAT = "NEW_CAT"
        const val NEW_TIMER = "NEW_TIMER"

        @JvmStatic
        fun toType(shortcutId: String): Int? =
            when (shortcutId) {
                NEW_CAT -> Type.CATEGORY
                NEW_TIMER -> Type.TIMER
                else -> null
            }
    }
}

@Retention(AnnotationRetention.SOURCE)
annotation class Type {
    companion object {
        const val CATEGORY = 0
        const val TIMER = 1
    }
}


fun main() {
    val a = ShortcutAction.toType("NEW_CAT")
}
