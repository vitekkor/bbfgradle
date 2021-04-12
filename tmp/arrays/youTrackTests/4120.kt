// Original bug: KT-14972

interface I1

interface I2

class A : I1, I2

class B : I1, I2

// Type inference failed. Expected type mismatch: 
// required: List<I1> 
// found: List<Any>
fun foo(): List<I1> = listOf(listOf(A()), listOf(B())).flatten() 
