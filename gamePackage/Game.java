package gamePackage;

import java.io.IOException;
import java.util.Scanner;

class Game {

   public static void main(String[] args) throws IOException{

	   Scanner user_input = new Scanner( System.in );
	   	String test;
	   	
        System.out.println("I'm a Simple Program");
        test = user_input.nextLine();
        System.out.print(test);
        
   }
 }