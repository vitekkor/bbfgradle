// Original bug: KT-41117

class Default {
  companion object {
    val DEFAULT_PROVIDER_GROUP_INFO = ProviderGroupInfo(ProviderGroupKey.DEFAULT_KEY, -1)
  }
}

interface UrlPathInlayHintsProviderSemElement {
  val inlayHints: List<UrlPathInlayHint>

  val groupInfo: ProviderGroupInfo
    get() = Default.DEFAULT_PROVIDER_GROUP_INFO
}

interface UrlPathInlayHint

data class ProviderGroupInfo(val key: ProviderGroupKey, val priority: Int)

private val priorityComparator: Comparator<UrlPathInlayHintsProviderSemElement> = Comparator.comparingInt { it.groupInfo.priority }

data class ProviderGroupKey(private val id: String) {
  companion object {
    internal val DEFAULT_KEY = ProviderGroupKey("default.group.key")
  }
}

fun Sequence<UrlPathInlayHintsProviderSemElement>.selectProvidersFromGroups(): Sequence<UrlPathInlayHintsProviderSemElement> {
  groupingBy { it.groupInfo.key }
    .aggregate { key, accumulator: MutableList<UrlPathInlayHintsProviderSemElement>?, element, _ ->
      if (key == ProviderGroupKey.DEFAULT_KEY) {
        accumulator?.let { it.apply { add(element) } } ?: mutableListOf(element)
      } else {
        mutableListOf(
          accumulator?.singleOrNull()?.let { maxOf(it, element, priorityComparator) } ?: element
        )
      }
    }.asSequence()
    .flatMap { it.value.asSequence() }

  return sequence {  }
}
