// Original bug: KT-21329

class ClassicProperties(val source: Map<String, String>) {
    inner class System {
        val os: String by source
        val version: String by source
    }
    val system = System()
    inner class Db {
        val username: String by source
        val password: String by source
        inner class Extended {
            val poolSize: String by source
        }
        val extended = Extended()
    }
    val db = Db()
}
