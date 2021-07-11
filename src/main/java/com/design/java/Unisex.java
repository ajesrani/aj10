package com.design.java;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Unisex {

	public static void main(String []args) {
		
		Bathroom bathroom = Bathroom.getInstance();
		List<Person> users = new ArrayList<Person>();
		for (int i = 0; i < 5; i++) {
			users.add(new Person(("Jack" + i), Sex.MAN, bathroom));
			users.add(new Person(("Jill" + i), Sex.WOMAN, bathroom));
		}
		
		users.stream().map((Person) -> new Thread(Person)).forEach((t) -> {
            t.start();
        });
		users.stream().map((Person) -> new Thread(Person)).forEach((t) -> {
            try {
				t.join();
			} catch (InterruptedException e) { }
        });
		
	}
}

enum Sex {
    NONE, MAN, WOMAN
}

class Person implements Runnable {
	
	private final String name;
    private final Sex sex;
    private final Bathroom bathroom;
    private boolean canLeave;
    private boolean needBathroom;
	
	public Person(String name, Sex sex, Bathroom bathroom) {
        this.name = name;
        this.sex = sex;
        this.bathroom = bathroom;
        this.canLeave = false;
        this.needBathroom = true;
    }
	
	public String getName() {
        return this.name;
    }
    public Sex getSex() {
        return this.sex;
    }
    
    public void useBathroom() {
    	this.bathroom.entry(this);
    	if (this.bathroom.isInTheBathroom(this)) {
    		try {
				TimeUnit.SECONDS.sleep((new Random()).nextInt(1) + 1);
				canLeave = true;
			} catch (InterruptedException e) { }
    	}
    }
    
    public void leaveBathroom() {
    	this.bathroom.exit(this);
    	this.canLeave = false;
    	this.needBathroom = false;
    }

	@Override
	public void run() {
		//System.out.println(this.getName());
		while (this.needBathroom) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException ex) {
				Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
			}
			if ((this.bathroom.getCurrentSex().equals(this.getSex())
                    || this.bathroom.getCurrentSex().equals(Sex.NONE))
                    && !this.bathroom.isFull()
                    && !this.bathroom.isInTheBathroom(this)) {
                this.useBathroom();
            }

            if (this.canLeave) {
                this.leaveBathroom();
            }
		}
	}
	
}

class Bathroom {
	
	private static final int CAPACITY = 5;
    private static Bathroom instance = new Bathroom(CAPACITY);
    private Sex currentSex;
    private final int capacity;
    private LinkedHashSet<Person> users;
    private Lock lock = new ReentrantLock();
    
    public Bathroom(int capacity) {
        this.capacity = capacity;
        this.currentSex = Sex.NONE;
        this.users = new LinkedHashSet<>();
    }
    
    public static Bathroom getInstance() {
        return instance;
    }
    
    public void entry(Person person) {
    	this.lock.lock();
    	try {
    		if (this.isEmpty()) {
                this.currentSex = person.getSex();
            }

    		if (!this.isFull() && !this.users.contains(person)
                    && getCurrentSex().equals(person.getSex())) {
    			if (this.users.add(person)) {
                    System.out.println(person.getName() + " entered the bathroom");
                }
                if (this.isFull()) {
                    System.out.println("The bathroom is full");
                }
    		}
    		
    	} finally {
    		this.lock.unlock();
    	}
    }
    
    public void exit(Person person) {
    	this.lock.lock();
    	try {
    		if (!this.isEmpty()) {
    			if (this.users.remove(person)) {
                    System.out.println(person.getName() + " left the bathroom");
                }
    			if (this.isEmpty()) {
                    System.out.println("The bathroon is empty");
                    this.currentSex = Sex.NONE;
                }
    		}
    	} finally {
    		this.lock.unlock();
    	}
    }
    
    public boolean isEmpty() {
        return this.users.isEmpty();
    }
    public boolean isFull() {
        return this.capacity == this.users.size();
    }
    public boolean isInTheBathroom(Person person) {
        return this.users.contains(person);
    }
    public Sex getCurrentSex() {
        return this.currentSex;
    }
    
}
