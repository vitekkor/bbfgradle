// Original bug: KT-776

fun test5() : Int {
    var x = 0
    while(true) {
        try {
            if(x < 10) {
                x++
                continue
            }
            else {
                break
            }
        }
        finally {
            x++
        }
    }
    return x
}
