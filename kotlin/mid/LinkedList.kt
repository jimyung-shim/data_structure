class LinkedList<T> : Iterable<T>, Collection<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    override var size = 0
        private set // read only로 만들기

    override fun iterator(): Iterator<T> {
        return LinkedListIterator(this)
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun contains(element: T): Boolean {
        for (item in this)
            if (item == element) return true
        return false
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        for (searched in elements)
            if (!contains(searched)) return false
        return true
    }

    override fun toString(): String {
        if (isEmpty()) {
            return "Empty list"
        }
        return head.toString()
    }

    fun push(value: T): LinkedList<T> {
        head = Node(value = value, next = head)
        if (tail == null) {
            tail = head
        }
        size++
        return this
    }

    fun append(value: T): LinkedList<T> {
        // 1
        if (isEmpty()) {
            push(value)
            return this
        }
        // 2
        tail?.next = Node(value = value)
        // 3
        tail = tail?.next
        size++
        return this
    }

    fun nodeAt(index: Int): Node<T>? {
        // 1
        var currentNode = head
        var currentIndex = 0
        // 2
        while (currentNode != null && currentIndex < index) {
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode
    }

    fun insert(value: T, afterNode: Node<T>): Node<T> {
        // 1
        if (tail == afterNode) {
            append(value)
            return tail!!
        }
        // 2
        val newNode = Node(value = value, next = afterNode.next)
        // 3
        afterNode.next = newNode
        size++
        return newNode
    }

    fun pop(): T? {
        if (!isEmpty()) size--
        val result = head?.value
        head = head?.next
        if (isEmpty()) {
            tail = null
        }
        return result
    }

    fun removeHead(): T? {
        val head = head ?: return null
        size--
        this.head = head.next
        if (isEmpty())
            this.tail = null
        return head.value
    }

    fun removeLast(): T? {
        // 1
        val head = head ?: return null
        // 2
        if (head.next == null) return pop()
        // 3
        size--
        // 4
        var prev = head
        var current = head
        var next = current.next
        while (next != null) {
            prev = current
            current = next
            next = current.next
        }
        // 5
        prev.next = null
        tail = prev
        return current.value
    }

    fun removeAfter(node: Node<T>): T? {
        val result = node.next?.value
        if (node.next == tail) {
            tail = node
        }
        if (node.next != null) {
            size--
        }
        node.next = node.next?.next
        return result
    }

    fun printInReverse() {
        this.nodeAt(0)?.printInReverse()
    }

    fun getMiddle(): Node<T>? {
        var slow = this.nodeAt(0)
        var fast = this.nodeAt(0)
        while (fast != null) {
            fast = fast.next
            if (fast != null) {
                fast = fast.next
                slow = slow?.next
            }
        }
        return slow
    }

    private fun addInReverse(list: LinkedList<T>, node: Node<T>)
    {
        val next = node.next
        if (next != null) {
            addInReverse(list, next)
        }
        list.append(node.value)
    }

    fun reversed(): LinkedList<T> {
        val result = LinkedList<T>()
        val head = this.nodeAt(0)
        if (head != null) {
            addInReverse(result, head)
        }
        return result
    }

    private fun append(
        result: LinkedList<T>,
        node: Node<T>
    ): Node<T>? {
        result.append(node.value)
        return node.next
    }

    fun mergeSorted(
        otherList: LinkedList<T>
    ): LinkedList<T> {
        if (this.isEmpty()) return otherList
        if (otherList.isEmpty()) return this
        val result = LinkedList<T>()
        var left = nodeAt(0)
        var right = otherList.nodeAt(0)
        while (left != null && right != null) {
            if ((left.value as Int) < (right.value as Int)) {
                left = append(result, left)
            } else {
                right = append(result, right)
            }
        }
        while (left != null) {
            left = append(result, left)
        }
        while (right != null) {
            right = append(result, right)
        }
        return result
    }
}

class LinkedListIterator<K> (
    private val list: LinkedList<K>
) : Iterator<K> {
    private var index = 0
    private var lastNode: Node<K>? = null

    override fun next(): K {
        if (index >= list.size) throw IndexOutOfBoundsException()
        lastNode = if (index == 0) {
            list.nodeAt(0)
        } else
            lastNode?.next
        index++
        return lastNode!!.value
    }
    override fun hasNext(): Boolean {
        return index < list.size
    }
}
