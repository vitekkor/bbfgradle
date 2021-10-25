// Original bug: KT-25165

enum class MyEnum {
    ONE, TWO
}

fun main(args: Array<String>) {
    println(MyEnum::class.constructors.toTypedArray()[0].parameters) // []
    println(MyEnum::class.java.declaredConstructors[0]) // protected MyEnum(java.lang.String,int)
}
