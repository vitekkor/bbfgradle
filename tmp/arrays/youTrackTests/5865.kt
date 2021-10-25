// Original bug: KT-29098

class Bar {
    private var continueClickListener: (() -> Unit)? = null

    fun setOnContinueClickListener(listener: ()-> Unit) {
        continueClickListener = listener
    }
}
