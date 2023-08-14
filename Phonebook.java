//Programmer: Ben Rathbone
//CS 145
//Date: 7-17-23
//Assignment 2 - Phonebook
//Purpose: A class that represents a phonebook.  A phonebook is comprised of multiple
//         entries in the form of PhonebookNode objects.

public class Phonebook
{
   //---fields---
   private PhonebookNode front;  // first value in the list
   
   //---constructor  
   //constructs an empty list
   public Phonebook()
   {
      front = null;  //sets the front node to null
   }//end of constructor
   
   //---methods---
   
   //adds a PhonebookNode to the end of the list
   public void add(String first, String last, String number, String address, String city)
   {
      if (front == null)   //if front is null
      {
         //create new entry at the front
         front = new PhonebookNode(first, last, number, address, city);
      }
      else  //otherwise
      {
         PhonebookNode current = front;   //creates current variable
         while (current.next != null)  //while there is a next entry
         {
            current = current.next; //advance to the next entry
         }
         //create new entry at the end
         current.next = new PhonebookNode(first, last, number, address, city);
      }
   }//end of add method
   
   //inserts a PhonebookNode at the given index
   public void add(int index, String first, String last, String number,
                   String address, String city)
   {
      if (index == 0)   //if adding to the beginning of the list
      {
         //create new entry at the front
         front = new PhonebookNode(first, last, number, address, city, front);
      }
      else  //otherwise
      {
         //creates current variable, sets it to 1 before the given index
         PhonebookNode current = nodeAt(index - 1);   
         //create new entry at the given index
         current.next = new PhonebookNode(first, last, number, address, city, current.next);
      }
   }//end of add method
   
   //removes the PhonebookNode at the given index
   public void remove(int index)
   {
      if (index == 0)   //if removing the first entry
      {
         front = front.next;  //sets front to the next entry after front
      }
      else  //otherwise
      {
         //creates current variable, sets it to 1 before the given index
         PhonebookNode current = nodeAt(index - 1);
         current.next = current.next.next;   //sets the next link to the one after it
      }
   }//end of remove method
   
   //modifies the fields of the PhonebookNode at the given index
   public void edit(int index, String first, String last, String number,
                   String address, String city)
   {
      //creates current variable, sets it to the given index
      PhonebookNode current = nodeAt(index);
      //modifies fields
      current.first = first;
      current.last = last;
      current.number = number;
      current.address = address;
      current.city = city;
   }//end of edit method
   
   //returns a String of the entire phonebook
   public String toString()
   {
      if (front == null)   //if the phonebook is empty
      {
         return "Your phonebook is currently empty.";
      }
      PhonebookNode current = front;   //create current variable
      int index = 1; //set starting index number
      String phonebookString = nodeString(current, index);  //start string with the first entry
      while (current.next != null)  //while there is a next entry
      {
         current = current.next; //advance to the next entry
         index++;                //increase index
         phonebookString += "\n" + nodeString(current, index); //add the entry to the string
      }
      return phonebookString;
   }//end of toString method
   
   //returns true if the phonebook is empty
   //otherwise, returns false
   public boolean isEmpty()
   {
      if (front == null)   //if the phonebook is empty
      {
         return true;
      }
      else  //otherwise
      {
         return false;
      }
   }//end of isEmpty method
   
   //returns a single entry (one PhonebookNode) in the phonebook
   //private, used only by the toString method
   private String nodeString(PhonebookNode current, int index)
   {
      String entry = index + ". ";  //starts the string with the index number
      entry += current.first; //add the first name
      if (!current.first.isEmpty() && !current.last.isEmpty())  //if there is a first & last name
      {
         entry += " ";   //add a space
      }
      entry += current.last + "\n"; //add last name
      int nameLength = entry.length() - 1; //gets the current length of the string
      for (int i = 0; i < nameLength; i++)
      {
         entry += "-";   //adds dashes
      }
      //add the rest of the contact info
      entry += "\nPhone Number: " + current.number + "\n";
      entry +=   "Address:      " + current.address + "\n";
      entry +=   "City:         " + current.city + "\n";
      return entry;
   }//end of nodeString class
   
   //returns the PhonebookNode at the given index
   //private, used by other methods in this class
   private PhonebookNode nodeAt(int index)
   {
        PhonebookNode current = front; //create current variable
        for (int i = 0; i < index; i++)
        {
            current = current.next; //advances current to the index
        }
        return current;
    }
}//end of class