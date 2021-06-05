package com.rg.DAO;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rg.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {

	
	@Query(value="select * from admin where username=? and password=?" , nativeQuery=true)
	public Admin LoginAdmin(String username , String password);

	
	@Modifying
	@Query(value="update admin set lastlogin=? where id=?" ,nativeQuery=true)
	public void updateLastLogin(String time,int id);
	
	
	
	
	@Query(value="select invalidlogin from admin where username=?", nativeQuery=true)
	public String getLoginCount(String userId);
	
	@Modifying
	@Query(value="update admin set invalidlogin=(invalidlogin+1) where username=?", nativeQuery=true)
	public void incrementLoginCount(String id);
	
	@Modifying
	@Query(value="update admin set invalidlogin=0 where id=?", nativeQuery=true)
	public void resetLoginCount(int id);
	
	
}
