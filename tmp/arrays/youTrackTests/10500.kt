// Original bug: KT-3988

class Comment() {
    var article = ""

}
class Comment2() {
    var article2 = ""
}

fun new(body: Comment.() -> Unit) {}

fun new2(body: Comment2.() -> Unit) {}

fun main(args: Array<String>) {
    new {
        new2 {
            this@new //UNRESOLVED REFERENCE
        }
    }
}
