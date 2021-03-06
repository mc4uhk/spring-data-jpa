package hk.mc4u.backend.test;

import hk.mc4u.backend.model.Person;

public class Test01 {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		
		doSomething(new String[3]);
		Object o = doSomethingElse(Person.class);
		
		System.out.println("1: "+o);
		
	
		Test01 t = new Test01();
	
		Point<Integer> p = t.new Point<Integer>(1,2);
		p.print();
	}
	
	private static <T> T doSomething(T something) {
		
		return something;
	}

	private static <T> T doSomethingElse(Class<T> someClass) throws InstantiationException, IllegalAccessException {
		System.out.println(someClass);
		return someClass.newInstance();
	}

	public class Point<T>{
		private T x;
		private T y;
		

		public Point(T x, T y) {
			super();
			this.x = x;
			this.y = y;
		}


		private void print() {
			System.out.println(x+":"+y);
		}
		
	}
}
