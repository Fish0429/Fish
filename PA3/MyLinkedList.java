/**
 *
 * Name: Xingzhou Yu
 * ID: A15853336
 * Account ID: cs12sp20ahq
 * Email: xiy019@ucsd.edu
 */


import java.util.*;

/**
* Name: MyLinkedList
* Purpose: To represent an MyLinkedList
*/

public class MyLinkedList<E> extends AbstractList<E> {

	int nelems;
	Node head;
	Node tail;

	protected class Node {

		// The data of the node
		E data;
		// Reference to the next node in the linked list
		Node next;
		//Reference to the previous node in the linked list
		Node prev;

		/**
	  * Name: Node
	  * Purpose: Constructor that initializes the node with element
	  * @param E element
	  * @return none
	  */
		public Node(E element)
		{
			this.data = element;
		}

		/**
	  * Name: setPrev
	  * Purpose: Set p as the predecessor node
	  * @param Node p
	  * @return void
	  */
		public void setPrev(Node p)
		{
			this.prev = p;
		}

		/**
	  * Name: setNext
	  * Purpose: Set n as the successor node
	  * @param Node n
	  * @return void
	  */
		public void setNext(Node n)
		{
			this.next = n;
		}

		/**
	  * Name: setElement
	  * Purpose: Set e as the node’s data
	  * @param E e
	  * @return void
	  */
		public void setElement(E e)
		{
			this.data = e;
		}

		/**
	  * Name: getNext
	  * Purpose: Return the node’s successor
	  * @param none
	  * @return Node
	  */
		public Node getNext()
		{
			return this.next;
		}

		/**
	  * Name: getPrev
	  * Purpose: Return the node’s predecessor
	  * @param none
	  * @return Node
	  */
		public Node getPrev()
		{
			return this.prev;
		}

		/**
	  * Name: getPrev
	  * Purpose: Return the node’s data
	  * @param none
	  * @return E
	  */
		public E getElement()
		{
			return this.data;
		}
	}

	/** ListIterator implementation */

	protected class MyListIterator implements ListIterator<E> {

		//Determine the current moving direction of the iterator
		boolean forward;
		//True after calling next or previous
		boolean canRemoveOrSet;
		//Two node references to determine the iterator location.
		Node left,right;
		//Int value of the index of the next node.
		int idx;

		/**
		* Name: MyListIterator
		* Purpose: Constructor that is used to initialize the iterator.
		* @param none
		* @return none
		*/
		public MyListIterator()
		{
			//initialize all variables
			this.left = head;
			this.right = head.getNext();
			this.forward = true;
			this.canRemoveOrSet = false;
			this.idx = 0;
		}

		/**
		* Name: add
		* Purpose: Insert the given item into the list immediately before whatever
		*          would have been returned by a call to next()
		* @param E e
		* @return void
		*/
		@Override
		public void add(E e)
		{
			//check null
			if (e == null){
				throw new NullPointerException();
			}

			//create a new node that store e
			Node newNode = new Node(e);

			//get node and its prev node
			Node node = this.right;
			Node pre = node.getPrev();

			//reconnect them with newNode
			newNode.setNext(node);
			newNode.setPrev(pre);
			pre.setNext(newNode);
			node.setPrev(newNode);

			//update variables
			nelems++;
			this.idx++;
			this.left = newNode;
			this.canRemoveOrSet = false;
		}

		/**
		* Name: hasNext
		* Purpose: Return true if there are more elements other than dummy nodes
		* @param none
		* @return boolean
		*/
		@Override
		public boolean hasNext()
		{
			//check the next node's data
			if (this.right.getElement() != null){
				return true;
			}
			return false;
		}

		/**
		* Name: hasPrevious
		* Purpose: Return true if there are more elements other than dummy nodes
		* @param none
		* @return boolean
		*/
		@Override
		public boolean hasPrevious()
		{
			//check the next node's data
			if (this.left.getElement() != null){
				return true;
			}
			return false;
		}

		/**
		* Name: next
		* Purpose: Return the next element in the list when going forward
		* @param none
		* @return E
		*/
		@Override
		public E next()
		{
			//check next
			if (this.hasNext() == false){
				throw new NoSuchElementException();
			}

			//get the nextNode
			Node node = this.right;
			//update idx, left, right ,and canRemoveOrSet
			this.idx++;
			this.left = this.right;
			this.right = this.right.getNext();
			this.canRemoveOrSet = true;

			//return the data of node
			return node.getElement();
		}

		/**
		* Name: nextIndex
		* Purpose: Return the next idx
		* @param none
		* @return int
		*/
		@Override
		public int nextIndex()
		{
			return this.idx;
		}

		/**
		* Name: previous
		* Purpose: Return the next element in the list when going backward
		* @param none
		* @return E
		*/
		@Override
		public E previous()
		{
			//check prev
			if (this.hasPrevious() == false){
				throw new NoSuchElementException();
			}

			//get the prevNode
			Node node = this.left;
			//update idx, left, right ,and canRemoveOrSet
			this.idx--;
			this.right = this.left;
			this.left = this.left.getPrev();
			this.canRemoveOrSet = true;
			this.forward = false;

			//return the data of node
			return node.getElement();
		}

		/**
		* Name: previousIndex
		* Purpose: Return the prev idx
		* @param none
		* @return int
		*/
		@Override
		public int previousIndex()
		{
			return this.idx-1;
		}

		/**
		* Name: remove
		* Purpose: Remove the last element returned by the most recent call
		* @param none
		* @return void
		*/
		@Override
		public void remove()
		{
			//check canRemoveOrSet
			if(!(this.canRemoveOrSet)){
				throw new IllegalStateException();
			}

			//create a node to store values
			Node node = null;

			//get the returned node
			if(this.forward){
				node = this.left;
			}else{
				node = this.right;
			}

			//get prev and next of node
			Node prevNode = node.getPrev();
			Node nextNode = node.getNext();
			//reconnect them
			node.setElement(null);
			node.setNext(null);
			node.setPrev(null);
			prevNode.setNext(nextNode);
			nextNode.setPrev(prevNode);

			//update variables
			nelems--;

			if(this.forward){
				this.left = prevNode;
				this.idx--;
			}else{
				this.right = nextNode;
			}
			this.canRemoveOrSet = false;

		}

		/**
		* Name: set
		* Purpose: Change the value returned by the most recent next/previous
		* @param E e
		* @return void
		*/
		@Override
		public void set(E e)
		{
			//check null
			if (e == null){
				throw new NullPointerException();
			}
			//check canRemoveOrSet
			if(!(this.canRemoveOrSet)){
				throw new IllegalStateException();
			}

			//create a node to store values
			Node node = null;

			//get the returned node
			if(this.forward){
				node = this.left;
			}else{
				node = this.right;
			}
			//set the data
			node.setElement(e);

			//update canRemoveOrSet
			this.canRemoveOrSet = false;
		}
	}

	//  Implementation of the MyLinkedList Class
	/**
	* Name: MyLinkedList
	* Purpose: Constructor that creates an empty list and
	*          initializes all the necessary variables
	* @param none
	* @return none
	*/
	public MyLinkedList(){
		//initializes nelems, head and tial
		this.nelems = 0;
		this.head = new Node(null);
		this.tail = new Node(null);

		//connect head and tail
		this.head.setNext(tail);
		this.tail.setPrev(head);
	}

	/**
	* Name: size
	* Purpose: Return the number of nodes being stored
	* @param none
	* @return int
	*/
	@Override
	public int size()
	{
		return this.nelems;
	}

	/**
	* Name: get
	* Purpose: Get data within the node at position index.
	* @param int index
	* @return E
	*/
	@Override
	public E get(int index)
	{
		//get the nth Node
		Node node = this.getNth(index);

		//return its content
		return node.getElement();
	}

	/**
	* Name: add
	* Purpose: Add a node into this list by index
	* @param int index
	* @param E data
	* @return void
	*/
	@Override
	public void add(int index, E data)
	{
		//check null
		if(data == null){
			throw new NullPointerException();
		}
		//check index
		if(index < 0 || index > this.nelems){
			throw new IndexOutOfBoundsException();
		}

		//if index equals to the nelems
		if (index == this.nelems){
			this.add(data);
			return;
		}

		//else get the nth Node and its prev Node
		Node node = this.getNth(index);
		Node prevNode = node.getPrev();

		//connect newNode to node and prevNode
		Node newNode = new Node(data);
		newNode.setPrev(prevNode);
		newNode.setNext(node);

		//reset end and tail
		prevNode.setNext(newNode);
		node.setPrev(newNode);

		//update nelems
		this.nelems++;
	}

	/**
	* Name: add
	* Purpose: Add a node at the end into this list
	* @param E data
	* @return boolean
	*/
	public boolean add(E data)
	{
		//check null
		if(data == null){
			throw new NullPointerException();
		}
		//get the node at the end
		Node end = this.tail.getPrev();

		//connect data to end and tail
		Node newEnd = new Node(data);
		newEnd.setPrev(end);
		newEnd.setNext(this.tail);

		//reset end and tail
		end.setNext(newEnd);
		tail.setPrev(newEnd);

		//update nelems
		this.nelems++;

		return true;
	}

	/**
	* Name: set
	* Purpose: Set the element for the node at index to data
	* and return the element that was previously stored in this node
	* @param int index
	* @param E data
	* @return E
	*/
	public E set(int index, E data)
	{
		//check null
		if(data == null){
			throw new NullPointerException();
		}

		//get the nth Node and its prevData
		Node node = this.getNth(index);
		E prevData = node.getElement();

		//set the node's data to new data
		node.setElement(data);

		//return prevData
		return prevData;
	}

	/**
	* Name: remove
	* Purpose: Remove the node from position index
	* @param int index
	* @return E
	*/
	public E remove(int index)
	{
		//get the nth node and its data
		Node node  = this.getNth(index);
		E data = node.getElement();

		//get its prev and next Node
		Node prev = node.getPrev();
		Node next = node.getNext();

		//reconnect them
		node.setPrev(null);
		node.setNext(null);
		prev.setNext(next);
		next.setPrev(prev);

		//update nelems
		this.nelems--;

		//return data
		return data;

	}

	/**
	* Name: clear
	* Purpose: Remove all nodes from the list
	* @param none
	* @return void
	*/
	public void clear()
	{
		//get start and end nodes
		Node start = this.head.getNext();
		Node end = this.tail.getPrev();
		//set their prev and next to null
		start.setPrev(null);
		end.setNext(null);

		//reconnect tail and head
		this.head.setNext(this.tail);
		this.tail.setPrev(this.head);

		//updata nelems
		this.nelems = 0;
	}

	/**
	* Name: isEmpty
	* Purpose: Determine if the list is empty
	* @param none
	* @return boolean
	*/
	public boolean isEmpty()
	{
		if (this.nelems == 0){
			return true;
		}
		return false;
	}

	/**
	* Name: getNth
	* Purpose: returns the Node at a specified index
	* @param int index
	* @return Node
	*/
	protected Node getNth(int index)
	{
		//check index
		if(index < 0 || index >= this.nelems){
			throw new IndexOutOfBoundsException();
		}

		Node node = this.head.getNext();
		//use for loop to find the nth node
		for (int i = 0; i < index; i++){
			node = node.getNext();
		}

		return node;
	}

	public Iterator<E> iterator()
	{
		return new MyListIterator();
	}
	public ListIterator<E> listIterator()
	{
		return new MyListIterator();
	}

}

// VIM: set the tabstop and shiftwidth to 4
// vim:tw=78:ts=4:et:sw=4
