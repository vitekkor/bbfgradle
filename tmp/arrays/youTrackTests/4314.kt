// Original bug: KT-37179

class Case2() {

    fun case1_0() {
        fun <T> Case2.emptyArray() = print("fun") //EXTENSION_FUNCTION_SHADOWED_BY_MEMBER_PROPERTY_WITH_INVOKE (0)
        emptyArray<Int>() //resolved to  (0)

        fun case1_1() {
            fun <T> Case2.emptyArray()= print("fun")//EXTENSION_FUNCTION_SHADOWED_BY_MEMBER_PROPERTY_WITH_INVOKE (1)
            emptyArray<Int>() //resolved to  (1)
        }
    }

    val Case2.emptyArray: A
        get() = A()

    fun <T> Case2.emptyArray()= print("fun")//EXTENSION_FUNCTION_SHADOWED_BY_MEMBER_PROPERTY_WITH_INVOKE (2)

    fun case2_0() {
        emptyArray<Int>() //resolved to  (2)
    }

}

class A {
    operator fun <T> invoke()= print("invoke")
}

fun <T> Case2.emptyArray()= print("fun")//EXTENSION_FUNCTION_SHADOWED_BY_MEMBER_PROPERTY_WITH_INVOKE (3)

fun boo(){
    Case2().emptyArray<Int>() //resolved to  (3)
}

