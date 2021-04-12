// Original bug: KT-9315


public class Outer(val one: String, val two: String) {
    companion object {
        public fun createMappings(): List<String> {
           return setOf(Outer::one, Outer::two).map { it.name }
        }
    }

    inner class Inner {
        public fun createMappings(): List<String> {
            return setOf(::one, ::two).map { it.name }
        }
    }

    public fun createMappings(): List<String> {
        return setOf(::one, ::two).map { it.name }
    }

    class Staticky {
        public fun createMappings(): List<String> {
            return setOf(Outer::one, Outer::two).map { it.name }
        }
    }
}
