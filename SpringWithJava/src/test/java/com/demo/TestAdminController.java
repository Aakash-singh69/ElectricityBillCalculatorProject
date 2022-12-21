package com.demo;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.demo.controller.AdminController;
import com.demo.exceptions.AdminNotFoundException;
import com.demo.exceptions.BillNotFoundException;
import com.demo.exceptions.ConsumerNotFoundException;
import com.demo.pojo.Admin;
import com.demo.pojo.Bill;
import com.demo.pojo.Consumer;
import com.demo.repository.AdminRepository;
import com.demo.repository.BillRepository;
import com.demo.repository.ConsumerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(AdminController.class)
public class TestAdminController {
	
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BillRepository billRepository;
	@MockBean
	private ConsumerRepository consumerRepository;
	@MockBean
	private AdminRepository adminRepository;
	
	@Test
	public void shouldAddUnitsConsumed() throws Exception{
		Consumer c = new Consumer("test","test","test","domestic");
		Bill b = new Bill(c,10,2022,"jan",20);
		Admin a = new Admin("test","test");
		when(adminRepository.validateAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		when(consumerRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(c));
		when(billRepository.save(Mockito.any(Bill.class))).thenReturn(b);
		mockMvc.perform(post("/admin/addUnits").param("consumerId", "0").param("year", "2022").param("month", "jan").param("unitsConsumed", "10").param("userName", "test").param("password","test")).andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void addUnitsConsumed_shouldThrowAdminNotFoundException() throws Exception{
		Admin a = null;
		when(adminRepository.validateAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		mockMvc.perform(post("/admin/addUnits").param("consumerId", "0").param("year", "2022").param("month", "jan").param("unitsConsumed", "10").param("userName", "test").param("password","test")).andExpect(status().isNotAcceptable()).andExpect(result -> assertTrue(result.getResolvedException() instanceof AdminNotFoundException)).andExpect(result -> assertEquals("Invalid admin credentials", result.getResolvedException().getMessage())).andReturn();
	}
	
	
	
	@Test
	public void addUnitsConsumed_shouldThrowConsumerNotFoundException() throws Exception{
		Admin a = new Admin("test","test");
		Optional<Consumer> c = Optional.empty(); 
		when(adminRepository.validateAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		when(consumerRepository.findById(Mockito.anyInt())).thenReturn(c);
		mockMvc.perform(post("/admin/addUnits").param("consumerId", "0").param("year", "2022").param("month", "jan").param("unitsConsumed", "10").param("userName", "test").param("password","test")).andExpect(status().isNotFound()).andExpect(result -> assertTrue(result.getResolvedException() instanceof ConsumerNotFoundException)).andExpect(result -> assertEquals("Consumer not found", result.getResolvedException().getMessage())).andReturn();
	}

	
	
	@Test
	public void shouldReturnBillsByYear() throws Exception{
		Consumer c = new Consumer("test","test","test","domestic");
		Bill b = new Bill(c,10,2022,"jan",20);
		Admin a = new Admin("test","test");
		List<Bill> bills = new ArrayList<Bill>();
		bills.add(b);
		when(adminRepository.validateAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		when(billRepository.findAllByYear(Mockito.anyInt())).thenReturn(bills);
		MvcResult result = mockMvc.perform(get("/admin/getBillsByYear").param("year", "2022").param("userName", "test").param("password","test")).andExpect(status().isOk())
        .andReturn();
		String expectedJson = this.mapToJson(bills);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	@Test
	public void getBillsByYear_shouldThrowBillNotFoundException() throws Exception{
		List<Bill> bills = new ArrayList<Bill>();
		Admin a = new Admin("test","test");
		when(adminRepository.validateAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		when(billRepository.findAllByYear(Mockito.anyInt())).thenReturn(bills);
		mockMvc.perform(get("/admin/getBillsByYear").param("year", "2023").param("userName", "test").param("password","test")).andExpect(status().isNotFound()).andExpect(result -> assertTrue(result.getResolvedException() instanceof BillNotFoundException)).andExpect(result -> assertEquals("year = 2023", result.getResolvedException().getMessage())).andReturn();
	}
	
	@Test
	public void getBillsByYear_shouldThrowAdminNotFoundException() throws Exception{
		Admin a = null;
		when(adminRepository.validateAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		mockMvc.perform(get("/admin/getBillsByYear").param("year", "2023").param("userName", "test").param("password","test")).andExpect(status().isNotAcceptable()).andExpect(result -> assertTrue(result.getResolvedException() instanceof AdminNotFoundException)).andExpect(result -> assertEquals("Invalid admin credentials", result.getResolvedException().getMessage())).andReturn();
	}
	
	@Test
	public void shouldReturnBillsByMonth() throws Exception{
		Consumer c = new Consumer("test","test","test","domestic");
		Bill b = new Bill(c,10,2022,"jan",20);
		List<Bill> bills = new ArrayList<Bill>();
		bills.add(b);
		Admin a = new Admin("test","test");
		when(adminRepository.validateAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		when(billRepository.findAllByMonth(Mockito.anyString(),Mockito.anyInt())).thenReturn(bills);
		MvcResult result = mockMvc.perform(get("/admin/getBillsByMonth").param("year", "2022").param("month", "jan").param("userName", "test").param("password","test")).andExpect(status().isOk())
        .andReturn();
		String expectedJson = this.mapToJson(bills);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	@Test
	public void getBillsByMonth_shouldThrowBillNotFoundException() throws Exception{
		List<Bill> bills = new ArrayList<Bill>();
		Admin a = new Admin("test","test");
		when(adminRepository.validateAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		when(billRepository.findAllByMonth(Mockito.anyString(),Mockito.anyInt())).thenReturn(bills);
		mockMvc.perform(get("/admin/getBillsByMonth").param("year", "2023").param("month", "jan").param("userName", "test").param("password","test")).andExpect(status().isNotFound()).andExpect(result -> assertTrue(result.getResolvedException() instanceof BillNotFoundException)).andExpect(result -> assertEquals("Bill Not Found for jan", result.getResolvedException().getMessage())).andReturn();
	}
	
	@Test
	public void getBillsByMonth_shouldThrowAdminNotFoundException() throws Exception{
		Admin a = null;
		when(adminRepository.validateAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		mockMvc.perform(get("/admin/getBillsByMonth").param("year", "2023").param("month", "jan").param("userName", "test").param("password","test")).andExpect(status().isNotAcceptable()).andExpect(result -> assertTrue(result.getResolvedException() instanceof AdminNotFoundException)).andExpect(result -> assertEquals("Invalid admin credentials", result.getResolvedException().getMessage())).andReturn();
	}
	
	
	@Test
	public void shouldReturnBillsByCity() throws Exception{
		Consumer c = new Consumer("aakash","nowhere","Delhi","domestic");
		Bill b = new Bill(c,100,2022,"feb",123);
		List<Bill> bills = new ArrayList<Bill>();
		bills.add(b);
		Admin a = new Admin("test","test");
		when(adminRepository.validateAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		when(billRepository.findBillsByCity(Mockito.anyString())).thenReturn(bills);
		MvcResult result = mockMvc.perform(get("/admin/getBillsByCity").param("city", "Delhi").param("userName", "test").param("password","test")).andExpect(status().isOk())
        .andReturn();
		String expectedJson = this.mapToJson(bills);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	@Test
	public void getBillsByCity_shouldThrowAdminNotFoundException() throws Exception{
		Admin a = null;
		when(adminRepository.validateAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		mockMvc.perform(get("/admin/getBillsByCity").param("city", "Delhi").param("userName", "test").param("password","test")).andExpect(status().isNotAcceptable()).andExpect(result -> assertTrue(result.getResolvedException() instanceof AdminNotFoundException)).andExpect(result -> assertEquals("Invalid admin credentials", result.getResolvedException().getMessage())).andReturn();
	}
	
	@Test
	public void getBillsByCity_shouldThrowBillNotFoundException() throws Exception{
		List<Bill> bills = new ArrayList<Bill>();
		Admin a = new Admin("test","test");
		when(adminRepository.validateAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		when(billRepository.findBillsByCity(Mockito.anyString())).thenReturn(bills);
		mockMvc.perform(get("/admin/getBillsByCity").param("city", "Delhi").param("userName", "test").param("password","test")).andExpect(status().isNotFound()).andExpect(result -> assertTrue(result.getResolvedException() instanceof BillNotFoundException)).andExpect(result -> assertEquals("Bill Not Found for Delhi", result.getResolvedException().getMessage())).andReturn();
	}
	
	
	@Test
	public void shouldReturnBillsByArea() throws Exception{
		Consumer c = new Consumer("aakash","nowhere","Delhi","domestic");
		Bill b = new Bill(c,100,2022,"feb",123);
		List<Bill> bills = new ArrayList<Bill>();
		bills.add(b);
		Admin a = new Admin("test","test");
		when(adminRepository.validateAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		when(billRepository.findBillsByArea(Mockito.anyString())).thenReturn(bills);
		MvcResult result = mockMvc.perform(get("/admin/getBillsByArea").param("area", "nowhere").param("userName", "test").param("password","test")).andExpect(status().isOk())
        .andReturn();
		String expectedJson = this.mapToJson(bills);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	@Test
	public void getBillsByArea_shouldThrowAdminNotFoundException() throws Exception{
		Admin a = null;
		when(adminRepository.validateAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		mockMvc.perform(get("/admin/getBillsByArea").param("area", "nowhere").param("userName", "test").param("password","test")).andExpect(status().isNotAcceptable()).andExpect(result -> assertTrue(result.getResolvedException() instanceof AdminNotFoundException)).andExpect(result -> assertEquals("Invalid admin credentials", result.getResolvedException().getMessage())).andReturn();
	}
	
	
	@Test
	public void getBillsByArea_shouldThrowBillNotFoundException() throws Exception{
		List<Bill> bills = new ArrayList<Bill>();
		Admin a = new Admin("test","test");
		when(adminRepository.validateAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		when(billRepository.findBillsByArea(Mockito.anyString())).thenReturn(bills);
		mockMvc.perform(get("/admin/getBillsByArea").param("area", "nowhere").param("userName", "test").param("password","test")).andExpect(status().isNotFound()).andExpect(result -> assertTrue(result.getResolvedException() instanceof BillNotFoundException)).andExpect(result -> assertEquals("Bill Not Found for nowhere", result.getResolvedException().getMessage())).andReturn();
	}
	
	
	@Test
	public void shouldReturnAllBills() throws Exception{
		Consumer c = new Consumer("aakash","nowhere","Delhi","domestic");
		Bill b = new Bill(c,100,2022,"feb",123);
		List<Bill> bills = new ArrayList<Bill>();
		bills.add(b);
		Admin a = new Admin("test","test");
		when(adminRepository.validateAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		when(billRepository.findAllBill()).thenReturn(bills);
		MvcResult result = mockMvc.perform(get("/admin/getBills").param("userName", "test").param("password","test")).andExpect(status().isOk())
        .andReturn();
		String expectedJson = this.mapToJson(bills);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	
	@Test
	public void getAllBils_shouldThrowBillNotFoundException() throws Exception{
		List<Bill> bills = new ArrayList<Bill>();
		Admin a = new Admin("test","test");
		when(adminRepository.validateAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		when(billRepository.findAllBill()).thenReturn(bills);
		mockMvc.perform(get("/admin/getBills").param("userName", "test").param("password","test")).andExpect(status().isNotFound()).andExpect(result -> assertTrue(result.getResolvedException() instanceof BillNotFoundException)).andExpect(result -> assertEquals("Bill Not Found", result.getResolvedException().getMessage())).andReturn();
	}
	
	@Test
	public void getAllBills_shouldThrowAdminNotFoundException() throws Exception{
		Admin a = null;
		when(adminRepository.validateAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		mockMvc.perform(get("/admin/getBills").param("userName", "test").param("password","test")).andExpect(status().isNotAcceptable()).andExpect(result -> assertTrue(result.getResolvedException() instanceof AdminNotFoundException)).andExpect(result -> assertEquals("Invalid admin credentials", result.getResolvedException().getMessage())).andReturn();
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);	
		}

	
}