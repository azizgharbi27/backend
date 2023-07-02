package com.example.demo;

import java.util.regex.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.apache.catalina.authenticator.Constants;
import org.eclipse.jdt.internal.compiler.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.*;
import java.util.Calendar;
import com.example.demo.GetMail;
import com.example.demo.model.Candidate;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.LocationTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;

@CrossOrigin
@SpringBootApplication
public class PDFreader {

   
    
	
	public PDFreader() {
		
	}
	
	  
	 
	 
public String FullName(String text) {
	Pattern pat= Pattern.compile("([A-Za-z0-9-_.]+@[A-Za-z0-9-_]+(?:\\.[A-Za-z0-9]+)+)");
	Matcher mat=pat.matcher(text);
	boolean find=mat.find();
	if(find) {
		return mat.group().substring(0, mat.group().indexOf("@"));
	}
	else {
		return "nom pas trouvé";
	}
}

public String Address(String text) {
		  
	Pattern pat=  Pattern.compile("([A-Za-z0-9-_.]+@[A-Za-z0-9-_]+(?:\\.[A-Za-z0-9]+)+)", Pattern.CASE_INSENSITIVE);
		  Matcher mat=pat.matcher(text);
		 
		  boolean find=mat.find();
		    if(find) {
		      
		     return mat.group();
		     
		    } else {
		      return "adresse mail non trouvée";
		      
		    }
		  }
public String phone(String text) {
	 Pattern pat=  Pattern.compile("\\d{8}", Pattern.CASE_INSENSITIVE);
	  Matcher mat=pat.matcher(text);
	 
	  boolean find=mat.find();
	    if(find) {
	      
	     return mat.group();
	     
	    } else {
	      return "telephone non trouvé";
	      
	    }
}
public String Skills(String text) {
	
	
	String m="";
	String regEx="JAVA|JAVASCRIPT|SQL|HTML|CSS|PHP|TYPESCRIPT|POSTGRESQL|PYTHON|AI|MACHINE LEARNING|ERP|ANDROID|MOBILE|REST|ANGULAR|SPRINGBOOT|WEB|BANKING|INSURANCE|MANAGEMENT|BOOTSTRAP|POSTGRESQL|UML|LINUX|NODE.JS|EXPRESS.JS|REACT.JS|NODE|EXPRESS|REACT";
	while(regEx.equals("")==false)
		  {
		
			  Pattern pat=  Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		  
		  Matcher mat=pat.matcher(text);
		 
		  boolean find=mat.find();
		    if(find) {
		      
		      m=m+mat.group()+",";
		      
		     
		    regEx=regEx.replace("|"+mat.group().toUpperCase(), "");
		    regEx=regEx.replace(mat.group().toUpperCase()+"|", "");
		    regEx=regEx.replace(mat.group().toUpperCase(), "");
		    
		    
		     
		    } else {
		      return m;
		      
		    }
		  }
		  return m;
}

public String field(String skills) {
	String f ="";
	if(skills.toUpperCase().contains("BANKING") || skills.toUpperCase().contains("INSURANCE") || skills.toUpperCase().contains("MANAGEMENT") ) {
		f="Business";
		
	}
	else 	{
		f="IT";
	}
	return f;
}
	 
	  //call receiveEmail

	 public void read(String file) {
		Candidate c=new Candidate();
			try 
	 
		    {
		        //Créer une instance PdfReader.
		        PdfReader pdf = new PdfReader("C:\\TestR\\"+file);// 
		        
		   
		        //Récupérer le nombre de pages en pdf.
		        int nbrPages = pdf.getNumberOfPages(); 
		        String content="";
		        //Itérer le pdf à travers les pages.
		        for(int i=1; i <= nbrPages; i++) 
		        { 
		        	
		        
		            //Extraire le contenu de la page à l'aide de PdfTextExtractor.
		             content = content+ PdfTextExtractor.getTextFromPage(pdf, i);
		     
		         
		            //Afficher le contenu de la page sur la console.
		            System.out.println("Contenu de la page : " + content);
		        }
		        		        
		        c.setFullname(FullName(content));
		         c.setEmail(Address(content));
		         c.setSkills(Skills(content));
		         c.setPhone(phone(content));
		        c.setField(field(Skills(content))); 
		        c.setCv(file);
		        
		         

		         System.out.println(c.toString());
		    
		         
		         
		    
		     
		   
		        //Fermez le PdfReader.
		        pdf.close();
		    
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		 
		 
		 
		//save candidate in database 
		 try
         {
           // create a mysql database connection
			   
           String myUrl = "jdbc:mysql://localhost/cms";
          
           Connection conn = DriverManager.getConnection(myUrl, "root", "admin123");
         
           // create a sql date object so we can use it in our INSERT statement
           Calendar calendar = Calendar.getInstance();
           java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
          

           // the mysql insert statement
           String query = " insert into Candidate (fullname, email, phone, skills, field, cv)"
             + " values (?, ?, ?, ?, ?, ?)";

           // create the mysql insert preparedstatement
           PreparedStatement preparedStmt = conn.prepareStatement(query);
           preparedStmt.setString (1, c.getFullname());
           preparedStmt.setString (2, c.getEmail());
           preparedStmt.setString (3, c.getPhone());
           preparedStmt.setString (4,c.getSkills());
           preparedStmt.setString (5,c.getField());
           preparedStmt.setString (6,c.getCv());

           // execute the preparedstatement
           preparedStmt.execute();
           
           conn.close();
         }
         catch (Exception e)
         {
           System.err.println("Got an exception!");
           System.err.println(e.getMessage());
         }
	 }
	 }
	 


	
