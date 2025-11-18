//data class Node<T>(
//    var value: T,
//    var next: Node<T>?=null
//){
//    override fun toString(): String {
//        return if (next !=null){
//            "$value -> ${next.toString()}"
//        } else {
//            "$value"
//        }
//    }
//}
data class Node<T>(var value: T, var next: Node<T>? = null) {
    override fun toString(): String {
        return if (next != null) {
            "$value -> ${next.toString()}"
        } else {
            "$value"
        }
    }

    /*
    override fun equals(other: Any?): Boolean {
        if (other is Node<*>)
            return value == (other as Node<*>).value
        return super.equals(other)
    }
    */

    fun printInReverse() {
        this.next?.printInReverse()
        // 1
        if (this.next != null) {
            print(" -> ")
        }
        // 2
        print(this.value.toString())
    }
}