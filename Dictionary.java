
public class Dictionary implements DictionaryADT{
	// private variables declared
	private int size,numRecords=0;
	private LinkedList<Record> hashtable[];

	// Constructor that takes the size of the hash map and initialize it  
	public Dictionary(int size) {
		hashtable=new LinkedList[size];
		this.size=size;

	}
	// put method that takes a type record, gets it's key, then put it somewhere unique in the list using the hashFunction. 
	public int put(Record rec)throws DuplicatedKeyException{
		// gets the index from the hash function
		int position=myHashFunction(rec.getKey());

		// checks if it's empty. if it is then add a new node and put record there. 
		if (hashtable[position]==null) {
			hashtable[position]=new LinkedList<Record>();}
		// p is used to go through the node.
		LinkedList<Record> p=hashtable[position];

		// if it's not empty and had a node.
		// it check if the node has elements, if no then it puts the record there. 
		if (p.getElement()==null) {
			p.setElement(rec);
			numRecords+=1;
			return 0;}
		// if it had an element it checks if it's equal to the one on-hand, if yes then throw an exception.
		if (p.getElement().getKey().equals(rec.getKey()))
			throw new DuplicatedKeyException("dublicated");
		// else it checks if the next one is not empty and goes on a loop doing that until next one is empty.
		else while (p.getNext()!=null) {

			// checks again if the element is equal.
			if (p.getElement().getKey().equals(rec.getKey()))
				throw new DuplicatedKeyException("dublicated");

			// assign p to the next node. 
			p=p.getNext();}

		//exits the loop, meaning the value was not found, and we reached the end of the node
		// so it adds the record in a new node. 
		p.setNext(new LinkedList<Record>());
		p=p.getNext();
		p.setElement(rec);
		numRecords+=1;
		return 1;
	}

	// remove method that removes the key from the hash map. or return an exception if it didn't exist. 
	public void remove(String key)throws InexistentKeyException{
		// private variables declared
		int position=myHashFunction(key);
		// p is used to go through the node. 
		LinkedList<Record> p=hashtable[position];

		// if first node on the list was empty, then throw an exception
		if (p==null) throw new InexistentKeyException("nothing here boy");
		else 
			// else if it's not null
			if (p!=null) {
				// it checks if it's the same as the one we want to remove.
				if (p.getElement().getKey().equals(key)) {

					// if it's the one we want to remove, it will check if the next one is null 
					if (p.getNext()==null)
						// then it will just make the current one null. 
						p=null;

					//but if the next one wasn't empty, then it will make it the first node. 
					else if (p.getNext()!=null)
						hashtable[position]=p.getNext();
				}
				// else if the first one wasn't the one intended to remove. 
				// then it goes into a while loop that checks every node. until it founds it. 
				// then depending on it's position it will either link the two around it. 
				// or make it null if it was the last node. 
				else while (p.getNext()!=null) {
					if (p.getNext().getElement().getKey().equals(key)) {
						if (p.getNext().getNext()!=null) {
							p.setNext(p.getNext().getNext());
							break;}
						if (p.getNext().getNext()==null) {
							p.setNext(null);
							break;
						}
						else p=p.getNext();
					}
					// if it goes through the whole node an found nothing, it will throw an exception. 
					if (p.getNext()==null) throw new InexistentKeyException("nothing here boy");}}

	}

	// a method that brings back the record value, based on it's key. 
	public Record get(String key) {
		// again, assigning position, and p to go through the node. 
		int position=myHashFunction(key);
		LinkedList<Record> p=hashtable[position];
		// if there's a value at that position
		if (p!=null)
			// then it will go through a while loop that checks if the element's key the exact same key we have on-hand. 
			while (p.getElement()!=null) {
				if (p.getElement().getKey().equals(key))
					// if it's then it will return the record value there. 
					return p.getElement();
				else if(p.getNext()!=null)
					p=p.getNext();
				// else if reaches the end of the node. and nothing was the same
				// it will break and return null. 
				else break;}
		return null;}

	// method that returns the number of record added so far.
	public int numRecords()	{
		return numRecords; 
	}
	// my hash function method that takes the key and return an index linked to it. 
	private int myHashFunction(String key) {
		double hash = 0;
		int x=37;
		double M=size;
		for (int i = 0;i<key.length();i++) {
			hash+=((int)key.charAt(i)*Math.pow(x, i))%M;
		}

		return (int) (hash%M);

	}
}
