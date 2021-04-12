// Original bug: KT-17753

fun main(args: Array<String>) {
    test()
}

fun test(): Long {
    if (1 == 1 && //breakpoint 
        2 == 2) { //breakpoint// stops only here
        return 0
    }

    return 1
}
