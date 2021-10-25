// Original bug: KT-2480

fun main(args: Array<String>) {
    System.out.print(Class().printSome())
}

public abstract class AbstractClass<T> {
    public fun printSome():Unit = System.out.print(some)

    public abstract val some: T
}

public class Class: AbstractClass<String>() {
    public override val some: String
        get() = "123"

}
