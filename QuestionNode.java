// Paul Lam
// Section AG, Neel jog
// A7 QuestionNode
// QuestionNode creates a structre that stores the information in a 20 questions game.

public class QuestionNode {

   public String data;		
    
   public QuestionNode yesNode;
   
   public QuestionNode noNode;
	
   // Purpose: Creates a node with the given information.
   // Pre: No pre conditions.
   // Post: Takes the given information and consturcts a node.
   // Parameters: data = the information given in regards to the game.	
   public QuestionNode(String data) {
      this(data, null, null);
   }		
   
   // Purpose: Creates a node with the given information.
   // Pre: No pre conditions.
   // Post: Takes the given game information from the user and creats a node to 
   // store given information.
   // Parameters: data = information given in regards to the game.
   // yesNode = the correct answer in regards to data.
   // noNode = the incorrect answer in regards to the data.
   public QuestionNode(String data, QuestionNode yesNode, QuestionNode noNode) {
      this.data = data;
      this.noNode = noNode;
      this.yesNode = yesNode;
   } 
}