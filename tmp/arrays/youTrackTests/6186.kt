// Original bug: KT-25302

import java.util.Collections
import java.util.stream.Collectors.toList
import java.util.stream.Collectors.toSet
import java.util.stream.Stream

class KotlinCollectionUser {
    fun use() {
        Collections.unmodifiableSet(Stream.of("a", "b", "c").collect(toSet<String>()))
        Collections.unmodifiableList(Stream.of("a", "b", "c").collect(toList<String>()))
    }
}
