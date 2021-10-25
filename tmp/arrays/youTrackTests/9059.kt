// Original bug: KT-14811

package test

interface WorldObject {
    val name: String
}

fun <T, R> T.let2(body: (T) -> R) = body(this)

fun testLet(worldObj: WorldObject) {
    //  ...
    //  CHECKCAST test/WorldObject
    //  ASTORE 1
    val x = worldObj.let { it }
}

fun testLet2(worldObj: WorldObject) {
    //  ...
    //  CHECKCAST test/WorldObject
    //  ASTORE 1
    val x = worldObj.let2 { it }
}
