/**
 *
 * Name: Xingzhou Yu
 * ID: A15853336
 * Account ID: cs12sp20ahq
 * Email: xiy019@ucsd.edu
 */


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable implements MyHashTableInterface {
	//Constant used to double the size and do addition
	final static int CONSTANT_TWO = 2;
	final static int CONSTANT_5 = 5;
	final static int CONSTANT_27 = 27;

	LinkedList<String>[] array;//Array that stores linkedlists
	int nelems;  //Number of element stored in the hash table
	int expand;  //Number of times that the table has been expanded
	int collision;  //Number of collisions since last expansion
	String statsFileName;     //FilePath for the file to write statistics
	//upon every rehash
	boolean printStats = false;   //Boolean to decide whether to write
	//stats to file or not after rehashing

	//Feel free to add more :)

	/**
	* Name: MyHashTable
	* Purpose: creates a hash table of a given size
	* @param int size
	* @return none
	*/
	@SuppressWarnings("unchecked")
	public MyHashTable(int size) {

		//check size
		if (size < 0){
			throw new IllegalArgumentException();
		}

		//initialize variables
		this.array = new LinkedList[size];
		this.nelems = 0;
		this.expand = 0;
		this.collision = 0;
	}

	/**
	* Name: MyHashTable
	* Purpose: creates a hash table of a given size, and fileName
	* @param int size
	* @param String fileName
	* @return none
	*/
	@SuppressWarnings("unchecked")
	public MyHashTable(int size, String fileName){

		//check size
		if (size < 0){
			throw new IllegalArgumentException();
		}

		//check null
		if (fileName == null){
			throw new NullPointerException();
		}

		//initialize variables
		this.array = new LinkedList[size];
		this.nelems = 0;
		this.expand = 0;
		this.collision = 0;
		this.statsFileName = fileName;
		this.printStats = true;
	}

	/**
	* Name: insert
	* Purpose: Inserts element value in the hash table.
	* @param String value
	* @return boolean
	*/
	@Override
	@SuppressWarnings("unchecked")
	public boolean insert(String value) {

		//check null
		if (value == null){
			throw new NullPointerException();
		}

		//check whether value exist
		if(this.contains(value)){
			return false;
		}

		//check load factor
		if ((double)(this.nelems+1)/this.array.length >= (double)2/3){
			this.rehash();
		}

		//find the index to insert
		int index = this.hashString(value);
		//check collision
		if (this.array[index] == null){
			this.array[index] = new LinkedList();
		}else{
			//updata variable
			this.collision++;
		}
		//insert value at the end of the list
		this.array[index].add(value);
		//updata variable
		this.nelems++;
		//return true
		return true;
	}

	/**
	* Name: delete
	* Purpose: delete elements from the hash table
	* @param String value
	* @return boolean
	*/
	@Override
	public boolean delete(String value) {

		//check null
		if (value == null){
			throw new NullPointerException();
		}

		//check whether value exist
		if(!(this.contains(value))){
			return false;
		}

		//else get the index
		int index = this.hashString(value);
		//remove value from the LinkedList
		this.array[index].remove(value);
		//set bucket to null if is empty
		if (this.array[index].isEmpty()){
			this.array[index] = null;
		}
		//update variable
		this.nelems--;

		return true;
	}

	/**
	* Name: contains
	* Purpose: determine if elem is in the hash table
	* @param String value
	* @return boolean
	*/
	@Override
	public boolean contains(String value) {

		//check null
		if (value == null){
			throw new NullPointerException();
		}

		//else get the index
		int index = this.hashString(value);

		//find if the LinkedList contains value
		if (this.array[index] != null){
			if (this.array[index].contains(value)){
				return true;
			}
		}

		return false;
	}

	/**
	* Name: printTable
	* Purpose: Print out the hash table to stdout
	* @param none
	* @return void
	*/
	@Override
	public void printTable() {

		//loop through the array
		for (int i = 0; i < this.array.length; i++){

			//print the index number
			System.out.print(i+":");
			//loop to print the contents
			if (this.array[i] != null){
				for (int j = 0; j < this.array[i].size(); j++){
					if (j == 0){
						System.out.print(" "+this.array[i].get(j));
					}else{
						System.out.print(", "+this.array[i].get(j));
					}
				}
			}
			System.out.println();
		}
	}

	/**
	* Name: getSize
	* Purpose: Returns the number of elements currently stored in the hash table
	* @param none
	* @return int
	*/
	@Override
	public int getSize() {
		return this.nelems;
	}

	/**
	* Name: rehash
	* Purpose: Expand the array and rehash all values
	* @param none
	* @return void
	*/
	@Override
	@SuppressWarnings( "unchecked" )
	public void rehash() {
		//print the stats if true
		if(printStats){
			this.printStatistics();
		}
		//get the new size
		int newSize = this.primeGen();
		//initialize a new array to store values
		LinkedList<String>[] newArray = this.array;

		//resize the array
		this.array = new LinkedList[newSize];

		//loop through the original array
		for (int i = 0; i < newArray.length; i++){

			//loop through each linkedlist if it is not null
			if (newArray[i] != null){
				for (int j = 0; j < newArray[i].size(); j++){
					//get the values
					String value = newArray[i].get(j);
					//find the index to insert
					int index = this.hashString(value);
					if (this.array[index] == null){
						this.array[index] = new LinkedList();
					}
					//insert value at the end of the list
					this.array[index].add(value);
				}
			}
		}

		//update variables
		this.collision = 0;
		this.expand++;
	}

	/**
	* Calculate the hash value of a given string
	* @param str the string value
	* @return the hash value
	*/
	public int hashString(String str){

		//using CRC variant

		//creat variables to store the values
		int h = 0;
		int highorder = 0;

		for (int i = 0; i < str.length(); i++){

			//get the i-th char in the str
			char ki = str.charAt(i);
			// extract the high-order 5 bits from h
			highorder = h & 0xf8000000;
			//Do a 5-bit left circular shift of ​h
			h = h << CONSTANT_5;
			h = h ^ (highorder >>> CONSTANT_27);
			//// XOR h and ki
			h = h ^ ki;
		}

		//get the abs value of h
		h = Math.abs(h);
		//return index based on h
		return h%this.array.length;
	}

	/**
	* Print statistics to the given file.
	* @return True if successfully printed statistics, false if the file
	*         could not be opened/created.
	*/
	@Override
	public boolean printStatistics(){
		PrintStream out;
		try {
			out = new PrintStream( new FileOutputStream( this.statsFileName,
			true ) );
		} catch(FileNotFoundException e) {
			return false;
		}
		out.print(this.expand + " resizes, ");//Print resize times
		//Calculate the load factor
		double loadFactor = ( (double) nelems / array.length );
		DecimalFormat df = new DecimalFormat("#.##"); //Print the load factor
		out.print("load factor " + df.format( loadFactor ) + ", ");
		out.print(this.collision + " collisions, "); //Print collision times
		int length = 0;
		for(int i = 0; i < this.array.length; i++){
			if(this.array[i] != null && this.array[i].size() > length)
			length = this.array[i].size();
		}
		//Print the length of the longest chain
		out.println(length + " longest chain");
		out.close();
		return true;
	}

	/**
	* Generate a prime number that is close to the double of current array
	* size
	* @return a prime number used as array size
	*/
	private int primeGen(){
		boolean isPrime = false;
		int num = array.length*CONSTANT_TWO;//Double the size

		/*
		* Generate next prime number that is greater than the double of
		* current array size
		*/
		while(!isPrime){
			num++;
			/*
			* Try divides the number with all numbers greater than two and
			* less than or equal to the square root of itself
			*/
			for(int divisor = CONSTANT_TWO; divisor <= Math.sqrt(num);
			divisor++){
				if(num % divisor == 0)//The number is divisible
				break;//No need for further testing, break inner loop
				if(divisor == (int)Math.sqrt(num))//The number is indivisible
				isPrime = true;//Then it is a prime
			}
		}
		return num;
	}

	
}
