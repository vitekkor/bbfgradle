// Original bug: KT-28939

fun bar(name:String?) {
        Test().also { 
            name?.let { foo(it) }    //warning: Implicit parameter 'it' of enclousing lambda is shadowed
        }
    }
    
fun foo(str:String) { }
    
class Test
