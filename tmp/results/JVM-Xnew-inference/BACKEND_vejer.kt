inline class Z(val x: Double) {
    tailrec fun String.repeat(num : Int, acc : StringBuilder = StringBuilder()) : Any =
        repeat( num,acc.append(this))
}