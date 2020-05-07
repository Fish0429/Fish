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
* Name: MyDeque<E>
* Purpose: implement deque
*/
public class MyDeque<E> implements DequeInterface<E> {

	//instance variables

	Object[] data;
	int size;
	int rear;
	int front;

	//constants
	public static final int DEFAULT_CAPACITY = 10;
	public static final int TWO = 2;


	//constructor

	/**
	* Name: MyDeque
	* Purpose: creates a MyDeque of a given initialCapacity
	* @param int initialCapacity
	*/
	public MyDeque(int initialCapacity){

		//check initialCapacity
		if (initialCapacity < 0){
			throw new IllegalArgumentException();
		}

		//initialize variables
		this.data = new Object[initialCapacity];
		this.size = 0;
		this.rear = 0;
		this.front = 0;
	}


	//methods

	/**
	* Name: size
	* Purpose: Return the number of elements that exist in the deque
	* @return the number of elements
	*/
	@Override
    public int size(){
		return this.size;
	}

	/**
	* Name: expandCapacity
	* Purpose: Double the current capacity
	*/
	@Override
    public void expandCapacity(){

		//if the capacity is 0
		if (this.data.length == 0){

			this.data = new Object[DEFAULT_CAPACITY];
			this.rear = 0;
		}else{

			//creat a new array to store values
			Object[] newData = new Object[this.data.length*TWO];

			//loop through data to copy
			for (int i = 0; i < this.size; i++){
				newData[i] = this.data[(this.front+i)%this.data.length];
			}

			this.data = newData;
			this.rear = this.size-1;
		}

		this.front = 0;

	}

	/**
	* Name: addFirst
	* Purpose: Add the specified element to the front of the deque
	* @param E element
	* @throws NullPointerException
	*/
	@Override
    public void addFirst(E element){

		//check null
		if (element == null){
			throw new NullPointerException();
		}

		//check capacity
		if (size == this.data.length){
			this.expandCapacity();
		}

		//update variables
		if (this.size != 0){
			this.front = (this.front-1+this.data.length)%this.data.length;
		}
		this.data[this.front] = element;
		this.size++;

	}

	/**
	* Name: addFirst
	* Purpose: Add the specified element to the rear of the deque
	* @param E element
	* @throws NullPointerException
	*/
	@Override
    public void addLast(E element){

		//check null
		if (element == null){
			throw new NullPointerException();
		}

		//update variables
		if (this.size != 0){
			this.rear = (this.rear+1)%this.data.length;
		}
		this.data[this.rear] = element;
		this.size++;
	}


	/**
	* Name: removeFirst
	* Purpose: Removes and returns the element at the front of the deque
	* @return E the element at the front of the deque
	*/
	@SuppressWarnings("unchecked")
	@Override
    public E removeFirst(){

		//check empty
		if (this.data.length == 0){
			return null;
		}

		//get the element
		E element = (E) this.data[this.front];

		//update variables
		this.data[this.front] = null;
		this.front = (this.front+1)%this.data.length;
		this.size--;

		return element;
	}

	/**
	* Name: removeFirst
	* Purpose: Removes and returns the element at the rear of the deque
	* @return E the element at the rear of the deque
	*/
	@SuppressWarnings("unchecked")
	@Override
    public E removeLast(){

		//check empty
		if (this.data.length == 0){
			return null;
		}

		//get the element
		E element = (E) this.data[this.rear];

		//update variables
		this.data[this.rear] = null;
		this.rear = (this.rear-1+this.data.length)%this.data.length;
		this.size--;

		return element;
	}

	/**
	* Name: peekFirst
	* Purpose: returns the element at the front of the deque
	* @return E the element at the rear of the deque
	*/
	@SuppressWarnings("unchecked")
	@Override
    public E peekFirst(){

		//check empty
		if (this.data.length == 0){
			return null;
		}

		//return the front element
		return (E)this.data[this.front];
	}

	/**
	* Name: peekLast
	* Purpose: returns the element at the rear of the deque
	* @return E the element at the rear of the deque
	*/
	@SuppressWarnings("unchecked")
	@Override
    public E peekLast(){

		//check empty
		if (this.data.length == 0){
			return null;
		}

		//return teh rear element
		return (E)this.data[this.rear];
	}

}
