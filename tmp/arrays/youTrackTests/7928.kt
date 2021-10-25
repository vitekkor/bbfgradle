// Original bug: KT-24349

interface A {
    fun myNullableFunc(): String?
}

fun foo(args: List<A>){
    bar(args.mapNotNull { it.myNullableFunc() })
}

fun bar(args: List<String>){

}
