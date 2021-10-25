// Original bug: KT-40764

@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
annotation class MyDsl

@MyDsl
class MyReceiver1 {
    fun foo() = Unit
}

@MyDsl
class MyReceiver2 {
    fun buzz() = Unit
    fun moreBuzz(init: MyFunction1) = Unit
    fun evenMoreBuzz(init: MyFunction2) = Unit
}

typealias MyFunction1 = @MyDsl String.() -> Unit
typealias MyFunction2 = @MyDsl () -> Unit // No receiver

fun main() {

    MyReceiver1().apply {

        foo() // Good
        
        MyReceiver2().apply {
            
//            foo() // Bad
            buzz() // Good

            moreBuzz { // Good
                
//                foo() // Bad
//                buzz() // Bad
//                moreBuzz {} // Bad
//                evenMoreBuzz {} // Bad
                
            }
            
            evenMoreBuzz { // Good

//                foo() // Bad
                buzz() // Bad
                moreBuzz {} // Bad
                evenMoreBuzz {} // Bad
                
            }

        }

    }

}
