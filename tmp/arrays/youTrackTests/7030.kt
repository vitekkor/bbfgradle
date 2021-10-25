// Original bug: KT-21787

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Container {

    var id: Int? = null
}


class TestClass {

    @Test
    fun doThings1() {
        val containers = createContainers1()
        assertEquals(containers.size, 101)
    }

    @Test
    fun doThings2() {
        val containers = createContainers2()
        assertEquals(containers.size, 101)
    }

    private fun createContainer(id: Int): Container { val q = Container(); q.id = id; return q }
    private fun createContainers1(from: Int = 0, to: Int = 100) = (from .. to).map(::createContainer)
    private fun createContainers2(from: Int = 0, to: Int = 100): List<Container> { return (from .. to).map(::createContainer) }
}
