// Original bug: KT-24388

fun doSomeStuff():Boolean {
    run {
        var flag = 0
        for (i in 0..4) {
            if (i and 2 == 0) {// Arbitrary condition
                println(flag)
            } else {
                flag = -1 // "The value '-1' assigned to 'var flag:Int defined in doSomeStuff.<anonymous>' is never used.
                if (false) {
                    return false
                }
            }
        }
    }

    return true
}
