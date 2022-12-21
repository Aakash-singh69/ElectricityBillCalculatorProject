package com.demo;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.pojo.Bill;
import com.demo.pojo.Consumer;
import com.demo.repository.BillRepository;
import com.demo.repository.ConsumerRepository;

@SpringBootTest
//@DataJpaTest
class SpringElectricityApplicationTests {
	
	@Autowired
	BillRepository billRepository;
	
	@Autowired
	ConsumerRepository consumerRepository;
	
	Consumer c = new Consumer("test","test","test","domestic");
	Bill b = new Bill(c,10,2022,"jan",20);

//	@Test
//	void contextLoads() {
//	}
	
	@BeforeEach
	void insertTestData() { 
		c = consumerRepository.save(c);
		b = billRepository.save(b);
	}
	
	@Test
	public void testfindAllBill() {
		List<Bill> bills = billRepository.findAllBill();
        Assertions.assertThat(bills.contains(b)).isEqualTo(true);
	}
	
	@Test
	public void testfindAllByYear() {
		List<Bill> bills = billRepository.findAllByYear(2022);
        Assertions.assertThat(bills.contains(b)).isEqualTo(true);
	}
	
	@Test
	public void testfindAllByMonth() {
		List<Bill> bills = billRepository.findAllByMonth("jan",2022);
        Assertions.assertThat(bills.contains(b)).isEqualTo(true);
	}
	
	@Test
	public void testfindBillsByArea() {
		List<Bill> bills = billRepository.findBillsByArea("test");
		Assertions.assertThat(bills.size()).isEqualTo(1);
        Assertions.assertThat(bills.contains(b)).isEqualTo(true);
	}
	
	@Test
	public void testfindBillsByCity() {
		List<Bill> bills = billRepository.findBillsByCity("test");
		Assertions.assertThat(bills.size()).isEqualTo(1);
        Assertions.assertThat(bills.contains(b)).isEqualTo(true);
	}
	
	@Test
	public void testGetBillsByMonth() {
		List<Bill> bills = billRepository.getBillsByMonth("jan",2022,c.getConsumerId());
		Assertions.assertThat(bills.size()).isEqualTo(1);
        Assertions.assertThat(bills.contains(b)).isEqualTo(true);
	}
	
	@Test
	public void testGetBillsByYear() {
		List<Bill> bills = billRepository.getBillsByYear(2022,c.getConsumerId());
		Assertions.assertThat(bills.size()).isEqualTo(1);
        Assertions.assertThat(bills.contains(b)).isEqualTo(true);
	}
	
	@Test
	public void testGetAllBills() {
		List<Bill> bills = billRepository.getAllBills(c.getConsumerId());
        Assertions.assertThat(bills.contains(b)).isEqualTo(true);
        Assertions.assertThat(bills.size()).isEqualTo(1);
	}
	
	@AfterEach
	void tearDown() {
		billRepository.delete(b);
		consumerRepository.delete(c);
	}

}
