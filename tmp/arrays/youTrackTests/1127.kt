// Original bug: KT-39209

package test.pkg2

import kotlin.reflect.KClass

interface NavArgs
class Fragment
class Bundle
class NavArgsLazy<Args : NavArgs>(
    private val navArgsClass: KClass<Args>,
    private val argumentProducer: () -> Bundle
)
