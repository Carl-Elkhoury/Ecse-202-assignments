/*
 *First Name : Carl
 *Last Name :El Khoury
 *Id:260806273
 */
/**
 * 
 * @author Carl
 *A stack class
 * @param <T> Generic type to accept any type of data
 */
public class Stack<T>{

	private listNode<T> Top = new listNode<>();//create a node which will be the top of the stack
	/**
	 *  push data to the stack
	 * @param data takes the data of type generic to push to the top of the stack
	 */
	public void push( T data ){
		listNode<T> Newstack = new listNode<T>(data); //create a new node storing data
		if (Top == null){ //if the top is null
			Top=Newstack;	//make it equal to the Newstack
		}
		else { //if the top isn't null
			Newstack.setNodeNext(Top); //make the next node of the new node point to Top
			Top.setNodePrevious(Newstack);// make the previous node the top to the new node
			Top = Newstack; // make the Top equal to the new stack
		}
	}
	/**
	 * returns the top of the queue 
	 * @return the data on top of the stack of type generic
	 */
	public T pop(){
		if(Top==null){ //check if top is empty
			//System.out.print("Error: Stack is empty");
			return null; //if yes the stack is empty to return null
		}
		else{ //if the top is not empty then output its data
			T temp = Top.getData(); //make a temporary variable store the data
			if(Top.getNextNode() != null) //of the next node of Top is not null
				(Top.getNextNode()).setNodePrevious(null); //set the previous node of the next node of top to null
			Top = Top.getNextNode(); //make the top equal to the nextnode
			return temp; //return the data

		}
	}
	/**
	 * peek the top of the stack
	 * @return the data stored on top of the stack without popping it 
	 */
	public T peek(){
		if (Top!=null) // checks if the Top exists
			return Top.getData(); //returns the data inside the top node
		return null; //if the Top doesn't exist return null
	}

}
