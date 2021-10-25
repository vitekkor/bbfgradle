// Original bug: KT-27437

tailrec fun heightCPS(t: Tree?, k: (Int) -> Int): Int {
    if (t == null) return k(0)

    return heightCPS(t.left, fun(hl: Int): Int {
        return heightCPS(t.right, fun(hr: Int): Int { // Recursive call is not a tail call
            return k(1 + maxOf(hl, hr))
        })
    })
}

class Tree(val left: Tree? = null, val right: Tree? = null)

fun main(args: Array<String>) {
    println(
        heightCPS(Tree(Tree())) { it }
    )
}
