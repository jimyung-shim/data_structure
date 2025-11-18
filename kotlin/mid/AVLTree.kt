import java.lang.Math.max
import kotlin.math.pow

class AVLTree<T: Comparable<T>> {
    var root: AVLNode<T>? = null
    override fun toString() = root?.toString() ?: "empty tree"

    private fun leftRotate(node: AVLNode<T>): AVLNode<T> {
        val pivot = node.rightChild!!
        node.rightChild = pivot.leftChild
        pivot.leftChild = node
        node.height = Math.max(node.leftHeight, node.rightHeight) + 1
        pivot.height = Math.max(pivot.leftHeight, pivot.rightHeight) + 1
        return pivot
    }

    private fun rightRotate(node: AVLNode<T>): AVLNode<T> {
        val pivot = node.leftChild!!
        node.leftChild = pivot.rightChild
        pivot.rightChild = node
        node.height = Math.max(node.leftHeight, node.rightHeight) + 1
        pivot.height = Math.max(pivot.leftHeight, pivot.rightHeight) + 1
        return pivot
    }

    private fun rightLeftRotate(node: AVLNode<T>): AVLNode<T> {
        val pivot = node.rightChild ?: return node
        node.rightChild = rightRotate(pivot)
        return leftRotate(node)
    }
    private fun leftRightRotate(node: AVLNode<T>): AVLNode<T> {
        val pivot = node.leftChild ?: return node
        node.leftChild = leftRotate(pivot)
        return rightRotate(node)
    }

    private fun balanced(node: AVLNode<T>): AVLNode<T> {
        return when (node.balanceFactor) {
            2 -> {
                if (node.leftChild?.balanceFactor == -1)
                    leftRightRotate(node)
                else
                    rightRotate(node)
            }
            -2 -> {
                if (node.rightChild?.balanceFactor == 1)
                    rightLeftRotate(node)
                else
                    leftRotate(node)
            }
            else -> node
        }
    }

    private fun insert(node: AVLNode<T>?, value: T): AVLNode<T>? {
        node ?: return AVLNode(value)
        if (value < node.value) {
            node.leftChild = insert(node.leftChild, value)
        } else {
            node.rightChild = insert(node.rightChild, value)
        }
        val balancedNode = balanced(node)
        balancedNode?.height = Math.max(
            balancedNode?.leftHeight ?: 0,
            balancedNode?.rightHeight ?: 0
        ) + 1
        return balancedNode
    }
    fun insert(value: T) {
        root = insert(root, value)
    }

    private fun remove(
        node: AVLNode<T>?,
        value: T
    ): AVLNode<T>? {
        node ?: return null
        when {
            value == node.value -> {
                if (node.leftChild == null && node.rightChild == null)
                    return null
                if (node.leftChild == null)
                    return node.rightChild
                if (node.rightChild == null)
                    return node.leftChild
                node.rightChild?.min?.value?.let {
                    node.value = it
                }
                node.rightChild = remove(node.rightChild, node.value)
            }
            value < node.value -> node.leftChild =
                remove(node.leftChild, value)
            else -> node.rightChild = remove(node.rightChild, value)
        }
        val balancedNode = balanced(node)
        balancedNode.height = max(
            balancedNode.leftHeight,
            balancedNode.rightHeight
        ) + 1
        return balancedNode
    }
    fun remove(value: T) {
        root = remove(root, value)
    }

    fun leafNodesOfPerfect(height: Int): Int {
        return 2.0.pow(height).toInt()
    }

    fun nodesOfPerfect(height: Int): Int {
        return leafNodesOfPerfect(height + 1) - 1
    }
}