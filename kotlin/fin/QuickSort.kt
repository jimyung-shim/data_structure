
fun<T: Comparable<T>> MutableList<T>.partitionLomuto(low: Int, high: Int): Int {
    val pivot = this[high]
    var i = low
    for (j in low until high) {
        if (this[j] <= pivot) {
            this.swapAt(i, j)
            i += 1
        }
    }
    this.swapAt(i, high)
    return i
}

fun<T: Comparable<T>> MutableList<T>.quicksortLomuto(low: Int, high: Int) {
    if (low < high) {
        val pivot = this.partitionLomuto(low, high)
        this.quicksortLomuto(low, pivot - 1)
        this.quicksortLomuto(pivot + 1, high)
    }
}

fun<T: Comparable<T>> MutableList<T>.partitionHoare(low: Int, high: Int): Int {
    val pivot = this[low]
    var i = low - 1
    var j = high + 1
    while (true) {
        do {
            j -= 1
        } while (this[j] > pivot)
        do {
            i += 1
        } while (this[i] < pivot)
        if (i < j) {
            this.swapAt(i, j)
        } else {
            return j
        }
    }
}

fun<T: Comparable<T>> MutableList<T>.quicksortHoare(low: Int, high: Int) {
    if (low < high) {
        val p = this.partitionHoare(low, high)
        this.quicksortHoare(low, p)
        this.quicksortHoare( p + 1, high)
    }
}

fun<T: Comparable<T>> MutableList<T>.partitionDutchFlag(
    low: Int, high: Int, pivotIndex: Int): Pair<Int, Int> {
    val pivot = this[pivotIndex]
    var smaller = low
    var equal = low
    var larger = high
    while (equal <= larger) {
        if (this[equal] < pivot) {
            this.swapAt(smaller, equal)
            smaller += 1
            equal += 1
        } else if (this[equal] == pivot) {
            equal += 1
        } else {
            this.swapAt(equal, larger)
            larger -= 1
        }
    }
    return Pair(smaller, larger)
}

fun<T: Comparable<T>> MutableList<T>.quicksortDutchFlag(low: Int, high: Int) {
    if (low < high) {
        val middle = partitionDutchFlag(low, high, high)
        this.quicksortDutchFlag(low, middle.first - 1)
        this.quicksortDutchFlag(middle.second + 1, high)
    }
}

fun <T: Comparable<T>> MutableList<T>.quicksortIterativeLomuto(low: Int, high: Int) {
    val stack = Stack<Int>()
    stack.push(low)
    stack.push(high)
    while (!stack.isEmpty) {
        val end = stack.pop() ?: continue
        val start = stack.pop() ?: continue
        val p = this.partitionLomuto(start, end)
        if ((p - 1) > start) {
            stack.push(start)
            stack.push(p - 1)
        }
        if ((p + 1) < end) {
            stack.push(p + 1)
            stack.push(end)
        }
    }
}
