// Original bug: KT-24427

abstract class A : Collection<String> {
    // public final foo([Ljava/lang/Object;)[Ljava/lang/Object;
    protected fun <T> foo(x: Array<T>): Array<T> = x // 
}
