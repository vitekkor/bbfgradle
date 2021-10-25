// Original bug: KT-30787

 fun fibonacci() = sequence {
        var prev = 1
        var next = 1
        while(true) {
            val t = prev + next
            prev = next
            next = t
            yield(t)  ///breakpoint here
        }
    }
