package dsa_project;

import java.util.Iterator;



public class BubleSortDriver implements SortProvider<DoublyLinkList<House>> {

	
	
	public void sort(DoublyLinkList<House> input) {
		
		
		
		Iterator<House> ir1 = input.iterator();
		
		
		while(ir1.hasNext()){
			
			House htemp = ir1.next();
			
			Iterator<House> ir2 = input.iterator();
			
			while(ir2.hasNext()){
				
				House httemp2 = ir2.next();
				
				if(htemp.compareTo(httemp2) == -1){
					
					try{
						
						// swap objects
						
						House tempclone = (House) htemp.clone();
						
						htemp.setLotnumber(httemp2.getLotnumber());
						htemp.setNobedRooms(httemp2.getNobedRooms());
						htemp.setOwner(httemp2.getOwner());
						htemp.setPrice(httemp2.getPrice());
						htemp.setSquareFeet(httemp2.getSquareFeet());
						
						httemp2.setLotnumber(tempclone.getLotnumber());
						httemp2.setNobedRooms(tempclone.getNobedRooms());
						httemp2.setOwner(tempclone.getOwner());
						httemp2.setPrice(tempclone.getPrice());
						httemp2.setSquareFeet(tempclone.getSquareFeet());
						
						tempclone = null;
						
					}
					catch(CloneNotSupportedException e){
						e.printStackTrace();
					}
				
					
					
					
					
					
				}
			}
			
			
			
		}
		
		
	}

	
	
	

	

	
	

	
	
	

}
