/*
 *First Name : Carl
 *Last Name :El Khoury
 *Id:260806273
 */
/**
 * 
 * @author Carl
 *
 * @param <T> Generic type T to accept any class
 */


public class listNode<T>{

	private T data; // the data in the current node
	private listNode<T> nextnode; // reference to the next node
	private listNode<T> previousnode; // reference to the previous node

	public listNode(T data){
		this.data = data;// adding the specific data to the node
		nextnode = null;//initializing next and previous node to null
		previousnode = null;
	}

	public listNode(){
		this(null); // initializing everything to null
	}

	/**
	 * Returns the element in the node
	 * @return the element of the node
	 */
	public T getData(){
		return data; // returns the data in the node
	}
	/**
	 * This functions returns the reference to the next node of the List 
	 * @return the reference to the next node in the List (returns void if it doesn't exist)
	 */
	public listNode<T> getNextNode(){
		return nextnode; // returns the reference to the next node
	}
	/**
	 * This function returns the reference to the previous node of the List 
	 * @return the reference to the previous node in the List (returns void if it doesn't exist)
	 */
	public listNode<T> getPreviousNode(){
		return previousnode; // returns the reference to the previous node
	}
	/**
	 * This function set the data stored in the current list to the value inputed by the user
	 * @param data is the new element stored in the list which is of Generic type T
	 */
	public void setdata(T data){
		this.data = data; // set the data of the node to the input of the user
	}
	/**
	 * this function set the reference of the next node inside the list to the one inputed by the user
	 * @param nextnode is the new reference of that will be assigned the previous node inputed by the user 
	 */
	public void setNodeNext(listNode<T> nextnode){
		this.nextnode = nextnode; // set the next node to the reference provided by the user
	}
	/**
	 *this function set the reference of the next node inside the list to the one inputed by the user
	 * @param previousnode is the new reference of that will be assigned the previous node inputed by the user 
	 */
	public void setNodePrevious(listNode<T> previousnode){
		this.previousnode = previousnode; // set the previous node to the reference provided by the user
	}



}

