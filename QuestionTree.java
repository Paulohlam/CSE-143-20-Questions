// Paul Lam
// Section AG, Neel jog
// A7 QuestionNode
// QuestionTree uses the stored information to run the game of yes or no (20 questions).

import java.io.*;
import java.util.*;

public class QuestionTree {
   
   private Scanner console;
   
   private QuestionNode root;
   
   // Purpose: Creates a structure with one node that represents "computer".
   // Pre: No pre conditions.
   // Post: Creates a tree with one leaf node that represents 'computer".
   // Parameters: No parameters.
   public QuestionTree() {
      root = new QuestionNode("computer");
      console = new Scanner(System.in);
   }
   
   // Purpose: Replaces the current tree (question) with the input given by the client.
   // Pre: No pre conditions.
   // Post: Takes the information given by the client and replaces the current tree 
   // Parameters: input = the client given information/questions/answers in regards
   // to the questions asked by the game.
   public void read(Scanner input) {
      root = readHelper(input);
   }
   
   // Purpose: Takes the users input to returns the replaced current tree with an updated one.
   // Pre: No pre conditions.
   // Post: Updates the current tree with a new tree from the clients input and returns it.
   // Parameters: input = the client given information/questions/answers in regards
   // to the questions asked by the game.
   private QuestionNode readHelper(Scanner input) {
      String s = input.nextLine();
      if (s.equals("A:")) {
         return new QuestionNode(input.nextLine());
      } else {
         return new QuestionNode(input.nextLine(), readHelper(input), readHelper(input));
      }
   }
   
   // Purpose: Takes the current tree and creates an output file with the information.
   // Pre: No pre conditions.
   // Post: Creates an output file using the current tree.
   // Parameters: output = The output file. 
   public void write(PrintStream output) {
      writeHelper(output, root);
   }
   
   // Purpose: Takes the current tree and creates an output file.
   // Pre: No pre conditions.
   // Post: Creates an output file using the information from the current tree.
   // Parameters: output = The output file.
   // currRoot = The current tree used to run the game.
   private void writeHelper(PrintStream output, QuestionNode currRoot) {
      if (currRoot.yesNode == null || currRoot.noNode == null) {
         output.println("A:");
         output.println(currRoot.data);
      } else {
         output.println("Q:");
         output.println(currRoot.data);
         writeHelper(output, currRoot.yesNode);
         writeHelper(output, currRoot.noNode); 
      }
   }
   
   // Purpose: Asks the clinet if the series of questions are yes or no until 2
   // conditions. The object is either guess correctly or if the correct object is not
   // able to be guessed, the tree will expand with the clinets object and a new 
   // question in regards to their object.
   // Pre: No pre conditions.
   // Post: Asks the client yes or no to a series of questions until the object is 
   // guess correctly or if failed to guess correctly, will expand to include the clinet's 
   // object and a new question in regards to their object.
   // Parameters: No parameters.
   public void askQuestions() {
      root = askQuestionsHelper(root);
   }
   
   // Purpose: Asks a series of yes or no questions until the correct object is guess or
   // failed to guess correctly. If failed, the tree will expand to include the client's
   // object and a question to seperate it from other objects. Returns the updated tree.
   // Pre: No pre conditions.
   // Post: Asks the client a series of yes or no questions until the object is guessed
   // correctly or failed to be guessed. If failed, the tree will expland to include the 
   // client's object and a question that distinguish the object from other objects.
   // If guess correct, returns the current tree. If failed, returns an updated tree.
   // Parameters: currRoot = the current tree.
   private QuestionNode askQuestionsHelper(QuestionNode currRoot) {
      if (currRoot.yesNode == null || currRoot.noNode == null) {
         if (yesTo("Would your object happen to be " + currRoot.data + "?")) {
            System.out.println("Great, I got it right!");
         } else {
            System.out.print("What is the name of your object? ");
            QuestionNode answerRoot = new QuestionNode(console.nextLine());
            System.out.println("Please give me a yes/no question that");
            System.out.println("distinguishes between your object");
            System.out.print("and mine--> ");
            String newData = console.nextLine();
            if (yesTo("And what is the answer for your object?")) {
               currRoot = new QuestionNode(newData, answerRoot, currRoot);
            } else {
               currRoot = new QuestionNode(newData, currRoot, answerRoot);
            }
         }
      } else {
         if (yesTo(currRoot.data)) {
            currRoot.yesNode = askQuestionsHelper(currRoot.yesNode);
         } else {
            currRoot.noNode = askQuestionsHelper(currRoot.noNode);
         }
      }
      return currRoot;
   }

   // Purpose: Asks the client the given question to see if the asnwer is yes or no.
   // Pre: No pre conditions.
   // Post: Asks the given question and returns the clients response in yes or no (y/s)
   // format.
   // Parameters: prompt = The question being asked.
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }
}