/**
 * 
 */
package com.ffbet.fase3.domain;

/**
 * @author Marco
 *
 */
public enum UserAdminNames {
	USER_ADMIN_1("ADMIN"),
	USER_ADMIN_2("ADMIN2");
	
	private final String name;
	
	private UserAdminNames(String s){
		name = s;
	}
	
	public boolean equalsNames(String nick){
		return name.equals(nick);
	}
	
	public String toString(){
		return this.name();
	}
}
