// Original bug: KT-45596

internal class Foo {
    fun x() {
        run {
            {
                val a = 1
                System.out.printf("%d\n", a)
            }
            {
                val a = 2
                System.out.printf("%d\n", a)
            }
        }
    }
}
