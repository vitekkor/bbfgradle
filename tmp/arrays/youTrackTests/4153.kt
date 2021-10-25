// Original bug: KT-19751

interface A

interface B

class C: A, B
class D: A, B

fun test(): A = if (true) {
    if (true) {
        if (true) C() else D()
    } else D()
} else C()
