// Original bug: KT-10771

package hello;

import kotlin.reflect.jvm.reflect

fun main( args : Array< String > ) {

  fun< T > produce( value : T ) : () -> T { return { value } }

  fun< T > printType( f : () -> T ) {
    println( "Invocation result: ${f.invoke()}" )
    println( "Function type: " + f.reflect() )
  }

  printType( { 10 } )
  printType( produce( "A" ) )

}
