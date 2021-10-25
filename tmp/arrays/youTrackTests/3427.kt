// Original bug: KT-31391

class A(val x: A?) {
    tailrec fun reg1() { // [NO_TAIL_CALLS_FOUND]
        if (x != null) x.reg1() // [NON_TAIL_RECURSIVE_CALL]
    }
    tailrec fun reg2() { // [NO_TAIL_CALLS_FOUND]
        x?.reg2() // [NON_TAIL_RECURSIVE_CALL]
    }
}

tailrec fun A.ext1(): A {
    return if (x != null) x.ext1() else this // OK but 'Replace 'if' expression with elvis expression' suggested
}

tailrec fun A.ext2(): A { // [NO_TAIL_CALLS_FOUND]
    return x?.ext2() ?: this // [NON_TAIL_RECURSIVE_CALL]
}
