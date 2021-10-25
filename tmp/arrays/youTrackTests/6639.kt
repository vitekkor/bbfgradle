// Original bug: KT-29863

fun main() {
    val s = "c2\nmaster\n2b734596ee5ad2b73a6e15e2ad866b54b2d55bb7\trefs/remotes/origin/master\n\nc1\nmaster\n?? new\nd3fc9dcac32ac58a0b3941ccfd43ea60f2a035f7\trefs/remotes/origin/master\n91e9e4d \"unpushed\"\n\n"
    val splitChar = s.split('\n')
    println(splitChar.size) //we get 11
    val splitString = s.split("\n")
    println(splitString.size) //we get 11
}
