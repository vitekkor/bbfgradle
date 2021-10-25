// Original bug: KT-5570

fun main(args: Array<String>) {
    var i = 0
    while(++i<5) {
        print("i=${i}")
        if (i<3)
            return continue
        else
            return break
    }
    print("after")
}
// output is "i=1,i=2,i=3,after"
