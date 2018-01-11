/*
 *First Name : Carl
 *Last Name :El Khoury
 *Id:260806273
 */
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * @author Carl
 * Class that converts infix expressions to profix (can take parenthesis and powers)
 * and then computes the output of te expression and return it in a String
 * **supports parenthesis and unary operator**
 */
public class JCalc {


	public static String main(String args){

//		Scanner in = new Scanner(System.in); //create scanner to take the input of the user
		//String inputstring = args[1]; //create a string that will store the input
		StringTokenizer token; //create a tokenizer to split the input into multiple string
		int numberOfElement = 0;	//create a counter to count the number of input
		//System.out.print("Enter expression: ");
		String inputstring = args.replace(" ",""); //read the input with the scanner 
		String originalInput = inputstring;
		inputstring = unaryOpera(inputstring);
//		System.out.println(inputstring);
		token = new StringTokenizer(inputstring,"+-*/()^y",true);
		//split the then input string after removing the spaces 
		Queue<String> inputqueue = new Queue<>(); //create a queue to store the inputs
		while(token.hasMoreTokens()){ //output everything to the input queue
			inputqueue.Enqueue(token.nextToken());
			numberOfElement++; //count the number of queued elements
		}
		Queue<String> profix=convert(inputqueue,numberOfElement); //pass the input queue and the numberofelements to the converter 
		//System.out.print(originalInput + " = ");
		String a =calculateoutput(profix);
		//System.out.println(a);
		return a;
	}
	/**
	 * convert the input into an profix expression
	 * @param queue that contains the expression
	 * @param numberOfElement the number of queued elements
	 */
	 static Queue<String> convert(Queue<String> inputqueue, int numberOfElement){

		String currentinput; //String that is the current input
		Queue<String> output = new Queue<String>(); //create an output queue
		Stack<String> stack= new Stack<>(); //create a stack required for the convertion


		for(int i=0 ; i<numberOfElement ; i++){
			currentinput = inputqueue.Dequeue(); //dequeue one element of the queue

			if(currentinput  == null) //if the input is null is ignore it
				continue;

			if(isNumber(currentinput)){ //if the input is an number enqueue it to the ouput
				output.Enqueue(currentinput);
				continue;
			}
			//if not it is an operator
			if(stack.peek() == null){ //check if the stack is empty 
				stack.push(currentinput);//if yes push the operator to the stack
				continue; //go to the next input
			}

			if(!currentinput.equals(")") && !currentinput.equals("(")){ //check if the input is not a parenthesis
				while(true){ //if it isn't check the precedences of the top of the stack and compare it to the precedences of the input
					if ((precedenceOf(currentinput))>(precedenceOf(stack.peek())) && !currentinput.equals(")")){
						//if the precedence of the top data is smaller then the precedence of the input
						stack.push(currentinput);//push the input on the top of the stack
						break;
					}else{//if not pop the stack and add it to the output queue
						if(stack.peek() != null){
							output.Enqueue(stack.pop());
						}else{
							System.out.println("unexpected error stack empty");
							return null;
						}
					}
				}
				continue;
			}
			if(currentinput.equals("(")){ //if the current input is an ( just push it to the stack
				stack.push(currentinput);
				continue;
			}
			if(currentinput.equals(")")){ //if the current input is )
				while(!((stack.peek()).equals("("))){ //while the top of the stack is not (
					output.Enqueue(stack.pop());//enqueue de top
				}
				stack.pop();// pop the ( because we dont want it in the output stack
				continue;
			}
		}
		while (stack.peek() != null){// pop every value of the stack and enqueue it
			output.Enqueue(stack.pop());
		}
		return output; //print the ouput queue
	}
	/**
	 * check if the current input is a number
	 * @param tocheck is the String to check
	 * @return true if it is an number false otherwise
	 */
	 static boolean isNumber(String tocheck){

		try{
			Integer.parseInt(tocheck);//try to convert the input into a number
		}catch(Exception e){ //if you get an exeption that means that the input is not an integer
			if(tocheck.indexOf('.')>=0) //check if the input has a point then it should be a decimal number 
				return true;
			return false;
		}
		return true;//if doesn't catch anyting then it is an integer return true
	}
	/**
	 * return the precedence of the input
	 * @param input is the operator
	 * @return
	 */
	 static int precedenceOf(String input){
		if (input == null)
			return 0;
		if (input.equals("y") ) // the unary minus has the highest precedence
			return 4;
		if((input.equals("^")))
			return 3;
		if((input.equals("*")) || (input.equals("/") || input.equals("%")))
			return 2;
		if ((input.equals("+")) || (input.equals("-")))
			return 1;

		return -1;


	}
	/**
	 * Calculate the output of a profix
	 * @param Input Takes a queue that stores the profix expression
	 */
	 static String calculateoutput(Queue<String> Input){
		
		Stack<Double> stack = new Stack<Double>(); //create a stack of type Double that is required for the calculation
		String Currentinput;//The value store in the Queue front
		Double temp; // number required for the calculation
		
		while(Input.peek()!=null){ //while the Queue is not empty
			Currentinput = Input.Dequeue();//dequeue and store to the currentinput
			
			if( isNumber(Currentinput)){ //check if the currentinput is an number
				stack.push(Double.parseDouble(Currentinput));//if yes push it to the stack
				continue;
			}
			if(Currentinput.equals("+")){//if the currentinput is an "+"
				stack.push(stack.pop() + stack.pop());//add the 2 previous numbers which are stored in the stack 
				continue;
			}
			if(Currentinput.equals("-")){//if the currentinput is an "-"
				temp = stack.pop(); //pop the first element of the stack
				stack.push(stack.pop()-temp);//substract the numbers in the stack by the one popped in the temp value
				continue;
			}
			if(Currentinput.equals("*")){//if the currentinput is an "*"
				stack.push(stack.pop()*stack.pop());//multiply the 2 previous numbers which are stored in the stack 
				continue;
			}
			if(Currentinput.equals("/")){//if the currentinput is an "/"
				temp = stack.pop(); //pop the first element of the stack
				stack.push(stack.pop() / temp);//devide the top of the stack by the previous popped value in temp
				continue;
			}
			if(Currentinput.equals("y")){ //if the currentinput is an "y" (unary minus)
				stack.push(-stack.pop());//negate the top of the stack
				continue;
			}
			if(Currentinput.equals("^")){
				temp=stack.pop();
				stack.push(Math.pow(stack.pop(), temp));
				continue;
			}
				
			
		}
		return (stack.pop()).toString();// print the result 
		
	}
	/**
	 * Distinguishes between normal minus and unary minus and replace them by "y"
	 * Delete the unary plus because it does not need to be accounted
	 * @param input takes the input expression as a String
	 * @return the modified String expression
	 */
	 static String unaryOpera(String input){
		StringBuilder buildInput = new StringBuilder(input);
		if (input.charAt(0) == '-'){ //if the first element is a - then it is unary to replace it by y
			buildInput.setCharAt(0 , 'y');
		}
			else{
				if (input.charAt(0) == '+'){ // if the first element is a + then it is a unary + just delete it
					buildInput.setCharAt(0 , ' ');
		}
			}
		for(int n=1; n < input.length(); n++){ //loop through the expression
			if(buildInput.charAt(n)== '-') //if you find a - check what is before it
				switch(buildInput.charAt(n-1)){
				case '-'://if it is an operator or a '(' then it is a unary minus
				case '+':
				case '/':
				case '*':
				case '(':
					buildInput.setCharAt(n ,'y');//replace it by y
					continue;
					}
			if(buildInput.charAt(n) == '+')//if you find a '+'
				switch(buildInput.charAt(n-1)){//check what is before it
				case '-'://if it is an operator or a '(' then it is unary
				case '+':
				case '/':
				case '*':
				case '(':
					buildInput.setCharAt(n ,' ');//delete the operator
					continue;
									}
		}
		
		return buildInput.toString().replace(" ","");
		//return the modified expression after removingg any spaces added
	}
}
