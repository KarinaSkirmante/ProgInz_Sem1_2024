package lv.venta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import lv.venta.model.security.MyUser;
import lv.venta.repo.security.IMyUserRepo;

@Service
public class MyUserDetailsManager implements UserDetailsManager{

	@Autowired
	private IMyUserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MyUser userFormDB =  userRepo.findByUsername(username);
		
		if(userFormDB == null) {
			throw new UsernameNotFoundException(username + " is not found in the system");
		}
		else
		{
			MyUserDetails details = new MyUserDetails(userFormDB);
			return details;
		}
		
	}

	//TODO papildināt pēc nepieciešamības
	
	@Override
	public void createUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
