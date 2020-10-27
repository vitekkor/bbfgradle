@file:Suppress("UNCHECKED_CAST")

package com.stepanov.bbf.bugfinder.abiComparator

inline fun <reified T> Any?.cast() =
        this as T

inline fun <reified T> Any?.safeCast() =
        this as? T

inline fun <reified T: Any> List<Any?>?.listOfNotNull() =
        orEmpty().filterIsInstance<T>()