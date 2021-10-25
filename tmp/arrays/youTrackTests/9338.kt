// Original bug: KT-14447

class ImpulsMigration
{
    fun migrate(oldVersion: Long)
    {
        var _oldVersion = oldVersion

        if (_oldVersion == 1L)
        {
            _oldVersion++
        }

        if (_oldVersion == 2L)
        {
            _oldVersion++
        }
    }
}

