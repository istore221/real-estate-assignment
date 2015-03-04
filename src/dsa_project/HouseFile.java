package dsa_project;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;




public class HouseFile {

	/*
	 *  Serialize the entire list object
	 *  and write it to a file to retrieve it later 
	 *  (reduce no of reads/writes to the physical  file every time we perform create/edit/delete operations) 
	 * 
	 */
	
	private File file;
	
	public HouseFile(){
		
		this.file = new File("realestate.ser");
		
	}
	
	
	
	public boolean writeDataset(DoublyLinkList<House> houselist){
		
		
		// write serialize objects to improve efficiency 
		FileOutputStream fileOut;
		  
		try {
			
			fileOut = new FileOutputStream(file.getAbsolutePath());
			 ObjectOutputStream out = new ObjectOutputStream(fileOut);
			 out.writeObject(houselist);
			 out.close();
	         fileOut.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		}catch(IOException e){
			
			e.printStackTrace();
		}
			         
			     
		return false;
	}
	
	
	public DoublyLinkList<House> getDataset(){
		
		DoublyLinkList<House> houselist = null ;
		
		try{
			
			FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        houselist = (DoublyLinkList<House>) in.readObject();
	        in.close();
	        fileIn.close();
	        
			
		}
		catch(ClassNotFoundException e){
			
			e.printStackTrace();
		}
		catch(IOException e){
			
			e.printStackTrace();
		}
		
		
		return houselist;
		 
		
	}
	
	public void deleteDataset(){
		try {
			writeDataset(new DoublyLinkList<House>());
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}
	

	
	 
}
