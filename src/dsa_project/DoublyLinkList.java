package dsa_project;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;



public class DoublyLinkList<T> implements List<T> , Serializable,Cloneable {



	private static final long serialVersionUID = 1L; // version 


	private Node head;
	private Node tail;
    private int size;
    SortProvider<DoublyLinkList<T>> provider = null;
    
    {
    	this.head = new Node<T>();
    	this.tail = new Node<T>();
    	
    }

    
    public DoublyLinkList(){
    	
    	this.clear();
    }
    
    
    
	public boolean add(T item) {
		
		if(this.size()==0){
			
			//nothing yet
			Node newNode = new Node<T>(this.head, this.tail, item);
			this.head.setNext(newNode);
			this.tail.setPrev(newNode);
			
			
			
			
		}else{
			
			
			// has been already init
			Node iterator = this.getHead();
			
			 while(iterator != null){
				 
				 
				 	if(iterator.getNext().getNext().getNext() == null){
				 		
				 		//add after
				 		Node newNode = new Node<T>(iterator.getNext(), this.tail, item);
				 		iterator.getNext().setNext(newNode);
				 		this.tail.setPrev(newNode);
				 		
				 		
				 		break;
				 	}
				
				    iterator = iterator.getNext();
			 }
			 
			 
			
			
		}
		
		
		this.size++;
		return false;
	}

	
	
	
	

	
	public void add(int pos, T item) {
		
		
		if(this.isEmpty() || pos > (this.size)-1){
			
			throw new IndexOutOfBoundsException();
			
		}
		else{
			
			int position = 0;
			
			Node iterator = this.getHead();
			
			while(iterator != null){
				
				if(iterator.getPrev() != null && iterator.getNext() != null){
					
					
					if(position == pos){
						
						iterator.setData(item);
						return;
						
					}
					position++;
				}
				
				iterator = iterator.getNext();
			}
			
			
			
			
		}
		
		
		
	}

	public boolean addAll(Collection<? extends T> arg0) {
		
		return false;
	}

	public boolean addAll(int pos, Collection<? extends T> arg1) {
	
		return false;
	}
	

	public void clear() {
	
		this.head.setNext(this.tail);
    	this.tail.setPrev(this.head);
    	this.tail.setNext(null);
    	this.size = 0;
		
	}

	public boolean contains(Object object) {
		
		
		
		Iterator<House> iterator = (Iterator<House>) this.iterator();
		
		
		
		while(iterator.hasNext()){
			
			
			if((iterator.next().equals((House)object))){
				
				return true;
			}
		}
		
		
		
		return false;
	}

	public boolean containsAll(Collection<?> list) {
		
		
		
		return false;
	}
	

	public T get(int pos) {
		
		
		Iterator<House> iterator = (Iterator<House>) this.iterator();
		int position = 0;
		
		while(iterator.hasNext()){
			
			
		    	House h = iterator.next();
			
				
				if(position == pos){
					return (T) h;
				}
				position++;
			}
			
			
		
		throw new IndexOutOfBoundsException();
		
	}
	
	
	public T binarySearch(int lotnumber){
		
		
		
				DoublyLinkList<House> houses = new DoublyLinkList<House>();
				
				
				for(House h : this.toArray()){
					
					houses.add(new House(h.getLotnumber(), new Owner(h.getOwner().getFirstname(), h.getOwner().getLastname()), h.getNobedRooms(), h.getSquareFeet(), h.getPrice()));
					
					
				}
				
				
				if(this.provider == null){
					
					
					houses.setProvider(new BubleSortDriver());
					houses.sort();
					
					
				}
				
				
				
				
				int lowerbound = 0;
				int upperbound = houses.size-1;
				
				
				while(upperbound >= lowerbound){
					
					int middle = (lowerbound+upperbound)/2;
					
					House temphouse = houses.get(middle);
					
					
					if(temphouse.getLotnumber() == lotnumber){
						
						return (T) temphouse;
						
					}
					else if(lotnumber < temphouse.getLotnumber() ){
						
						upperbound = middle-1;
						
					}else{
						
						lowerbound = middle+1;
						
						
					}
								
					
				}

		
		return null;
		
	}

	
	
	 
	
	public int indexOf(Object elemet) {
		
		Iterator<House> iterator = (Iterator<House>) this.iterator();
		int position = 0;
		while(iterator.hasNext()){
			
			if(iterator.next().equals((House)elemet)){
				
				return position;
			}
			
			position++; 
			
		}
		
		return -1;
	}

	public boolean isEmpty() {
		
		
		return (this.size == 0)?true:false;
		
	}

	public Iterator<T> iterator() {
		
		
		
	   	Iterator<House> it  = new Iterator<House>() {
			
	   		Node node = getHead();
	   		
			public House next() {
				
				House temphouse = (House) node.getNext().getData();
				node = node.getNext();
				return temphouse;
			}
			
			public boolean hasNext() {
				// check for trailer :D
				return (node.getNext().getNext() != null)?true:false;
				
			}
			
			
		};
		
		return (Iterator<T>) it;
	}

	public int lastIndexOf(Object arg0) {
		
		return 0;
	}

	public ListIterator<T> listIterator() {
		
		return null;
	}

	public ListIterator<T> listIterator(int arg0) {
		
		return null;
	}

	public boolean remove(Object element) {
		
		House house = (House)element;
		
		int index = this.indexOf(house);
		
		if(index < 0){
			
			return false;
		}
		this.remove(index);
		
		return true;
	}

	public T remove(int pos) {
		
		if(this.isEmpty() || pos > (this.size)-1){
			
			throw new IndexOutOfBoundsException();
			
		}

		if(pos == 0 && this.size == 1){
			
			// since there is only on element left in the list
			// reseting the list is much better
			House house = (House) this.get(pos);
			this.clear();
			return (T) house;
			
		}else{
			
			// ooops more elements
			
			Node iterator = this.getHead();
			int position = 0;
			
			while(iterator != null){
				
				if(iterator.getPrev() != null && iterator.getNext() != null){
					
					if(position == pos){
						
						Node removethis = iterator;
						
						removethis.getPrev().setNext(removethis.getNext());
						removethis.getNext().setPrev(removethis.getPrev());
						
						House removedHouse = (House)removethis.getData();
						
						this.size--;
						removethis = null;
						return (T) removedHouse;
						
						
					}
					
					position++;
					
				}
				
				
				iterator = iterator.getNext();
			}
		}
		
		return null;
	}

	public boolean removeAll(Collection<?> arg0) {
		
		return false;
	}

	public boolean retainAll(Collection<?> arg0) {
		
		return false;
	}

	public T set(int arg0, T arg1) {
		
		return null;
	}

	public int size() {
	
		return this.size;
	}

	public List<T> subList(int arg0, int arg1) {
	
		return null;
	}

	
	
	public House[] toArray() {
		
		Iterator<House> iterator = (Iterator<House>) this.iterator();
		
		House[] houses = new House[this.size()];
		int index = 0;
		
		while(iterator.hasNext()){
			
			
			House h = iterator.next();
			
			
				houses[index] = h;
				index++;
			
			
		}
		
		
		return houses;
	}

	public <T> T[] toArray(T[] arg0) {
	
		return null;
		
	}
    
    
    public Node getHead() {
		return this.head;
	}



	public void setHead(Node head) {
		this.head = head;
	}

    

    public Node getTail() {
		return this.tail;
	}



	public void setTail(Node tail) {
		this.tail = tail;
	}



	public Stream<T> parallelStream() {
		// TODO Auto-generated method stub
		return null;
	}



	public boolean removeIf(Predicate<? super T> arg0) {
		// TODO Auto-generated method stub
		return false;
	}



	public Stream<T> stream() {
		// TODO Auto-generated method stub
		return null;
	}



	public void forEach(Consumer<? super T> arg0) {
		// TODO Auto-generated method stub
		
	}



	public T sort(T input) {
		
		
		
		return null;
	}



	public void replaceAll(UnaryOperator<T> arg0) {
		// TODO Auto-generated method stub
		
	}



	public void sort(Comparator<? super T> arg0) {
		
		
	}
	
	
	public void sort() {
		
		
		 this.provider.sort(this);
	}
	
	
	
	public void setProvider(SortProvider<DoublyLinkList<T>> provider) {
		
		 this.provider = provider;
		
	}



	public Spliterator<T> spliterator() {
		// TODO Auto-generated method stub
		return null;
	}


	
	

	



   
    
	
	
	
}
