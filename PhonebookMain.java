//Programmer: Ben Rathbone
//CS 145
//Date: 7-18-23
//Assignment: Assignment 2 - Phonebook
//Purpose: A program that allows the user to create and modify a phonebook.
//         The user can add new entries, remove entries, and modify entries.
//         They can also view their phonebook, or print it to a file.

import java.util.*;  //imports Scanner
import java.io.*;    //imports File and PrintStream

public class PhonebookMain
{ 
   //main method
   //a menu that allows the user to modify their phonebook
   public static void main(String[] args) throws FileNotFoundException
   {
      Phonebook phonebook = new Phonebook(); //creates Phonebook object 
      Scanner console = new Scanner(System.in); //creates Scanner object
      
      programIntro();   //calls the programIntro method to explain the program
      
      //user menu
      while (true)
      {
         menuText(); //calls the menuText method to explain commands
         
         //accepts user input
         char userInput;
         try 
         {
            userInput = console.nextLine().charAt(0);   //saves input as char "userInput"
            userInput = Character.toUpperCase(userInput);  //converts char to uppercase
         }
         catch (StringIndexOutOfBoundsException e) //if input is invalid
         {
            userInput = 'X';  //set input to X (invalid)
         }
         
         if (userInput == 'V') //view
         {
            if (!phonebook.isEmpty())  //if phonebook is empty
            {
               System.out.println();   //new line
            }
            //print phonebook to console
            System.out.println(phonebook.toString());
         }//end of view
         
         else if (userInput == 'A') //add
         {
            addMenu(phonebook, console);  //calls the addMenu method
         }//end of add
         
         else if (userInput == 'E') //edit
         {
            editMenu(phonebook, console); //calls the editMenu method
         }//end of edit
         
         else if (userInput == 'R') //remove
         {
            removeMenu(phonebook, console); //calls the removeMenu method
         }//end of remove
         
         else if (userInput == 'P') //print
         {
            printToFile(phonebook, console); //calls the printToFile method
         }//end of print
         
         else if (userInput == 'Q') //quit program
         {
            System.out.println("Have a nice day!");
            break;
         }//end of quit
         
         else  //invalid input
         {
            System.out.println("Invalid input.  Please try again.");
         }//end of invalid input
      }//end of user menu while loop
   }//end of main method
   
   //allows the user to add an entry to the end, beginning, or middle of the list
   //accepts Phonebook & Scanner
   public static void addMenu(Phonebook phonebook, Scanner console)
   {
      //user menu
      while (true)
      {
         //strings to be passed to the phonebook
         String[] contactInfo = new String[5];
                  
         //accepts user input
         char userInput;
         if (phonebook.isEmpty())   //if the phonebook is empty
         {
            userInput = 'B';  //set input to B (skips menu)
         }
         else //otherwise (goes through the menu)
         {
            addMenuText(); //calls the addMenuText method to explain the 3 options to the user
            try 
            {
               userInput = console.nextLine().charAt(0);   //saves input as char "userInput"
               userInput = Character.toUpperCase(userInput);  //converts char to uppercase
            }
            catch (StringIndexOutOfBoundsException e) //if input is invalid
            {
               userInput = 'X';  //set input to X (invalid)
            }
         }
         
         if (userInput == 'E') //end
         {
            //calls the contactInfo method to prompt user for contact info
            contactInfo = setContactInfo(contactInfo, console);
            //creates entry
            phonebook.add(contactInfo[0], contactInfo[1],
                          contactInfo[2], contactInfo[3], contactInfo[4]);
            System.out.println("Contact added successfully!");
            break;
         }//end of... end
         
         else if (userInput == 'B') //beginning
         {
            //calls the contactInfo method to prompt user for contact info
            contactInfo = setContactInfo(contactInfo, console);
            //creates entry
            phonebook.add(0, contactInfo[0], contactInfo[1],
                          contactInfo[2], contactInfo[3], contactInfo[4]);
            System.out.println("Contact added successfully!");
            break;
         }//end of beginning
         
         else if (userInput == 'M') //middle
         {
            System.out.println("Where would you like this contact to be added?");
            System.out.print("Index #:      ");
            try
            {
               //prompts user for index number
               int index = console.nextInt();
               console.nextLine();
               //calls the contactInfo method to prompt user for contact info
               contactInfo = setContactInfo(contactInfo, console);
               
               //creates entry
               phonebook.add(index - 1, contactInfo[0], contactInfo[1],
                          contactInfo[2], contactInfo[3], contactInfo[4]);
               System.out.println("Contact added successfully!");
               break;
            }
            catch (InputMismatchException e) //invalid input
            {
               System.out.println("Invalid input.  Please try again.");
               console.nextLine();
            }
            catch (NullPointerException e)   //invalid index
            {
               System.out.println("Invalid index.  Please try again.");
               console.nextLine();
            }
         }//end of middle
         
         else  //invalid input
         {
            System.out.println("Invalid input.  Please try again.");
         }//end of invalid input
      }//end of user menu
   }//end of addMenu method
   
   //allows the user to edit an entry at the given index
   //accepts Phonebook & Scanner
   public static void editMenu(Phonebook phonebook, Scanner console)
   {
      if (phonebook.isEmpty())   //if the phonebook is empty
         {
            System.out.println("Your phonebook is currently empty.");
         }
         else  //if it's not empty
         {
            while (true)
            {
               System.out.println("What entry would you like to edit?");
               System.out.print("Index #:      ");
               try
               {
                  //prompts user for index number
                  int index = console.nextInt();
                  console.nextLine();
                  
                  String[] contactInfo = new String[5]; 
                  //calls the contactInfo method to prompt user for contact info
                  contactInfo = setContactInfo(contactInfo, console);
                  //edits entry
                  phonebook.edit(index - 1, contactInfo[0], contactInfo[1],
                             contactInfo[2], contactInfo[3], contactInfo[4]);
                  System.out.println("Contact edited successfully!");
                  break;
               }
               catch (InputMismatchException e) //invalid input
               {
                  System.out.println("Invalid input.  Please try again.\n");
               }
               catch (NullPointerException e)   //invalid index
               {
                  System.out.println("Invalid index.  Please try again.\n");
               }
            }
         }
   }//end of editMenu method
   
   //allows the user to remove an entry at the given index
   //accepts Phonebook & Scanner
   public static void removeMenu(Phonebook phonebook, Scanner console)
   {
      if (phonebook.isEmpty())   //if the phonebook is empty
         {
            System.out.println("Your phonebook is currently empty.");
         }
         else  //if it's not empty
         {
            while (true)
            {
               System.out.println("What entry would you like to remove?");
               System.out.print("Index #: ");
               try
               {
                  //prompts user for index number
                  int index = console.nextInt();
                  console.nextLine();
                  
                  if (index == 0)
                  {
                     System.out.println("Invalid index.  Please try again.\n");
                  }
                  else
                  {
                     //removes entry
                     phonebook.remove(index - 1);
                     System.out.println("Contact removed successfully!");
                     break;
                  }
               }
               catch (InputMismatchException e) //invalid input
               {
                  System.out.println("Invalid input.  Please try again.\n");
               }
               catch (NullPointerException e)   //invalid index
               {
                  System.out.println("Invalid index.  Please try again.\n");
               }
            }
         }
   }//end of removeMenu()
   
   //prints the phonebook to a file
   //the file will be created in the same location as this .java file
   //accepts Phonebook & Scanner
   public static void printToFile(Phonebook phonebook, Scanner console)
                                           throws FileNotFoundException
   {
      if (phonebook.isEmpty())   //if the phonebook is empty
         {
            System.out.println("Your phonebook is currently empty.");
         }
         else  //if it's not empty
         {
            //prompts user for output file name
            System.out.print("Output file name: ");
            //creates new file object with that name
            File outputFile = new File(console.nextLine());
            //creates PrintStream object to output to file
            PrintStream output = new PrintStream(outputFile);
            
            //outputs phonebook to file
            output.println("_____________");
            output.println("| PHONEBOOK |");
            output.println("‾‾‾‾‾‾‾‾‾‾‾‾‾");
            output.print(phonebook.toString());
            
            System.out.println(outputFile + " created successfully!");
         }
   }//end of printToFile
   
   //prompts the user for contact info and returns it in an array
   //accepts a String array contactInfo, which is the same array that will be returned
   //accpets Scanner console
   public static String[] setContactInfo(String[] contactInfo, Scanner console)
   {     
      System.out.println("Please enter the following information for this contact.");         
      //prompts user for contact info
      System.out.print("First Name:   ");
      contactInfo[0] = console.nextLine();
      
      System.out.print("Last Name:    ");
      contactInfo[1] = console.nextLine();
      
      System.out.print("Phone Number: ");
      contactInfo[2] = console.nextLine();
      
      System.out.print("Address:      ");
      contactInfo[3] = console.nextLine();
      
      System.out.print("City:         ");
      contactInfo[4] = console.nextLine();
      
      return contactInfo;
   }//end of contactInfo method
   
   //explains the purpose of the program
   public static void programIntro()
   {
      System.out.println("_____________");
      System.out.println("| PHONEBOOK |");
      System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾");
      System.out.println("Welcome!");
      System.out.println("This program allows you to");
      System.out.println("create and modify a phonebook.");
   }//end of gameIntro method
   
   //displays menu text explaining the functions to the user
   public static void menuText()
   {
      System.out.println();
      System.out.println("What would you like to do?\n" +
                "  _______________________________________________\n" +
                " | \"V\" | view   | view your phonebook            |\n" +
                " | \"A\" | add    | add a new entry                |\n" +
                " | \"E\" | edit   | edit an existing entry         |\n" +
                " | \"R\" | remove | remove an entry                |\n" +
                " | \"P\" | print  | print your phonebook to a file |\n" +
                " | \"Q\" | quit   | quit the program               |\n" +
                "  ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
   }//end of menuText method
   
   //displays menu text asking the user where they would like to add an entry
   public static void addMenuText()
   {
      System.out.println();
      System.out.println("Where would you like to add an entry?\n" +
                "  ___________________________________________\n" +
                " | \"E\" | at the end of the list              |\n" +
                " | \"B\" | at the beginning of the list        |\n" +
                " | \"M\" | somewhere in the middle of the list |\n" +
                "  ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
   }//end of menuText method
}//end of program