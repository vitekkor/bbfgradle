// Original bug: KT-2824

val String.foo: Iterator<String>
    get() = object : Iterator<String> {
        public override fun hasNext(): Boolean {
            return true
        }
        public override fun next(): String {
            return this@foo
        }

    }
