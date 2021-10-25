// Original bug: KT-37056

fun case2(a: A){
   Case2(a)      // <Inapplicable(PARAMETER_MAPPING_ERROR): [/Case2.Case2]>#(R|<local>/a|)
  Case2(a =a)    //  <Inapplicable(INAPPLICABLE): [/Case2.Case2]>#(a = R|<local>/a|)
}

class Case2 {
    companion object {
        operator fun invoke(a: A) = "" //(1)
    }
}

class A()
