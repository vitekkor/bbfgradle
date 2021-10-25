// Original bug: KT-26860

class SampleNotWorking {
    private val builder = StringBuilder()
    // an intrinsic check is generated because you assign a value of a platform type to a variable of a non-nullable type
    fun test(): StringBuilder = builder.reverse()
}
class SampleWorking {
    private val builder = StringBuilder()
    // no additional checks, platform type is a return type of this function
    // intrinsic checks will be generated when you try to assign the result of test() to a variable of a non-nullable type
    fun test() = builder.reverse()
}
