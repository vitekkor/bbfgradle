interface A
interface B : A?
fun<T : A> doer(init: () -> T)  = TODO
fun 
()  {
    doer {
        object : B {}
    }
}