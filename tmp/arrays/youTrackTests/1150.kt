// Original bug: KT-38975

class IntWrapper (val value: Int) {
    tailrec fun sumDownToZeroInternal(runningTotal: Int): Int { // NO_TAIL_CALLS_FOUND
        if (value == 0) return runningTotal
        return IntWrapper(value - 1).sumDownToZeroInternal(runningTotal - value)
    }


}

tailrec fun IntWrapper.sumDownToZero(runningTotal: Int): Int { // everything ok
    if (value == 0) return runningTotal
    return IntWrapper(value - 1).sumDownToZero(runningTotal - value)
}
