// Original bug: KT-1066

fun test() {
            var sum : Int = 0
            var first : Int = 1
            var second : Int = 2
            var temp : Int = 0 // variable 'temp' initializer is redundant
            
            while (true)
            {
                if (second > 4000000)
                    break

                if (second % 2 == 0)
                    sum += second

                temp = second
                second = first + second
                first = temp
            }
}
