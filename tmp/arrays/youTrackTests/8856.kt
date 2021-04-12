// Original bug: KT-10021

fun <T> Collection<T>.partitionToSize(bucketSize: Int): List<List<T>> = 
    partitionToPieces(pieces = (this.size / bucketSize) + 1)

fun <T> Collection<T>.partitionToPieces(pieces: Int): List<List<T>> =
    (0..this.size)
    .zip(this)
    .groupBy(keySelector = { it.first % pieces }, valueTransform = { it.second })
    .map { it.value }
