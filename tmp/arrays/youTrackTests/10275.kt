// Original bug: KT-7658

public class Test {
    constructor(cond : Boolean) {
        if (!cond){
            return // Error here. But in Java we can do it.
        }
        // Do something in constructor.
    }
}
