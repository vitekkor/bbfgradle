// Original bug: KT-15101

fun main(args: Array<String>) {
    println(::callable.equals(::callable));
    println(::callable == ::callable);
    println(::callable.hashCode() == ::callable.hashCode());
}

fun callable() {
    
}
