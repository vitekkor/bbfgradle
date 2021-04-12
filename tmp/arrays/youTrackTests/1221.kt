// Original bug: KT-20913

import java.math.BigDecimal

operator fun Double.invoke() :BigDecimal{ return BigDecimal(this.toString())}

fun main(args: Array<String>) {
       println( 2.01() + 2.01() == 4.02() )
}
