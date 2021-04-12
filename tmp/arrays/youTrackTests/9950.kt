// Original bug: KT-10444

class Bar<T : Any>(val a: T?) {
    fun test1(obj: Any) {
        obj as Bar<T>
        check(obj.a)     // ok
    }

    fun test1(obj: Bar<*>) {
        obj as Bar<T>
        check(obj.a)     // error
    }

    fun check(a: T?) {
    }
}
