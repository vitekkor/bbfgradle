// Original bug: KT-38968

internal object A {
    @JvmStatic
    fun main(args: Array<String>) {
        val s = " 1234-12_90-45_"
        val result = s
            .replace("-", "+")
            .replace("_", "/") + "==" 
    }
}
