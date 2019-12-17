inline class Z(val x: Int) {
    tailrec fun String.repeat(num : Int, acc : StringBuilder = StringBuilder()) : String =
        repeat( num,acc.append(this))
}