// Original bug: KT-15341

class Example(val parent: Example?) {
    // This is tail recursive
    tailrec fun foo(e: Example) {
        if (e.parent != null) foo(e.parent)
    }
    
    // But this is not and causes a compiler warning: "Recursive call is not a tail call"
    tailrec fun foo() {
        if (parent != null) parent.foo()
    }
}
