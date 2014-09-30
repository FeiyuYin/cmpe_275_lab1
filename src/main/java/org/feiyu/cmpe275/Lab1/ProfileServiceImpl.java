package org.feiyu.cmpe275.Lab1;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * This class implements the Profileservice interface
*/
public class ProfileServiceImpl implements ProfileService{
	
	/**
	 * HashMap named profileMap
	 * Type of key maintained by this map is String
	 * Type of mapped value is Set of Profiles
	*/
	
	HashMap<String, Set<Profile>> profileMap; 
	List<Profile> profiles;                                // List is created which saves all profile
	
//	public ProfileServiceImpl(HashMap<String, List<Profile>> profiles){
//		
//		this.profiles = profiles; 
//	}
	
	public void setProfileMap(HashMap<String, Set<Profile>> profileMap){
		
		this.profileMap = profileMap; 
	}
	
	public HashMap<String, Set<Profile>> getProfileMap(){
		
		return profileMap; 
	}
	
	public void setProfiles(List<Profile> profiles){
		
		this.profiles = profiles; 
	}
	
	public List<Profile> getProfiles(){
		
		return profiles; 
	}

       /** 
	 
	* Read the profile of another user or oneself. 
	 
	* @param userId the ID of the current user 
	 
	* @param profileUserId the ID of user, whose profile is being requested 
	 
	* @return the profile for profileUserId   
	 
	*/ 



	public Profile readProfile(String userId, String profileUserId) {
		// TODO Auto-generated method stub
		
		for(Profile prof : profiles){
			
			if(prof.getUserId() == profileUserId){
				
				return prof; 
			}
		} 
		return null;
	}
	
	
	
	/** 
	 
	* Share a profile with another user. The profile may or may not belong to the current user. 
	 
	* @param userId the ID of the current user 
	 
	* @param profileUserId the ID of the user, whose profile is being shared 
	 
	* @param targetUserId the ID of the user to share the profile with 
	 
	*/ 
	

	public void shareProfile(String userId, String profileUserId, String targetUserId) {
		// TODO Auto-generated method stub
		
		for(Profile prof : profiles){
			
			if(prof.getUserId() == profileUserId){
				
				profileMap.get(targetUserId).add(prof);
				break;
			}
		}
	}
	
	
	/** 
	 
	* Unshare the current user's own profile with another user. 
	 
	* @param userId 
	 
	* @param targetUserId 
	 
	*/ 


	public void unshareProfile(String userId, String targetUserId) {
		// TODO Auto-generated method stub
		
		for(Profile prof : profileMap.get(targetUserId)){
			
			if(prof.getUserId() == userId){
				
				profileMap.get(targetUserId).remove(prof);
				break;
			}
		}
	}

}
