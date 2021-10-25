// Original bug: KT-11285

package test

import java.util.*

fun main(args: Array<String>) {
    val sprintIssues = Derived()
    sprintIssues.remove(Issue())
}

open class Base<Target : DatabaseEntity>() : HashSet<Target>() {
    override fun remove(element: Target): Boolean {
        return true
    }
}

class Derived : Base<Issue>() {
    override fun remove(element: Issue): Boolean {
        return super.remove(element)
    }
}

open class DatabaseEntity
class Issue: DatabaseEntity()
