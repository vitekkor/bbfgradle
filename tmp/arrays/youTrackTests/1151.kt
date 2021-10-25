// Original bug: KT-38975

class C {
    var parent: C = C()

    tailrec fun f() { // [NO_TAIL_CALLS_FOUND] A function is marked as tail-recursive but no tail calls are found
        parent.f() // [NON_TAIL_RECURSIVE_CALL] Recursive call is not a tail call
    }
}
