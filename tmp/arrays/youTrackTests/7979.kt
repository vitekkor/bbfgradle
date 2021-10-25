// Original bug: KT-16549

object V {

    private inline fun act(action: () -> Unit) {
        return action()
    }

    private var flag = false

    tailrec fun foo() {
        if (flag) return
        act {
            flag = true
            return foo()  // GOTO flag = true
        }
    }
}
