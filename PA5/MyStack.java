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
* Name: MyStack
* Purpose: creates a stack
*/
public class MyStack<E> implements StackInterface<E> {


	//instance variables

	MyDeque<E> theStack;

	//constructor

	/**
	* Name: MyStack
	* Purpose: creates a stack of a given initialCapacity
	* @param int initialCapacity
	*/
	@SuppressWarnings("unchecked")
	public MyStack (int initialCapacity){

		this.theStack = new MyDeque(initialCapacity);
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
		if (theStack.size() == 0){
			return true;
		}
		return false;
	}

    /**
	* Name: push
   	* Purpose: Pushes the specified element to the top of the stack
    * @param element the element to add to the stack
    * @throws NullPointerException if the specified element is null.
    */
	@Override
	@SuppressWarnings("unchecked")
    public void push(E element){
		theStack.addFirst(element);
	}

    /**
	* Name: pop
   	* Purpose: Removes an element from the top of the stack
    * @return  the element removed, or null if the size was zero.
    */
	@Override
	@SuppressWarnings("unchecked")
    public E pop(){
		return theStack.removeFirst();
	}

    /**
	* Name: peek
   	* Purpose: Returns the element at the top of the stack
    * @return  the element at the top, or null if the size was zero.
    */
	@Override
	@SuppressWarnings("unchecked")
    public E peek(){
		return theStack.peekFirst();
	}


}
