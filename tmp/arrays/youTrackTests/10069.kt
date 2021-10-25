// Original bug: KT-9779

class Test {
    companion object {

        @JvmStatic
        public fun main(args: Array<String>) {
            "test"["b"];
        }
    }
}


private operator fun String.get(field: String): String {
    return field;
}
