package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exceptions.BillNotFoundException;
import com.demo.pojo.Bill;
import com.demo.pojo.Consumer;
import com.demo.repository.BillRepository;
import com.demo.repository.ConsumerRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class ConsumerController {

	@Autowired
	BillRepository billRepository;
	@Autowired
	ConsumerRepository consumerRepository;
	
	@GetMapping("/consumer/getBillsByMonth")
	public List<Bill> getBillsByMonth(@RequestParam("month") String month, @RequestParam("year") int year,@RequestParam("consumerId") int consumerId) throws BillNotFoundException {
		List<Bill> bills = billRepository.getBillsByMonth(month, year, consumerId);
		if(bills.isEmpty()) {
			throw new BillNotFoundException("Bill Not Found!! for these values");
		}else {
			return bills;
		}
	}
	
	@GetMapping("/consumer/getBillsByYear")
	public List<Bill> getBillsByYear(@RequestParam("year") int year,@RequestParam("consumerId") int consumerId) throws BillNotFoundException {
		List<Bill> bills = billRepository.getBillsByYear(year, consumerId);
		if(bills.isEmpty()) {
			throw new BillNotFoundException("Bill Not Found!! for these values");
		}else {
			return bills;
		}
	}
	
	@GetMapping("/consumer/getAllBills")
	public List<Bill> getAllBills(@RequestParam int consumerId) throws BillNotFoundException {
		List<Bill> bills = billRepository.getAllBills(consumerId);
		if(bills.isEmpty()) {
			throw new BillNotFoundException("Bill Not Found!! for these values");
		}else {
			return bills;
		}
	}
	
	@PostMapping("/consumer/register")
	public Consumer addConsumer(@RequestBody Consumer c)  {
			Consumer consumer = consumerRepository.save(c);
			return consumer;
	}
	
	
	
}