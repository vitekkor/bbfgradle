// Original bug: KT-21329

class Properties(val source: Map<String, String>) {
    val system = object {
        val os: String by source
        val version: String by source
    }
    val db = object {
        val username: String by source
        val password: String by source
        val extended = object {
            val poolSize: String by source
        }
    }
}
