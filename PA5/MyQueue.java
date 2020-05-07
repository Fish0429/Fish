/**
 *
 * Name: Xingzhou Yu
 * ID: A15853336
 * Account ID: cs12sp20ahq
 * Email: xiy019@ucsd.edu
 */


import java.io.*;
import java.util.*;

/**
* Name: MyQueue
* Purpose: creates a queue
*/
public class MyQueue<E> implements QueueInterface<E> {


	//instance variables

	MyDeque<E> theQueue;

	//constructor

	/**
	* Name: MyQueue
	* Purpose: creates a queue of a given initialCapacity
	* @param int initialCapacity
	*/
	@SuppressWarnings("unchecked")
	public MyQueue (int initialCapacity){

		this.theQueue = new MyDeque(initialCapacity);
	}

	//methods

	/**
	* Name: empty
   	* Purpose: Checks whether or not the stack is empty
    * @return True if there are no elements in the StackInterface, false
    * otherwise.
    */
	@Override
    public boolean empty(){
		//check stack size
		if (theQueue.size() == 0){
			return true;
		}
		return false;
	}

    /**
	* Name: enqueue
   	* Purpose: Adds the specified element to the back of queue
    * @param element the element to add to the stack
    * @throws NullPointerException if the specified element is null.
    */
	@Override
	@SuppressWarnings("unchecked")
    public void enqueue(E e){
		theQueue.addLast(e);
	}

    /**
	* Name: dequeue
   	* Purpose: Removes an element from the front of the queue
    * @return  the element removed, or null if the size was zero.
    */
	@Override
	@SuppressWarnings("unchecked")
    public E dequeue(){
		return theQueue.removeFirst();
	}

    /**
	* Name: peek
   	* Purpose: Returns the element at the front of the queue.
    * @return  the element at the top, or null if the size was zero.
    */
	@Override
	@SuppressWarnings("unchecked")
    public E peek(){
		return theQueue.peekFirst();
	}


}
