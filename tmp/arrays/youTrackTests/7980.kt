// Original bug: KT-16549

class TailInline {

    private inline fun act(action: () -> Unit) {
        return action()
    }

    private var countDown = 10

    tailrec fun test() : Int {
        if (countDown<5) return countDown
        act {
            countDown--
            if (countDown<1)
                return countDown
            else
                return test()  // GOTO countDown--
        }
        return countDown
    }
}
