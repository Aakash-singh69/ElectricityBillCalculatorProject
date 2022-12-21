package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exceptions.AdminNotFoundException;
import com.demo.exceptions.BillNotFoundException;
import com.demo.exceptions.ConsumerNotFoundException;
import com.demo.pojo.Admin;
import com.demo.pojo.Bill;
import com.demo.pojo.Consumer;
import com.demo.repository.AdminRepository;
import com.demo.repository.BillRepository;
import com.demo.repository.ConsumerRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class AdminController {
	
	@Autowired
	BillRepository billRepository;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	ConsumerRepository consumerRepository;
	
	@PostMapping("/admin/addUnits")
	public Bill addUnitsConsumed(@RequestParam("userName") String userName, @RequestParam("password") String password, @RequestParam("consumerId") int consumerId, @RequestParam("year") int year, @RequestParam("month") String month, @RequestParam("unitsConsumed") int unitsConsumed) throws ConsumerNotFoundException, AdminNotFoundException{
		Admin admin = adminRepository.validateAdmin(userName, password);
		if(admin==null) 
			throw new AdminNotFoundException("Invalid admin credentials");
		Consumer c  = consumerRepository.findById(consumerId).orElse(null);
		if(c==null)
			throw new ConsumerNotFoundException("Consumer not found");
		Bill bill = new Bill();
		bill.setConsumerId(c);
		bill.setMonth(month);
		bill.setYear(year);
		bill.setUnitsConsumed(unitsConsumed);
		if(c.getConnectionType().equals("domestic")){
			bill.setTotalAmount(bill.getUnitsConsumed()*2);
		}else
			bill.setTotalAmount(bill.getUnitsConsumed()*4);
	
		bill = billRepository.save(bill);
		System.out.println(bill);
		return bill;
	}
	
	@GetMapping("/admin/getBillsByYear")
	public List<Bill> getBillsByYear(@RequestParam("userName") String userName, @RequestParam("password") String password, @RequestParam("year") int year) throws BillNotFoundException, AdminNotFoundException{
		Admin admin = adminRepository.validateAdmin(userName, password);
		if(admin==null) 
			throw new AdminNotFoundException("Invalid admin credentials");
		List<Bill> bills = billRepository.findAllByYear(year);
		if(bills.isEmpty()) {
			throw new BillNotFoundException("year = "+ year);
		}else {
			return bills;
		}
	}
	
	@GetMapping("/admin/getBillsByMonth")
	public List<Bill> getBillsByYear(@RequestParam("userName") String userName, @RequestParam("password") String password, @RequestParam("month") String month, @RequestParam("year") int year ) throws BillNotFoundException, AdminNotFoundException {
		Admin admin = adminRepository.validateAdmin(userName, password);
		if(admin==null) 
			throw new AdminNotFoundException("Invalid admin credentials");
		List<Bill> bills = billRepository.findAllByMonth(month, year);
		if(bills.isEmpty()) {
			throw new BillNotFoundException("Bill Not Found for "+month);
		}else {
			return bills;
		}
		
	}
	
	@GetMapping("/admin/getBillsByArea")
	public List<Bill> getBillsByArea(@RequestParam("userName") String userName, @RequestParam("password") String password, @RequestParam("area") String area) throws BillNotFoundException, AdminNotFoundException {
		Admin admin = adminRepository.validateAdmin(userName, password);
		if(admin==null) 
			throw new AdminNotFoundException("Invalid admin credentials");
		List<Bill> bills = billRepository.findBillsByArea(area);
		if(bills.isEmpty()) {
			throw new BillNotFoundException("Bill Not Found for "+area);
		}else {
			return bills;
		}
		
	}
	
	@GetMapping("/admin/getBillsByCity")
	public List<Bill> getBillsByCity(@RequestParam("userName") String userName, @RequestParam("password") String password, @RequestParam("city") String city) throws BillNotFoundException, AdminNotFoundException {
		Admin admin = adminRepository.validateAdmin(userName, password);
		if(admin==null) 
			throw new AdminNotFoundException("Invalid admin credentials");
		List<Bill> bills = billRepository.findBillsByCity(city);
		if(bills.isEmpty()) {
			throw new BillNotFoundException("Bill Not Found for "+city);
		}else {
			return bills;
		}
		
	}
	
	
	@GetMapping("/admin/getBills")
	public List<Bill> getBills(@RequestParam("userName") String userName, @RequestParam("password") String password) throws BillNotFoundException, AdminNotFoundException {
		Admin admin = adminRepository.validateAdmin(userName, password);
		if(admin==null) 
			throw new AdminNotFoundException("Invalid admin credentials");
		if((billRepository.findAllBill()).isEmpty()) {
			throw new BillNotFoundException("Bill Not Found");
		}else {
			return billRepository.findAllBill();
		}
	}

}