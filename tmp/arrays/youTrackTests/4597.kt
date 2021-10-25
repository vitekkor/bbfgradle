// Original bug: KT-34395

        val code = """
            |fun f(x: Int) {
            |    when (x) {
            |        !in 10..20 -> print(5)
            |    }
            |}
        """.trimMargin()
