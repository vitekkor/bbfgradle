// Original bug: KT-42221

class A {
    val x =
        mapOf(
            "" to { a: String, b: String, c: String, d: String -> },
            "" to { a: String, b: String, c: String, d: String -> },
            "" to { a: String, b: String, c: String, d: String -> },
            "" to { a: String, b: String, c: String, d: String -> },
            "" to { a: String, b: String, c: String, d: String -> }
        )
}
