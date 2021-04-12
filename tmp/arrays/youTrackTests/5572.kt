// Original bug: KT-19776

class Test() {

    fun toBeSynchronized() = 12
    
    fun test(){
//We can use existing `Add type for left-handside` quickfix here 
//to fix code. Quickifx basicly adds class name, so we can reference 
//existing function properly.
        val myFunA = ::toBeSynchronized 
    }
}
