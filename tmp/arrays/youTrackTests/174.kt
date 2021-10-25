// Original bug: KT-45451

interface EntityBase<out ID> {
    suspend fun id(): ID
}

inline class EntityId(val value: String)

interface Entity : EntityBase<EntityId>

class EntityStub : Entity {
    override suspend fun id(): EntityId = EntityId("foo-bar")
}

class ExampleQuery(
    private val entity: Entity
) {
    suspend fun id(): EntityId = entity.id()
}

suspend fun main() {
    val entity = EntityStub()
    val query = ExampleQuery(entity)
    println(query.id())
}
