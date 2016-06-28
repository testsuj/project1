package testcases;

import java.util.Random;

public class RandomNumber {
	
	public int random(int min,int max)
	{
		int i=(int) Math.random();
		int randomNum = min+(i* max); 
		
		return randomNum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomNumber m=new RandomNumber();
		int i=m.random(1,100);
		System.out.println(i);
		

	}

}
