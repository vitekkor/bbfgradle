// Original bug: KT-3930

public abstract class Foo {
	var isOpen = true
		private set
}
public class Bar: Foo() {
	inner class Baz {
		fun call() {
 			// These two lines cause an error
			val s = this@Bar
			s.isOpen
		}
	}
}
fun main(args: Array<String>) {
	Bar().Baz()
}
