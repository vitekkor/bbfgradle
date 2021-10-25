// Original bug: KT-22302

fun foo(list: MutableList<(Int) -> Int>) {
    list.onEach { println("longlonglong") }.add({ argName ->
                                                    // Lambda body
                                                    println()
                                                    argName + 1
                                                })
}
