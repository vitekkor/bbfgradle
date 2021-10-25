// Original bug: KT-2481

fun main(args: Array<String>) {
    B().method()
}

public open class A(){
    public open fun method() {
    }
}

public class B(): A(){

    public override fun method() {
        val time = time{
            super<A>.method()
        }
        System.out.println(time)
    }
}

public fun time(f: () -> Unit): Long {
    val start = System.currentTimeMillis()
    f()
    val end = System.currentTimeMillis()
    return end - start
}
