// Original bug: KT-17438

object `?` // compiles in Linux/JVM, compile crash in Windows/JVM, compile error in JS
object `*` // compiles in Linux/JVM, compile crash in Windows/JVM, compile error in JS
object `"` // compiles in Linux/JVM, compile crash in Windows/JVM, compile error in JS
object `%JAVA_HOME%` // compiles in Linux/JVM, compiles in Windows/JVM, compile error in JS - but in Windows there can be problems with such file
