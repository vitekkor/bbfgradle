// Original bug: KT-254

class A<T>() {
    fun foo(): A<T> = this  //should be no error; 
    //now here is an error: "Type mismatch: inferred type is A<&T> but A<&T?> was expected"
}

class B<out T>() {   //with 'out' modifier it works
    fun foo(): B<T> = this
}
