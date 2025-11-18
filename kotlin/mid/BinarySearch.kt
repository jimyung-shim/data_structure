fun <T : Comparable<T>> ArrayList<T>.binarySearch(
    value: T,
    range: IntRange = indices
): Int? {
    if (range.first > range.last)
        return null
    val size = range.last - range.first + 1
    val middle = range.first + size / 2
    return when {
        this[middle] == value -> middle
        this[middle] > value -> binarySearch(value,
            range.first until middle)
        else -> binarySearch(value,
            (middle + 1)..range.last)
    }
}

fun <T : Comparable<T>> ArrayList<T>.findIndices(
    value: T
): IntRange? {
    val startIndex = startIndex(value, 0..size) ?: return null
    val endIndex = endIndex(value, 0..size) ?: return null
    return startIndex until endIndex
}

private fun <T : Comparable<T>> ArrayList<T>.startIndex(
    value: T,
    range: IntRange
): Int? {
    val middleIndex = range.start + (range.last - range.start + 1) / 2
    if (range.last < range.start || range.start >= size)
        return null

    return if (this[middleIndex] == value) {
        val isStartIndex = middleIndex == 0 || this[middleIndex - 1] != value
        if (isStartIndex)
            middleIndex
        else
            startIndex(value, range.start until middleIndex)
    } else if (value < this[middleIndex])
        startIndex(value, range.start until middleIndex)
    else
        startIndex(value, (middleIndex + 1)..range.last)
}

private fun <T : Comparable<T>> ArrayList<T>.endIndex(
    value: T,
    range: IntRange
): Int? {
    val middleIndex = range.start + (range.last - range.start + 1) / 2
    if (range.last < range.start || range.start >= size)
        return null

    return if (this[middleIndex] == value) {
        val isEndIndex = middleIndex == size - 1 || this[middleIndex + 1] != value
        if (isEndIndex)
            middleIndex + 1
        else
            endIndex(value, (middleIndex + 1)..range.last)
    } else if (value < this[middleIndex])
        endIndex(value, range.start until middleIndex)
    else
        endIndex(value, (middleIndex + 1)..range.last)
}

