// Original bug: KT-6742

class A {                              
    fun c() {                          
    }                                  
}                                      
                                       
class B {                              
                                       
    fun invoke(f: A.() -> Unit): Unit {
        A().f()                        
    }                                  
}                                      
                                       
fun B.invoke(f: (A) -> Unit): Unit {   
    f(A())                             
}                                      
                                       
fun B.invoke(i: Int): Unit {           
                                       
}                                      
                                       
fun buildB() = B()                     
