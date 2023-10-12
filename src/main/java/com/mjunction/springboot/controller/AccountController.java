package com.mjunction.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mjunction.springboot.exception.ResourceNotFound;
import com.mjunction.springboot.model.Accounts;
import com.mjunction.springboot.repo.AccountRepo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class AccountController {

    @Autowired
    private AccountRepo accountRepo;

    // get all employees
    @GetMapping("/accounts")
    public List<Accounts> getAllAccount() {
        return accountRepo.findAll();
    }

  
     //create employees
     
     @PostMapping("/accounts")
     public Accounts CreateAccount(@RequestBody Accounts a){
     return accountRepo.save(a);
     }
     //get account by id
      
     @GetMapping("/accounts/{id}")
      public ResponseEntity<Accounts> getAccountById(@PathVariable Long id) {
      Accounts acc=accountRepo.findById(id)
      .orElseThrow(()->new ResourceNotFound("Account Not Found with Id : "+id));
      return ResponseEntity.ok(acc);
      }
      
      @GetMapping("/accounts/ac/{acno}")
      public ResponseEntity<Accounts> getAccountByAcNo(@PathVariable String acno) {
      Accounts acc=accountRepo.findByAnum(acno);
      return ResponseEntity.ok(acc);
      }
      
      //update account
      
      @PutMapping("/accounts/{id}")
      public ResponseEntity<Accounts> updateAccount(@PathVariable Long
      id,@RequestBody Accounts account){
      Accounts acc=accountRepo.findById(id)
      .orElseThrow(()->new ResourceNotFound("Account Not Found with Id : "+id));
      
      acc.setName(account.getName());
      acc.setAcno(account.getAcno());
      acc.setPhone(account.getPhone());
      acc.setEmail(account.getEmail());
      acc.setBalance(account.getBalance());
      acc.setPassword(account.getPassword());
      Accounts updateAccount=accountRepo.save(acc);
      return ResponseEntity.ok(updateAccount);
      
      }
      
      //delete account
      
      @DeleteMapping("/accounts/{id}")
      public ResponseEntity<Map<String,Boolean>> deleteAccount(@PathVariable Long
      id){
      Accounts acc=accountRepo.findById(id)
      .orElseThrow(()->new ResourceNotFound("Account Not Found with Id : "+id));
      accountRepo.delete(acc);
      Map<String,Boolean> response=new HashMap<>();
      response.put("deleted", Boolean.TRUE);
      return ResponseEntity.ok(response);
      }
      
      @GetMapping("/accounts/{id}/{password}")
      public ResponseEntity<?> userLogin(@PathVariable Long id,@PathVariable String
      password) {
      Accounts acc=accountRepo.findById(id).orElseThrow(()->new
      ResourceNotFound("Account Not Found with Id : "+id));
      if(acc.getPassword().equals(password)) {
      return ResponseEntity.ok(acc);
      }
      return (ResponseEntity<?>) ResponseEntity.internalServerError();
      
      }
      
      @PutMapping(path = "/accounts/{acc1}/{acc2}/{amt}")
      
      @Transactional
      public void transaction(@PathVariable("acc1") String
      accnum1,@PathVariable("acc2") String accnum2,@PathVariable("amt")double amt)
      {
      Accounts detail1= accountRepo.findByAnum(accnum1);
      Accounts detail2= accountRepo.findByAnum(accnum2);
      double Acc1Bal=detail1.getBalance();
      double Acc2Bal=detail2.getBalance();
      detail1.setBalance(Acc1Bal-amt);
      detail2.setBalance(Acc2Bal+amt);
      accountRepo.save(detail1);
      accountRepo.save(detail2);
      }
      
      @Transactional//delete or update
      @PutMapping("/updatePhone/{phone}/{id}")
      public String updatePhone(@PathVariable String phone,@PathVariable int id) {
          accountRepo.updatePhone(phone,id);
          return ("Phone Number Changed");
      }
}
