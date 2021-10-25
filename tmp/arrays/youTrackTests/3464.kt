// Original bug: KT-11293

fun foo(f: (Int) -> Double) = 2.0
fun foo(f: (Int) -> Int) = 1

fun main(args:Array<String>){
    foo({i: Int -> 1.0} as (Int) -> Double) // false "USELESS_CAST" here
}
