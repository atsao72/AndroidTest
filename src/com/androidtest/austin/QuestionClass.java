package com.androidtest.austin;

import java.util.ArrayList;
import java.util.Random;

public class QuestionClass {
	private int question1;
	private int question2;
	private String operation;
	private Random rand;
	private ArrayList<String> operations = new ArrayList<String>();
	private ArrayList<String> firstOperation = new ArrayList<String>();
	private int answer;
	
	public QuestionClass(int level){
		rand=new Random();
		operations.add("Add "); operations.add("Subtract "); operations.add("Multiply by "); operations.add("Divide by ");
		firstOperation.add("+"); firstOperation.add("-"); firstOperation.add("x"); firstOperation.add("/");
		answer=0;
	}
	
	public String getFirstEquation(){
		answer=0;
		question1 = rand.nextInt(20)+1;
		int operationInt = rand.nextInt(firstOperation.size());
		operation=firstOperation.get(operationInt);
		question2 = rand.nextInt(20)+1;
		switch(operationInt){
			case 0:
				answer = question1 + question2;
				break;
			case 1:
				if(question1<=question2){
					while(question1<=question2){
						question1 = rand.nextInt(20)+1;
						question2 = rand.nextInt(20)+1;
						answer = question1 - question2;
					}
				}
				else{
					answer = question1 - question2;
				}
				break;
			case 2:
				question1=rand.nextInt(10)+1;
				question2 = rand.nextInt(10)+1;
				answer = question1 * question2;
				break;
			case 3:
				//question2 = rand.nextInt(10)+1;
				if(question1%question2!=0){
					while(question1%question2!=0){
						question1 = rand.nextInt(10)+1;
						question2 = rand.nextInt(10)+1;
						answer = question1 / question2;
					}
				}
				else{
					answer = question1 / question2;
				}
				break;
		}
		return question1 + operation + question2;
	}
	
	public String getAdditionalEquation(){
		question1 = rand.nextInt(20)+1;
		int operationInt = rand.nextInt(operations.size());
		operation = operations.get(operationInt);
		switch(operationInt){
		case 0:
			answer = answer + question1;
			break;
		case 1:
			if(answer<=question1){
				while(answer<=question1){
					question1 = rand.nextInt(20)+1;
				}
				answer = answer - question1;
			}
			else{
				answer = answer - question1;
			}
			break;
		case 2:
			question1 = rand.nextInt(10)+1;
			answer = answer * question1;
			break;
		case 3:
			//question1 = rand.nextInt(10)+1;
			if(answer!=0 && answer%question1!=0 || answer<question1){
				while(answer%question1!=0 || answer<question1){
					question1 = rand.nextInt(10)+1;
					}
				answer = answer / question1;
				break;
			}
			else{
				answer = answer / question1;
			}
			break;
		}
		return operation + question1;
	}
	
	public String getAnswer(){
		return Integer.toString(answer);
	}
}
