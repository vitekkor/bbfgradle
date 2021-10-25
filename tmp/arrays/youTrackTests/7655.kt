// Original bug: KT-26402

package test

inline class Str(val s: String)
inline class NStr(val ns: String?)

fun test1(s: Str) {}        // (1) test1(Ljava/lang/String;)V
fun test1(s: Str?) {}       // (2) test1(Ljava/lang/String;)V

fun test2(ns: NStr) {}      // (3) test2(Ljava/lang/String;)V
fun test2(ns: NStr?) {}     // (4) test2(Ltest/NStr;)V
