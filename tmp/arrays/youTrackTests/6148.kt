// Original bug: KT-31469

// base can't be 0, 1, or -1, power can't be 0
tailrec fun isPowerOf(base: Int, power: Int): Boolean =
    power == 1 || power % base == 0 && isPowerOf(base, power / base)
