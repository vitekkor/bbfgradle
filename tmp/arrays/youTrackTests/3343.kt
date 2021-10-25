// Original bug: KT-32801

        val x = listOf<Any>(123, 7.7, true, "wefdwe", "wergwe")
        val z = mapOf(Pair("wefdwe", "wedfwef"))
        val y = x.mapNotNull {
            (it as? String).let { stringMaybe ->
                if (stringMaybe == null) {
                    println("not a string")
                    null
                } else {
                    z[stringMaybe]?.let { s ->
                        Math.random()
                    }
                }
            }
        }
