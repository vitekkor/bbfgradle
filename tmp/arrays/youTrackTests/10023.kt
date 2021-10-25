// Original bug: KT-9901

public abstract class AbstractFoo<K, V> : Map<K, V> {
    override operator fun get(key: K): V? {
        println("AbstractFoo")
        return null
    }

    override val size: Int
        get() = throw UnsupportedOperationException()

    override fun isEmpty(): Boolean {
        throw UnsupportedOperationException()
    }

    override fun containsKey(key: K): Boolean {
        throw UnsupportedOperationException()
    }

    override fun containsValue(value: V): Boolean {
        throw UnsupportedOperationException()
    }

    override val keys: Set<K>
        get() = throw UnsupportedOperationException()
    override val values: Collection<V>
        get() = throw UnsupportedOperationException()
    override val entries: Set<Map.Entry<K, V>>
        get() = throw UnsupportedOperationException()
}

public class IntFoo<E> : AbstractFoo<Int, E>() {
    override operator fun get(key: Int): E? {
        println("IntFoo")
        return null
    }
}

public interface Bar<K, V> {
    operator fun get(key: K): V?
}

public abstract class AbstractBar<K, V> : Bar<K, V> {
    override operator fun get(key: K): V? {
        println("AbstractBar")
        return null
    }
}

public class IntBar<E> : AbstractBar<Int, E>() {
    override operator fun get(key: Int): E? {
        println("IntBar")
        return null
    }
}

fun main(args: Array<String>) {
    IntFoo<String>().get(1) // prints "AbstractFoo"
    IntBar<String>().get(1) // prints "IntBar"
}
