// Original bug: KT-29440

/**
 * Some class.
 *
 * @param i The first number
 */
class Foo(val i: Int,
        /**
         * A second number
         */
          val j: Int = 7) {
}
