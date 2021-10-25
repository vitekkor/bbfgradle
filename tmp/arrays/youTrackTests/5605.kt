// Original bug: KT-32949

class Binder() {
    lateinit var bindee: Container<*>
    
    fun bind(subject: Container<*>) {
        bindee = subject
        when(subject.containee) {
            is Foo -> bind(subject.containee)
            is Bar -> bind(subject.containee)
        }
    }
    
    private fun bind(foo: Foo) {
        println("binding foo")
    }
    private fun bind(bar: Bar) {
        println("binding bar")
    }
}

class Container<out T>(val containee: T)

data class Foo(val x: Int = 0)
data class Bar(val y: Int = 0)
