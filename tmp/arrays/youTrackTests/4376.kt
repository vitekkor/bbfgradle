// Original bug: KT-36940

    class Example {
        val myLambda = ::addExt
        val lambdaMap: Map<String, (String) -> String> = mapOf(
            "world" to { value:String -> addExt(value) },    // <<-- compile error here
            "mars" to ::addExt
        )

        fun addExt(value: String): String {
            return "$value.gif"
        }
    }
