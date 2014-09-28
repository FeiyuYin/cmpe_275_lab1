package org.feiyu.cmpe275.Lab1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before; 
import org.junit.Test; 
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
 
public class ProfileServiceTest { 
   
    ProfileServiceImpl profileService; 
 
    @Before 
    public void setUp() throws Exception { 
    	
		ApplicationContext context = new FileSystemXmlApplicationContext("src/main/resource/beans.xml");
		profileService = (ProfileServiceImpl) context.getBean("profileService");
		
		Profile alice = (Profile) context.getBean("profile"); 
		Profile bob = (Profile) context.getBean("profile");
		Profile carl = (Profile) context.getBean("profile");
		
		alice.setAge(18);
		alice.setName("Alice");
		alice.setUserId("Alice"); 
		
		bob.setAge(19);
		bob.setName("Bob");
		bob.setUserId("Bob");
		
		carl.setAge(20);
		carl.setName("Carl");
		carl.setUserId("Carl");
		
		Set<Profile> a = new HashSet<Profile>(); 
		a.add(alice); 
		Set<Profile> b = new HashSet<Profile>(); 
		b.add(bob);
		Set<Profile> c = new HashSet<Profile>(); 
		c.add(carl);
		
		HashMap<String, Set<Profile>> profileMap = new HashMap<String, Set<Profile>>(); 
		profileMap.put(alice.getUserId(), a); 
		profileMap.put(bob.getUserId(), b);
		profileMap.put(carl.getUserId(), c);
		
		List<Profile> profiles = new ArrayList<Profile>(); 
		profiles.add(alice);
		profiles.add(bob);
		profiles.add(carl);
		
		profileService.setProfileMap(profileMap); 
		profileService.setProfiles(profiles);
    } 
 
    @Test(expected = UnauthorizedException.class) 
    public void testA() { 
 
    	System.out.println("testA"); 
    	profileService.readProfile("Bob", "Alice"); 
    } 
 
    @Test 
    public void testB(){ 
 
    	System.out.println("testB"); 
    	profileService.shareProfile("Alice", "Alice", "Bob"); 
    	profileService.readProfile("Bob","Alice"); 
    } 
    
    @Test 
    public void testC(){
    	System.out.println("testC");
    	profileService.shareProfile("Alice", "Alice", "Bob"); 
    	profileService.shareProfile("Bob", "Alice", "Carl");
    	profileService.readProfile("Carl", "Alice");
    }
    
    @Test(expected = UnauthorizedException.class)
    public void testD(){
    	
    	System.out.println("testD");
    	profileService.shareProfile("Alice", "Alice", "Bob"); 
    	profileService.shareProfile("Bob", "Carl", "Alice");
    }
    
    @Test(expected = UnauthorizedException.class) 
    public void testE(){
    	System.out.println("testE");
    	profileService.shareProfile("Alice", "Alice", "Bob");
    	profileService.shareProfile("Bob", "Alice", "Carl");
    	profileService.unshareProfile("Alice", "Carl");
    	profileService.readProfile("Carl", "Alice");
    }
    
    @Test(expected = UnauthorizedException.class) 
    public void testF(){
    	System.out.println("testF");
    	profileService.shareProfile("Alice", "Alice", "Bob");
    	profileService.shareProfile("Alice", "Alice", "Carl");
    	profileService.shareProfile("Carl", "Alice", "Bob");
    	profileService.unshareProfile("Alice", "Bob");
    	profileService.readProfile("Bob", "Alice");
    }
    
    @Test(expected = UnauthorizedException.class) 
    public void testG(){
    	System.out.println("testG");
    	profileService.shareProfile("Alice", "Alice", "Bob");
    	profileService.shareProfile("Bob", "Alice", "Carl");
    	profileService.unshareProfile("Alice", "Bob");
    	profileService.shareProfile("Bob", "Alice", "Carl");
    	profileService.readProfile("Carl", "Alice");
    }
    
} 