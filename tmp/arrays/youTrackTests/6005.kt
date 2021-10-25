// Original bug: KT-31417

class MyClass(){
        var someInt1: Int
        
        var someInt2: Int=0
        set(value){
                field=value
        }
        init {
                someInt1 = 10
                someInt2 = 20
        }
}

fun main(args: Array<String>){
       class MyClass(){
        var someInt1: Int
        
        var someInt2: Int
        set(value){
                field=value
        }
        init {
                someInt1 = 10
                someInt2 = 20
        }
} 
}
