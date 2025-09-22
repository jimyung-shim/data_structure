class lnklst<T>(){
    class node<T>(var data: T, var next:node<T>? = null){
        override fun toString(): String {
            return if (next != null){
                "$data -> ${next.toString()}"
            } else "$data"
        }


    }

    private var head: node<T>?=null
    private var tail: node<T>?=null
    private var size:Int = 0

    fun getSize():Int=size

    override fun toString(): String {
        return head.toString()
    }

    private fun isEmpty(): Boolean{
        return size==0
    }

    fun push(data:T): lnklst<T>{
        head=node<T>(data,head)
        if(isEmpty()){
            tail=head
        }
        size++
        return this
    }

    fun append(data:T):lnklst<T>{
        if(isEmpty()) {
            push(data)
            return this
        }else{
            val newNode=node<T>(data)
            tail?.next=newNode
            tail=newNode
        }
        size++
        return this
    }

    fun getNode(index:Int):node<T>?{
        if(index<0&&index>=size)error("Invalid access")
        var currentIndex=0
        var currentNode=head

        while(currentNode?.next!=null && currentIndex<index){
            currentNode=currentNode.next
            currentIndex++
        }
        return currentNode
    }

    fun insert(data:T, index:Int):node<T>{
        if(index<0&&index>=size)error("Invalid access")
        val newNode=node<T>(data,getNode(index)?.next)
        getNode(index)?.next=newNode
        size++
        return newNode
    }

    fun pop():T?{
        if(!isEmpty())size--
        val result=head?.data
        head=head?.next
        return result
    }

    fun backPop():T?{
        if(!isEmpty())size--
        var prev=head
        var current=head
        var next=current?.next

        while(next!=null){
            prev=current
            current=next
            next=current.next
        }
        val result=current?.data
        tail=prev
        prev?.next=null

        return result
    }

    fun removeAfter(index:Int):T?{
        if(index<0&&index>=size)error("Invalid access")
        val temp =getNode(index)
        val result=temp?.next?.data
        if(temp?.next==tail){
            tail=temp
            tail?.next=null
        }

        temp?.next=temp.next?.next

        return result

    }


}