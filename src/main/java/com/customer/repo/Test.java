/**
 * 
 */
package com.customer.repo;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author dsure
 *
 */
public class Test {

	public static void main(String s[]) {
		Queue products = new ArrayDeque();
		products.add("PI");
		products.add("p2");
		products.add("p3");
		System.out.print (products.peek()) ;
		System.out.print(products.poll ( )) ;
		System.out.println("");
		/*
		 * products.forEach(name -> { System.out.println(name); });
		 */
	}
}
