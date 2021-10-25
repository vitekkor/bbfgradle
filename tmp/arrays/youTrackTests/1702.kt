// Original bug: KT-35166

// src/main/kotlin/Classes.kt

@file:Suppress("MemberVisibilityCanBePrivate", "ktPropBy")

import kotlin.reflect.KProperty

data class Field(val bytes: Int)

abstract class BinaryObject {
    private val _fields = mutableMapOf<String, Field>()
    val fields: Map<String, Field> = _fields
    
    operator fun Field.getValue(thisRef: BinaryObject, property: KProperty<*>) = this
    operator fun Field.provideDelegate(thisRef: BinaryObject, property: KProperty<*>) =
        this.also { this@BinaryObject._fields[property.name] = it }
    
    override fun toString() = fields.toString()
}

class BinaryObjects {
    private val _binaryObjects = mutableMapOf<String, BinaryObject>()
    val binaryObjects: Map<String, BinaryObject> = _binaryObjects
    
    operator fun <T : BinaryObject> T.getValue(thisRef: Nothing?, property: KProperty<*>) = this
    operator fun <T : BinaryObject> T.provideDelegate(thisRef: Nothing?, property: KProperty<*>) =
        this.also { this@BinaryObjects._binaryObjects[property.name] = it }
    
    override fun toString() = binaryObjects.toString()
}
