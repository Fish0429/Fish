/**
 * Class RockPaperScissors. Plays repeated games of Rock Paper Scissors
 * with a user.
 *
 * Name: Xingzhou Yu
 * ID: A15853336
 * Account ID: cs12sp20ahq
 * Email: xiy019@ucsd.edu
 */


 /**
 * Name: MyArrayList
 * Purpose: To represent an ArrayList
 */

public class MyArrayList<E> implements MyList<E>{

  //constants
  public static final int DEFULT_SIZE = 10;
  public static final int TWO = 2;

  //instance variables

  //The underlying data structure of the ArrayList
  Object[] data;
  //the number of valid elements in data array
  int size;

  //constructors

  /**
  * Name: MyArrayList
  * Purpose: Defult Constructor
  * @param none
  * @return none
  */
  public MyArrayList (){

    data = new Object[DEFULT_SIZE];

  }

  /**
  * Name: MyArrayList
  * Purpose: creat arraylist with initialCapacity
  * @param int initialCapacity
  * @return none
  */
  public MyArrayList (int initialCapacity){

    //check initialCapacity
    if(initialCapacity < 0){
      throw new IllegalArgumentException();
    }

    data = new Object[initialCapacity];

  }

  /**
  * Name: MyArrayList
  * Purpose: creat arraylist copy arr
  * @param E[] arr
  * @return none
  */
  public MyArrayList (E[] arr){

    //check null
    if(arr == null){

      data = new Object[DEFULT_SIZE];
    }else{

      data = new Object[arr.length];
      size = arr.length;
      for(int i = 0; i < arr.length; i++){
        this.data[i] = arr[i];
      }
    }
  }


  //public methods

  /**
  * Name: checkCapacity
  * Purpose: check and expand the capacity of an array
  * @param int requiredCapacity
  * @return void
  */
  @Override
  public void checkCapacity(int requiredCapacity){

    //get the capacity
    int capacity = this.getCapacity();

    //if capacity is bigger
    if(capacity >= requiredCapacity){
      return;
    }

    //if capacity is not enough
    if(capacity < requiredCapacity){
      //if capacity is 0
      if(capacity == 0){
        capacity = DEFULT_SIZE;
      }
      //otherwise double the capacity
      else{
        capacity *= TWO;
      }
    }

    // check capacity again
    if(capacity < requiredCapacity){
      capacity = requiredCapacity;
    }

    //create a new array with the new capacity
    Object[] newData = new Object[capacity];

    //copy the original array
    for (int i = 0; i < this.data.length; i++){
      newData[i] = this.data[i];
    }

    //set newData to be the new data
    this.data = newData;
  }

  /**
  * Name: getCapacity
  * Purpose: get the capacity of an array
  * @param none
  * @return int
  */
  @Override
  public int getCapacity(){
    return this.data.length;
  }

  /**
  * Name: insert
  * Purpose: insert an element at specific index
  * @param int index
  * @param E element
  * @return void
  */
  @Override
  public void insert(int index, E element){

    //check index
    if(index > this.size || index < 0){
      throw new IndexOutOfBoundsException();
    }

    //update size
    this.size++;

    //checkCapacity
    this.checkCapacity(size);

    //reset the data array
    for(int i = size-1; i > index; i--){
      this.data[i] = this.data[i-1];
    }
    //set the element at index
    this.data[index] = element;
  }

  /**
  * Name: append
  * Purpose: append an element at specific index
  * @param E element
  * @return void
  */
  @Override
  public void append(E element){

    this.insert(size, element);
  }

  /**
  * Name: prepend
  * Purpose: prepend an element at specific index
  * @param E element
  * @return void
  */
  @Override
  public void prepend(E element){

    this.insert(0, element);
  }

  /**
  * Name: get
  * Purpose: get an element at specific index
  * @param int index
  * @return E
  */
  @Override
  @SuppressWarnings("unchecked")
  public E get(int index){

    //check index
    if(index < 0 || index >= size){
      throw new IndexOutOfBoundsException();
    }

    return (E) data[index];
  }

  /**
  * Name: set
  * Purpose: set an element at specific index
  * @param int index
  * @param E element
  * @return E
  */
  @Override
  public E set(int index, E element){

    //check index
    if(index < 0 || index >= size){
      throw new IndexOutOfBoundsException();
    }

    //set new element
    this.data[index] = element;
    //return the resetted element
    return element;
  }

  /**
  * Name: remove
  * Purpose: remove an element at specific index and return it
  * @param int index
  * @return E
  */
  @Override
  @SuppressWarnings("unchecked")
  public E remove(int index){

    //get the element at index
    E element = (E) this.data[index];

    //check index
    if(index < 0 || index >= size){
      throw new IndexOutOfBoundsException();
    }

    //update size
    this.size--;

    //resset the data array
    for(int i = index; i < size; i++){

      this.data[i] = this.data[i+1];
    }

    //return the removed element
    return element;
  }


  /**
  * Name: size
  * Purpose: return the size of current arraylist
  * @param none
  * @return int
  */
  @Override
  public int size(){
    return size;
  }

  /**
  * Name: trimToSize
  * Purpose: set capacity to size
  * @param none
  * @return void
  */
  @Override
  public void trimToSize(){

    //create a new array with size size
    Object[] newData = new Object[this.size];

    //copy the elements
    for (int i = 0; i < this.size; i++){
      newData[i] = this.data[i];
    }

    //set data to newData
    this.data = newData;
  }


}
