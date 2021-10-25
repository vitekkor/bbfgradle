// Original bug: KT-39217

@file:Suppress("UNCHECKED_CAST", "SameParameterValue", "unused")

package de.smartsteuer.kotlin.plugin.bug.demo

import kotlin.reflect.KProperty

class BugDemo(private val fields: MutableMap<String, Any?>) {

  var demo1: Int  by output("a")
  var demo2: Int? by nullableOutput("b")

  private fun <T: Any> output(key: String): Output<T> {
    return Output(fields, key)
  }

  private fun <T> nullableOutput(key: String): NullableOutput<T> {
    return NullableOutput(fields, key)
  }
}


class Output<T>(private val fields: MutableMap<String, Any?>, private val key: String) {
  operator fun getValue(thisRef: Any?, property: KProperty<*>): T = fields[key] as T
  operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    fields[key] = value
  }
  operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): Output<T> = this
}


class NullableOutput<T>(private val fields: MutableMap<String, Any?>, private val key: String) {
  operator fun getValue(thisRef: Any?, property: KProperty<*>): T? = fields[key] as T?
  operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    fields[key] = value
  }
  operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): NullableOutput<T> = this
}
