// Original bug: KT-21396

class Something {
  fun foo(){}
  fun bar(){}
}

val fooOrBar: Something.() -> Unit = if(true) {{ foo() }} else {{ bar() }} //compiles without issue

val fooOrBar2: Something.() -> Unit = if(true) {{ foo() }} else if(true) {{ bar() }} else TODO() 
//fails on "{ bar() }": type miss-match, required: Nothing, found () -> ???
