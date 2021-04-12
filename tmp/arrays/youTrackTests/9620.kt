// Original bug: KT-11307

tailrec fun odd(n: Int): Boolean =
        if (n == 0) false
        else even(n - 1)

tailrec fun even(n: Int): Boolean =
        if (n == 0) true
        else odd(n - 1)

fun main(args:Array<String>) {
    System.out.println(even(99999))  // java.lang.StackOverflowError
}
