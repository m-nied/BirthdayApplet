/*
 * BirthdayApplet.java
 *
 */

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class BirthdayApplet extends Applet implements ActionListener {
    
   
    private Label yearLabel;   //label for year text box
    private TextField yearField;     //text field variable year
    private Label fileName;   //label for file text box
    private TextField fileField;     //text field variable file
    
    private Button displayButton;      //button variable submit
   
    private int inputYear;       //year info from text box
    private String fileLoc = new String("");
   
    public static final int FIRST_DAY = 5;      //constant starting first day of 1582, Friday Oct. 15
    private int firstDayOfInputYear;       //  stores weekday for first day of input year
    public static final int FIRST_YEAR =1582;
    
    //array giving number of days in each month
    static int daysInMonth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
   

    //array giving number of days in each month
    static int firstDayOfMonth[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    
    //array giving the month names in proper order
    static String monthNames[] = {"", "               January", "              February", "                 March", 
                                      "                 April", "                   May", "                  June", 
                                      "                  July", "                August", "             September", 
                                      "               October", "              November", "              December"};
   
   
    private boolean displayCalendar = false;
    private boolean validNumbers = true;

   
    //init method
    public void init() {
        
        
        yearLabel = new Label("   Year (yyyy):");
        yearField = new TextField (10);
        fileName = new Label("Enter the file location ");
        fileField = new TextField (".txt",20);
        displayButton = new Button ("Create Calendar");
        displayButton.addActionListener(this); 
       
        add(yearLabel);
        add(yearField);
        add(fileName);
        add(fileField);
        add(displayButton);
       
        
        //sets size of applet
        setSize(800,800);
  
    }// end init()
    
   
    //takes julian day as parameter and returns the day of the week
    public int getDayOfWeek (int julianDay){
        
        return  (julianDay + firstDayOfInputYear - 1) % 7;

    }// end getDayOfWeek()
    
   public void getYear(int year)
    {
        int diff = 0;
           
        for(int i=FIRST_YEAR; i<year; i++)
        {
            if (isLeapYear((i))){
                 diff +=366;//add 366 to ?
            //int daysInMonth[] = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            }
            else
               diff +=365; // add 365*/
        }
            
        firstDayOfInputYear = (FIRST_DAY + diff) %7;
        
    }
    
    //takes month and day as parameters and returns a numerical value 
    // for the exact day (julian day) of the year (January 1st = 1, December 31 = 365)
    public int getDay (int month, int day) {
        
        int numDays = 0;
        
        for (int m = 0; m < month; m++) {
            
            numDays = numDays + daysInMonth[m];
            
        // end for loop
        }
        numDays = numDays + day;
        
        return numDays;
    }// end getDay ()
    
    
    //returns true if year entered is a leap year and false if not
    public boolean isLeapYear (int year) {
        
          if (year % 4 == 0) 
            if (year % 100 == 0) 
                if (year % 400 == 0)
                    return true;//  Year is evenly divisble by 4, 100 and 400
                else                            
                    return false;    // Year not divisible by 400			
            else 
                return true;         // Year is evenly divisible by 4 but not 100
        else 
            return false;            // Year is not evenly divisible by 4

        
    }// end isLeapYear()

    //Paints calander on the applet viewer
    public void paint (Graphics g) {
    //array giving the of days in each month
        int dayCounter[] = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        boolean leapYear = true;    //isLeapYear's boolean value
    
        int xpos = 100;
        int ypos = 50; 

     //initial if that displays the calendarthat 
       if (displayCalendar){
            if (validNumbers == true){        
         
            int  outRow =0, outCol = 0,count =0; //if statement that determines the amount of days in the month and 
            //positioning of each day in the month, also starting point of days
          
            for(outRow=0; outRow<4; outRow++) {
                for (int row = 0; row < 8; row++){
                    for(outCol=1; outCol<4; outCol++) {  
                        count = 3 * outRow + outCol; // num 1..12
                                          
                        for (int col = 0; col < 7; col++){
                            if (row == 0) {
                                if (col==0)
                                {   
                            
                                 g.drawString(monthNames[count], xpos, ypos);
                                }
                            }
                            else if (row==1) {
                                if (col==0) {
                                   g.drawString("S    M    T    W    T    F    S", xpos, ypos);
                            
                                }
                            }
                            else if (row==2) { // first row of numbers
                                  
                                  if (col < firstDayOfMonth[count])
                                  {
                                        g.drawString(" ", xpos, ypos);
                                  }
                                else{
                                 
                                        g.drawString(String.valueOf(dayCounter[count]), xpos, ypos);
                                        dayCounter[count] = dayCounter[count] + 1;
                                   
                                    
                                }//end else
                                }// end if (row==2)
                              
                            else{ // all numbers or spaces
                                    if (dayCounter[count] <= daysInMonth[count]){
                                        g.drawString(String.valueOf(dayCounter[count]), xpos, ypos);
                                        dayCounter[count] = dayCounter[count] + 1;
                                    }//end if 
                                    else 
                                    {
                                       g.drawString(" ", xpos, ypos);  
                                    }
                                } //end else   
                            
                               xpos = xpos + 20;
                               
                        }// end for COL
                       
                        xpos=xpos + 50;
                        
                        }//end outCol
                    xpos=100;
                    ypos = ypos + 20;
                   
            }// end for ROW  
              xpos=100;
              ypos = ypos + 40;
            }//end outRow    
            
        if(validNumbers == false)
            g.drawString("ERROR - Please input a valid date!!", 200, 250);
       }//end valid number
   
    }//end display calander
}//end paint()    

      public void printToFile()
         {
             int dayCounter[] = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
             String monthNames2[] = {"", "\tJanuary", "\tFebruary", "   \tMarch", 
                                      "\tApril", "  \tMay", "   \t\tJune", 
                                      "\tJuly", "   \tAugust", "    \tSeptember", 
                                      "\tOctober", "\tNovember", "\tDecember"};
              String xpos = new String ("  ");
              
           try { 
                PrintWriter outStream = new PrintWriter(fileLoc);
                int  outRow =0, outCol = 0,count =0; //if statement that determines the amount of days in the month and 
            //positioning of each day in the month, also starting point of days
          
            for(outRow=0; outRow<4; outRow++) {
                for (int row = 0; row < 8; row++){
                    for(outCol=1; outCol<4; outCol++) {  
                        count = 3 * outRow + outCol; // num 1..12
                                         
                        for (int col = 0; col < 7; col++){
                           xpos = "  ";
                            if (row == 0) {
                                if (col==0)
                                {   
                                 outStream.print(monthNames2[count]);
                                }
                            }
                            else if (row==1) {
                                if (col==0) {
                                   outStream.print("S  M  T  W  T  F  S");
                                }
                            }
                            else if (row==2) { // first row of numbers
                                  if (col < firstDayOfMonth[count])
                                  {
                                        outStream.print("  ");
                                  }
                                else{
                                        if(dayCounter[count]>9)
                                            xpos =" ";
                                        outStream.print(String.valueOf(dayCounter[count])+xpos);
                                        dayCounter[count] = dayCounter[count] + 1;
                                }//end else
                                }// end if (row==2)
                              
                            else{ // all numbers or spaces
                                    if(dayCounter[count]>9)
                                            xpos =" ";
                                    
                                    if (dayCounter[count] <= daysInMonth[count]){
                                        outStream.print(String.valueOf(dayCounter[count])+xpos);
                                        dayCounter[count] = dayCounter[count] + 1;
                                    }//end if 
                                    else 
                                    {
                                       outStream.print("   "); 
                                    }
                                   
                                } //end else   
                          outStream.print("");
                        }// end for COL
                     
                    outStream.print("                           ");
                    }//end outCol
                    
                    outStream.println("");
            }// end for ROW  
                xpos="  ";
            }//end outRow    
                 
                outStream.close(); 
            } 
            catch (IOException ex) { 
                System.out.println("IOERROR: " + ex.getMessage() + "\n"); 
                ex.printStackTrace(); 
            }
          }
  
    public void actionPerformed (ActionEvent e) {
        
        validNumbers = true;
        
        //reads string from text field, casts as int, stores in variable
        //inputMonth = Integer.parseInt(monthField.getText());
      
        inputYear = Integer.parseInt(yearField.getText());
        fileLoc = fileField.getText();
        if (inputYear <1582) 
           validNumbers = false;
        
        getYear(inputYear); // initialize for input year
        
        //gets the first day of the week (smtwtfs) for the input month as numeric
        for (int i = 1; i <=12; i++)
        {
            if(isLeapYear(inputYear)){
                daysInMonth[2]=29;
                firstDayOfMonth[i] = getDayOfWeek(getDay(i, 1));
            }
            else 
               firstDayOfMonth[i] = getDayOfWeek(getDay(i, 1)); 
        }    
        // Since user selected display calendar, OK to display calenday
        displayCalendar = true;
        
        
       // call the paint method to display the calendar
      
       repaint();
       printToFile();
       
 
        
    }//end actionPerformed method
   
}// end BirthdayApplet Class