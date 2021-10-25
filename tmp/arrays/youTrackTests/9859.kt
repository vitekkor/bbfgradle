// Original bug: KT-10729

class IntentionsBundle {
    companion object {
        fun message(key: String): String {
            return key + BUNDLE
        }

        private const val BUNDLE = "K"
    }
}


fun main(args: Array<String>) {
    IntentionsBundle.message("O")
}
