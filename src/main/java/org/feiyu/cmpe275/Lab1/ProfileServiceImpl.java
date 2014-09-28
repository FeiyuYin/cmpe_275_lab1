package org.feiyu.cmpe275.Lab1;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ProfileServiceImpl implements ProfileService{
	
	HashMap<String, Set<Profile>> profileMap; 
	List<Profile> profiles; 
	
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

	public Profile readProfile(String userId, String profileUserId) {
		// TODO Auto-generated method stub
		
		for(Profile prof : profiles){
			
			if(prof.getUserId() == profileUserId){
				
				return prof; 
			}
		} 
		return null;
	}

	public void shareProfile(String userId, String profileUserId, String targetUserId) {
		// TODO Auto-generated method stub
		
		for(Profile prof : profiles){
			
			if(prof.getUserId() == profileUserId){
				
				profileMap.get(targetUserId).add(prof);
				break;
			}
		}
	}

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
