// Original bug: KT-20990

package some
val inserted = "m"
val case1 = "a $inserted z"
val case2 = "a ${inserted} z"
val case3 = "a ${inserted + ".postfix"} z"
//val case4 = "a ${"literal"} z"
val case5 = "a ${"prefix." + inserted} z" 