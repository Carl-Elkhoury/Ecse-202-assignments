/*
 *First Name : Carl
 *Last Name :El Khoury
 *Id:260806273
 */
/**
 * 
 * @author Carl
 * A queue class
 * @param <T> Generic type to accept any type of data
 */
public class Queue<T> {


	private listNode<T> front;
	private listNode<T> back;

	/**
	 * Enqueue data to the Queue
	 * @param data is the data added to the queue
	 */
	public void Enqueue(T data)
	{
		listNode<T> NewBackNode = new listNode<T>(data); //create a new node

		if (back == null){ // if back is null then the queue is empty
			back = NewBackNode; // make the back and the front equal to the new node
			front = NewBackNode;			
		}
		else{ //if not
			NewBackNode.setNodeNext(back); //set the back of the new node to the back of the queue
			back.setNodePrevious(NewBackNode);// set the previous node of the (old) back to the new node
			back = NewBackNode;// set the new back to the node
		}
	}
	/**
	 * Dequeue the front of the queue
	 * @return the top of the queue of type generic
	 */
	public T Dequeue(){
		if (front == null){ // if the front is null then the stack is empty
			//System.out.println("Error: no queued elements");
			return null; //returns null
		}
		else {//if not we output the data in the front
			T temp = front.getData(); //read he data in front 
			front=(front.getPreviousNode()); //make front equal previous node
			if (front == null){ //if the new front is null(means the stack is now empty)
				back = null;//make back and front equal to null
				front = null;
			}
			return temp; // return the data
		}
	}
	/**
	 * read the data of the queue without dequeuing
	 * @return the data in the front node of generic type 
	 */
	public T peek(){
		if(front !=null)
			return front.getData(); //return data
		return null;
	}
	/**
	 * print the Queue from front to back (this destroys the queue)
	 */
	public void print(){
		if (front != null){//if there is a front print and recall the function
			System.out.print(front.getData() + " ");
			front=(front.getPreviousNode());
			print();
		}


	}
}
