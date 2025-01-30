

import java.io.BufferedReader;  
import java.io.BufferedWriter; 
import java.io.File; 
import java.io.FileNotFoundException; 
import java.io.FileReader; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.PrintWriter; 
import java.util.Scanner; 

public class A8_Student
{
  BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 

//***********************Add Records*******************************

  public void addRecords()throws IOException
  {
    // Create or Modify a file for Database

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("studentRecords.txt",true)));
    
    int stud_id,roll_no;
    String name,s_class,address;
    float marks;
    String s;
    boolean addMore = false;

    //Read Data 
    do 
    {
       System.out.println("\nEnter Student Id: "); 
       stud_id = Integer.parseInt(br.readLine());
       
       System.out.println("Enter Roll No: "); 
       roll_no = Integer.parseInt(br.readLine());

       System.out.println("Enter Name: "); 
       name = br.readLine();

       System.out.println("Enter Class"); 
       s_class=br.readLine();

       System.out.println("Enter Address"); 
       address=br.readLine();

       System.out.println("Enter Marks ");
       marks=Float.parseFloat(br.readLine());

       //Print to File     
       //pw.println(stud_id+" "+roll_no+" "+name+" "+s_class+" "+address+" "+marks);
       pw.println(stud_id);
       pw.println(roll_no);
       pw.println(name); 
       pw.println(s_class); 
       pw.println(address);
       pw.println(marks); 
       
       System.out.print("\nRecords added successfully !\n\nDo you want to add more records ? (y/n) : ");
       s = br.readLine();

       if(s.equalsIgnoreCase("y"))
       {
         addMore = true;
         System.out.println();
       }else
         addMore = false;
     }while(addMore);
       
     pw.close();
     showMenu();
  }

//***********************Display*******************************

  public void displayRecords()throws IOException
  {
    try 
    {   // Open the File

      BufferedReader file=new BufferedReader(new FileReader("studentRecords.txt"));
      String id;
      int i=1;

       // Read records from the file

      while((id=file.readLine())!=null)
      { 
        System.out.println("\n********************************"); 
        System.out.println("Record :"+(i++));
        System.out.println(" Id : "+id);
        System.out.println(" Roll No : "+file.readLine());
        System.out.println(" Name : "+file.readLine());
        System.out.println(" Class : "+file.readLine());
        System.out.println(" Address : "+file.readLine());
        System.out.println(" Marks :"+file.readLine());
        System.out.println("\n");
      }
      file.close();
      showMenu();
    } 
    catch (FileNotFoundException e) 
    {
      System.out.println(" File Not found Exception Occured...");
    }
  }

//***********************Search*******************************

  public void searchRecords(int id)throws IOException
  {
    BufferedReader file=new BufferedReader(new FileReader("studentRecords.txt"));
    String s;
      
    while((s=file.readLine())!=null)
    {
      if(Integer.parseInt(s)==id)
      {
        System.out.println("****************************"); 
        System.out.println("Student Id : "+s); 
        System.out.println("Roll No : "+file.readLine()); 
        System.out.println("Name : "+file.readLine()); 
        System.out.println("Class : "+file.readLine()); 
        System.out.println("Address : "+file.readLine()); 
        System.out.println("Marks : "+file.readLine());
        
        System.out.println("Record Found Successfylly........\n");
        showMenu();
      }
      else{
            for(int j=0;j<5;j++)
            {
              file.readLine();              
            }	    
      }	
    }
    System.out.println("Record Not Found........\n");
    file.close();
    showMenu();
  }

 //***********************Modify*******************************
 
  public void modifyRecords()throws IOException
  {
    File db = new File("studentRecords.txt");

    File tempDB = new File("studentRecordstemp.txt");

    BufferedReader file = new BufferedReader( new FileReader(db) ); 
    BufferedWriter bw = new BufferedWriter( new FileWriter(tempDB) );
    
    PrintWriter temp = new PrintWriter(bw);
    String id;
    int i=1;

    System.out.println("Enter id of the record you want to edit?");	
    int sid = Integer.parseInt(br.readLine());

    while((id=file.readLine())!=null)
    {
      if(Integer.parseInt(id)==sid)
      {
        temp.println(id);
        System.out.println("Enter roll No : ");
        int r=Integer.parseInt(br.readLine()); 
        temp.println(r);  
        System.out.println("Enter Name : ");
        String n=br.readLine();
        temp.println(n);
        System.out.println("Enter Class");
        String c=br.readLine();
        temp.println(c);
        System.out.println("Enter Address");
        String a=br.readLine();
        temp.println(a);
        System.out.println("Enter Marks "); 
        Float m=Float.parseFloat(br.readLine());
        temp.println(m);

        for(int j=0;j<5;j++)
        {
          file.readLine();
        }

      }else {
              temp.println(id);
              temp.println(file.readLine());	
              temp.println(file.readLine());
              temp.println(file.readLine()); 
              temp.println(file.readLine()); 
              temp.println(file.readLine());
       }
      }
        temp.close();
        file.close();

       boolean x=db.delete();
       if (db.exists()) 
       {
          System.out.println("file Still exist"); 
       } else {
          System.out.println("file deleted Successfully....");
       }

       boolean success = tempDB.renameTo(db);

       System.out.println("File Rename	:"+success);

       System.out.println("File updated successfully...\n");

     showMenu();
    }

//***********************Delete*******************************
   
   public void deleteRecords()throws IOException
 
   {
 
     try
 
       {
  // Open the file
  
          BufferedReader file1 = new BufferedReader(new FileReader("studentRecords.txt"));
  
	  PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("studentRecordstemp.txt",true)));
  
	  
          String sId;
  
	  int flag=0;
   
	  System.out.print("Enter the id of the student you want to delete: ");
  
	  int srchId = Integer.parseInt(br.readLine());
          
	  // Read records from the file
  
	  while((sId = file1.readLine()) != null)
  
	  {
 
  
            if(Integer.parseInt(sId)==srchId)  //delete this
            {

           
   flag=0;
	      System.out.println("Record found...Deleting record");
           
   System.out.println("Student Id: "+ sId);
		System.out.println("Student RollNo: "+ Integer.parseInt(file1.readLine()));
		System.out.println("Student Name: "+ file1.readLine());
		System.out.println("Student Class: "+ file1.readLine());
		System.out.println("Student Address: "+ file1.readLine());
		System.out.println("Student Marks: "+ Float.parseFloat(file1.readLine()));

            }else

		{ //Write non matching part in new file
		  pw.println(sId);
		  pw.println(file1.readLine());
              	  pw.println(file1.readLine()); 
              	  pw.println(file1.readLine()); 
      	    
		  pw.println(file1.readLine());
		  pw.println(file1.readLine());

      		  flag=1;
   
		 }
   
  
	   }
     

           file1.close();
     
      pw.close();

    
       File delName =  new File("studentRecords.txt");
    
       File oldName =  new File("studentRecordstemp.txt");
           File newName =   new File("studentRecords.txt");
           if(delName.delete())
           {

             System.out.println("deleted successfully");
           }else{
 
                 System.out.println("Error");
                }
 
  
        if (oldName.renameTo(newName)){
  
            System.out.println("Renamed successfully");
           }else{
 
                 System.out.println("Error");
                }

  
 
     
         showMenu();
    
    }

         catch(FileNotFoundException e)
 
         {
     
      System.out.println("\nERROR : File not Found !!!");
 
         }

    }


//***********************Clear*******************************


   public void clearRecords()throws IOException
   {
     PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("studentRecords.txt")));
     pw.close();

     System.out.println("\n All Records Deleted Successfully\n"); 
     showMenu();

   }

//***********************Menu*******************************
  
   public void showMenu()throws IOException
  {
    int choice;
    System.out.println("1.Add Records \n2.Display Records \n3.Search Records \n4.Modify Records \n5.Delete Records \n6.Clear all Records \n7.Exit \nEnter Your Choice ");
    choice=Integer.parseInt(br.readLine());
    switch(choice)
    {
      case 1:
        addRecords();
        break;
      case 2:
        displayRecords();
        break;
      case 3:
        System.out.println("Enter Student Id "); 
        int id=Integer.parseInt(br.readLine());
        searchRecords(id);
        break;
      case 4:
        modifyRecords();
        break;
      case 5:
        deleteRecords();
        break;
      case 6:
        clearRecords();
        break;
      case 7:
        System.exit(0);
        break;
      default:
        System.out.println("Wrong Choice....");
        break;
    }
  }

//***********************main method*******************************

  public static void main(String[] args) throws IOException 
  {
     A8_Student s=new A8_Student();
     s.showMenu();
  }
}
