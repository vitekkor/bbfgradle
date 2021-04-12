// Original bug: KT-42221

class A {
    val x: Map<String, (String, String, String, String) -> Unit> =
        mapOf(
            "" to { a, b, c, d -> },
            "" to { a, b, c, d -> },
            "" to { a, b, c, d -> },
            "" to { a, b, c, d -> },
            "" to { a, b, c, d -> }
        )
}
