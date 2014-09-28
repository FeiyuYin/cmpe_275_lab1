package org.feiyu.cmpe275.Lab1;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


@Aspect
public class ProfileServiceLoggingAspect {

//	private Log log = LogFactory.getLog(this.getClass());
	@Around("execution(* ProfileServiceImpl.readProfile(..))")
	public void aroundRead(ProceedingJoinPoint joinPoint) throws Throwable{
 
		ProfileServiceImpl profileService = (ProfileServiceImpl)joinPoint.getTarget();
		Object [] args = joinPoint.getArgs(); 
		System.out.println(args[0] + " reads the profile of " + args[1]);
		
		if (!profileService.getProfileMap().containsKey(args[0])){
			
			System.out.println(args[0] + " does not exist!");  
			return; 
		}
		if(!profileService.getProfileMap().containsKey(args[1])){
			
			System.out.println(args[1] + " does not exist");  
			return; 
		}
		
		boolean okToRead = false; 
		
		for(Profile prof : profileService.getProfileMap().get(args[0])){
			
			if(prof.getUserId() == args[1]){
				
				System.out.println("OK to read the profile."); 
				okToRead = true; 
				break; 
			} 
		}
		if(okToRead){Object result = joinPoint.proceed();}
		else{
			
			System.out.println("Does not have the priviledge to read!"); 
			throw new UnauthorizedException("Does not have the priviledge to read exception!");
		}
	}
	
	@Around("execution(* ProfileServiceImpl.shareProfile(..))")
	public void aroundShare(ProceedingJoinPoint joinPoint) throws Throwable{

		ProfileServiceImpl profileService = (ProfileServiceImpl)joinPoint.getTarget();
		Object [] args = joinPoint.getArgs(); 
		System.out.println(args[0] + " shares the profile of " + args[1] + " to " + args[2]);
		
		
		if (!profileService.getProfileMap().containsKey(args[0])){
			
			System.out.println(args[0] + " does not exist!");  
			return; 
		}
		if(!profileService.getProfileMap().containsKey(args[1])){
			
			System.out.println(args[1] + " does not exist");  
			return; 
		}
		if(!profileService.getProfileMap().containsKey(args[2])){
			
			System.out.println(args[2] + " does not exist");  
			return; 
		}
		if(args[1] == args[2]){return;}
		
		Profile tmp = null; 
		for(Profile prof : profileService.getProfileMap().get(args[0])){
			
			if(prof.getUserId() == args[1]){
				
				tmp = prof; 
				break;
			}
		}
		if(tmp != null){
			
			System.out.println("OK to share the profile.");
			Object result = joinPoint.proceed();
		}
		else{
			
			System.out.println("Does not have the priviledge to share!"); 
			throw new UnauthorizedException("Does not have the priviledge to share exception!");
		}
	}
	
	@Around("execution(* ProfileServiceImpl.unshareProfile(..))")
	public void aroundUnshare(ProceedingJoinPoint joinPoint) throws Throwable{

		ProfileServiceImpl profileService = (ProfileServiceImpl)joinPoint.getTarget();
		Object [] args = joinPoint.getArgs(); 
		System.out.println(args[0] + " unshares the profile to " + args[1]);
		
		
		if (!profileService.getProfileMap().containsKey(args[0])){
			
			System.out.println(args[0] + " does not exist!");  
			return; 
		}
		if(!profileService.getProfileMap().containsKey(args[1])){
			
			System.out.println(args[1] + " does not exist");  
			return; 
		}
	
		if(args[0] == args[1]){return; }
		
		for(Profile prof : profileService.getProfileMap().get(args[1])){
			
			if(prof.getUserId() == args[0]){
				
				System.out.println("OK to unshare.");
				Object result = joinPoint.proceed();
				break;
			}
		}
	}
}
