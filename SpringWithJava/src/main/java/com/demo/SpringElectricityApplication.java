package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.pojo.Admin;
import com.demo.pojo.Bill;
import com.demo.pojo.Consumer;
import com.demo.repository.AdminRepository;
import com.demo.repository.BillRepository;
import com.demo.repository.ConsumerRepository;

@SpringBootApplication
public class SpringElectricityApplication implements CommandLineRunner {
	@Autowired
	BillRepository billRepository;
	
	@Autowired
	ConsumerRepository consumerRepository;
	
	@Autowired
	AdminRepository adminRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringElectricityApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Consumer c1 = new Consumer("Glen Quagmire", "anna nagar", "chennai","domestic");
		consumerRepository.save(c1);
		Consumer c2 = new Consumer("Mahatma Gandhi", "miyapur", "hyd","domestic");
		consumerRepository.save(c2);
		Consumer c3 = new Consumer("John Cena", "rk colony", "hyd","commercial");
		consumerRepository.save(c3);
		Consumer c4 = new Consumer("Katrina Kaif", "naaripuri", "mumbai","domestic");
		consumerRepository.save(c4);
		Consumer c5 = new Consumer("bahubali", "maahishmathi", "bangalore","commercial");
		consumerRepository.save(c5);
		billRepository.save(new Bill(c1,123,2022,"January",246));
		billRepository.save(new Bill(c2,355,2022,"March",710));
		billRepository.save(new Bill(c3,734,2022,"June",2936));
		billRepository.save(new Bill(c4,454,2021,"Febraury",908));
		billRepository.save(new Bill(c5,555,2021,"May",2220));

		adminRepository.save(new Admin("admin","admin"));
		for(Bill emp:billRepository.getBillsByMonth("jan", 2023, c1.getConsumerId()) ) {
			System.out.println(emp);
		}
		
	}

}
