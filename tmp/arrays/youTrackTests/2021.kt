// Original bug: KT-23850

package test

import kotlin.reflect.KClass

interface In<in T>

inline fun <reified T> In<T>.isT(): Boolean =
        this is T

inline fun <reified T> In<T>.asT() { this as T }

inline fun <reified T> In<T>.classOfT(): KClass<*> = T::class

fun <S> sel(x: S, y: S): S = x

interface A
interface B

interface A1 : A
interface A2 : A

interface Z1 : A, B
interface Z2 : A, B


fun testInIs1(x: In<A>, y: In<B>) = sel(x, y).isT()
// In<{A & B}>
// INSTANCEOF java/lang/Object

fun testInIs2(x: In<Z1>, y: In<Z2>) = sel(x, y).isT() 
// In<{Z2 & Z1}>
// INSTANCEOF java/lang/Object

fun testInIs3(x: In<A1>, y: In<A2>) = sel(x, y).isT()
// In<{A2 & A1}>
// INSTANCEOF test/A

fun testInAs1(x: In<A>, y: In<B>) = sel(x, y).asT()
// In<{A & B}>
// CHECKCAST java/lang/Object

fun testInAs2(x: In<Z1>, y: In<Z2>) = sel(x, y).asT()
// In<{Z2 & Z1}>
// CHECKCAST java/lang/Object

fun testInAs3(x: In<A1>, y: In<A2>) = sel(x, y).asT()
// In<{A2 & A1}>
// CHECKCAST test/A

fun testInClassOf1(x: In<A>, y: In<B>) = sel(x, y).classOfT()
// In<{A & B}>
// LDC Ljava/lang/Object;.class

fun testInClassOf2(x: In<Z1>, y: In<Z2>) = sel(x, y).classOfT()
// In<{Z2 & Z1}>
// LDC Ljava/lang/Object;.class

fun testInClassOf3(x: In<A1>, y: In<A2>) = sel(x, y).classOfT()
// In<{A2 & A1}>
// LDC Ltest/A;.class
