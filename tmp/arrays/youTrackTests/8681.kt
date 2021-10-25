// Original bug: KT-18637

class PrivateVarInline {
    @Volatile
    private var state: Int = 0
    
    private inline fun loopOnState(block: (Int) -> Unit) {
        while (true) {
            block(state)
        }
    }
}
