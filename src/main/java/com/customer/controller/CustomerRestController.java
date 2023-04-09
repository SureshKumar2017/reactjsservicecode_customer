/**
 * 
 */
package com.customer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.models.CustomerVO;
import com.customer.repo.CustomerRepo;
import com.customer.repo.Customer;

/**
 * @author dsure
 *
 */
@CrossOrigin(value = "*")
@RestController
@RequestMapping("/customer")
public class CustomerRestController {
	
	@Autowired
	public CustomerRepo repo;

	@GetMapping("/")
	public ResponseEntity<List<CustomerVO>> getWebsites(){
		System.out.println("==??????????????????????????");
		List<CustomerVO> emp =new ArrayList<>();
		CustomerVO websiteVO = null;
		List<Customer> websiteL = repo.findAll();
		for(Customer website:websiteL) {
			websiteVO = new CustomerVO();
			websiteVO.setCustid(website.getCustid());
			websiteVO.setCustname(website.getCustname());
			websiteVO.setOccupation(website.getOccupation());
			emp.add(websiteVO);
		}
		
		System.out.println(" Employee List Call");
		return new ResponseEntity<List<CustomerVO>>(emp, HttpStatus.OK);
	}
	

	@GetMapping("/delete/{id}")
	public ResponseEntity<CustomerVO> deleteWebsite(@PathVariable Integer id) throws Exception {
		if(id!=null) {
			repo.deleteById(Long.parseLong(id.toString()));
		}
		System.out.println("==???????????delete??????????????");
		return new ResponseEntity<CustomerVO>(HttpStatus.OK);
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<CustomerVO> getWebsite(@PathVariable Integer id) throws Exception {
		
		Optional<Customer> web  = repo.findById((Long.parseLong(id.toString())));
		Customer website = web.get();
		
		CustomerVO employee2 = new CustomerVO();
		employee2.setCustid(website.getCustid());
		employee2.setCustname(website.getCustname());
		employee2.setOccupation(website.getOccupation());
		System.out.println("Get by title ===========>"+website.getCustname());
		return new ResponseEntity<CustomerVO>(employee2, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Void> saveWebsite(@RequestBody CustomerVO website) throws Exception {
		//websiteService.saveWebsite(website);
		System.out.println("==???????????ADDE??????????????");
		Customer website2 = new Customer();
	//	website2.setId(website.getId());
		website2.setCustname(website.getCustname());
		website2.setOccupation(website.getOccupation());
		Customer w = repo.save(website2);
		System.out.println("Added ===========>"+w.getCustid());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@PostMapping("/update")
	public ResponseEntity<Void> updateWebsite(@RequestBody CustomerVO website) throws Exception {
	//	websiteService.updateWebsite(website);
		
		Optional<Customer> web  = repo.findById((Long.parseLong(website.getCustid().toString())));
		Customer website2 = web.get();
		
		website2.setCustname(website.getCustname());
		website2.setOccupation(website.getOccupation());
		repo.save(website2);
		System.out.println(" Update Sucessfully");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
