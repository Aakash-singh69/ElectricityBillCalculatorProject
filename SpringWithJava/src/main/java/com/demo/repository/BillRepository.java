package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.pojo.Bill;
import com.demo.pojo.Consumer;

//Generate bills for an year: /admin/getBillsByYear/{year} 
@Repository
public interface BillRepository extends CrudRepository<Bill, Integer>{
	
	@Query(value = "select * from Bill", nativeQuery = true)
	List<Bill> findAllBill();
	
	@Query(value = "select * from Bill where year=?", nativeQuery = true)
	List<Bill> findAllByYear(int year);
	
	@Query(value=" SELECT * FROM Bill WHERE month=:month && year=:year", nativeQuery = true)
	List<Bill> findAllByMonth(@Param("month") String month, @Param("year") int year);
	
	@Query(value = "Select * from Bill join Consumer on Bill.consumer_id = Consumer.consumer_id where Consumer.area=?", nativeQuery = true)
	List<Bill> findBillsByArea(String area);

	@Query(value = "select * from Bill join Consumer on Bill.consumer_id = Consumer.consumer_id where Consumer.city=?", nativeQuery = true)
	List<Bill> findBillsByCity(String city);
	
	@Query(value=" SELECT * FROM Bill WHERE month=:month and year=:year and consumer_id=:consumer_id", nativeQuery = true)
	List<Bill> getBillsByMonth(@Param("month") String month, @Param("year") int year, @Param("consumer_id") int consumer_id);
//	
	@Query(value = "select * from Bill where year=:year && consumer_id=:consumer_id", nativeQuery = true)
	List<Bill> getBillsByYear(@Param ("year") int year, @Param("consumer_id") int consumer_id);
//	
	@Query(value = "select * from Bill where consumer_id=:consumer_id ", nativeQuery = true)
	List<Bill> getAllBills(@Param("consumer_id") int consumer_id);
	
	

	
//	consumerid
//	consumer name
//	area
//	city
//	connectype
//	total amount
//	year
//	month
//	bill id
	
}
