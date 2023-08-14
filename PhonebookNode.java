//Programmer: Ben Rathbone
//CS 145
//Date: 7-17-23
//Assignment: Assignment 2 - Phonebook
//Purpose: A single entry (node) in the phonebook.  Includes first and last name,
//         phone number, address, and a link to another node.

public class PhonebookNode
{
   //---fields---
   public String first;
   public String last;
   public String number;
   public String address;
   public String city;
   public PhonebookNode next;
   
   //---constructors---
   
   //constructs a PhonebookNode with given fields and null link
   public PhonebookNode(String first, String last, String number, String address, String city)
   {
      this.first = first;
      this.last = last;
      this.number = number;
      this.address = address;
      this.city = city;
      next = null;
   }//end of constructor
   
   //constructs a PhonebookNode with given fields and given link
   public PhonebookNode(String first, String last, String number, String address, String city,
                        PhonebookNode next)
   {
      this.first = first;
      this.last = last;
      this.number = number;
      this.address = address;
      this.city = city;
      this.next = next;
   }//end of constructor
   
   //---methods---
   
   //returns a string representation of this node
   public String toString()
   {
      int i = 1;
      String s = first; //start the string with first name
      if (!first.isEmpty() && !last.isEmpty())  //if there is a first & last name
      {
         s += " ";   //add a space
         i--;        //and an extra dash
      }
      s += last + "\n"; //add last name
      for (i = i; i < first.length() + last.length() + 1; i++)
      {
         s += "-";   //add dashes
      }
      //add the rest of the contact info
      s += "\nPhone Number: " + number + "\n";
      s +=   "Address:      " + address + "\n";
      s +=   "City:         " + city + "\n";
      return s;
   }//end of toString method
}//end of class