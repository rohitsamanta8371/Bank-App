package com.mjunction.springboot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mjunction.springboot.model.Accounts;
@Repository
public interface AccountRepo extends JpaRepository<Accounts, Long>{
	@Query("from Accounts")//entity or model
	List<Accounts> findAll();
	@Query("from Accounts where acno = :accountNumber")
	Accounts findByAnum(String accountNumber);
	
	@Modifying
    @Query("update Accounts set phone= :phone where id= :id")
    void updatePhone(@Param("phone") String phone,@Param("id") long id);
}
