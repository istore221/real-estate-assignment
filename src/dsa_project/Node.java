package dsa_project;

import java.io.Serializable;

public class Node<T> implements Serializable {

	private static final long serialVersionUID = 1L; // version 
	
	private Node next;
	private Node prev;
	private T data;
	
	public Node(){
		
	}
	
	public Node(Node prev,Node next,T data){
		
		this.next = next;
		this.prev = prev;
		this.data = data;
		
	}

	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public Node getPrev() {
		return prev;
	}
	public void setPrev(Node prev) {
		this.prev = prev;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	 
	 
}
