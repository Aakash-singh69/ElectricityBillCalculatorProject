package com.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.demo.controller.ConsumerController;
import com.demo.exceptions.BillNotFoundException;
import com.demo.pojo.Bill;
import com.demo.pojo.Consumer;
import com.demo.repository.AdminRepository;
import com.demo.repository.BillRepository;
import com.demo.repository.ConsumerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(ConsumerController.class)
public class TestConsumerController {
	
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BillRepository billRepository;
	@MockBean
	private ConsumerRepository consumerRepository;
	@MockBean
	private AdminRepository adminRepository;
	
	
//	@Test
//	public void shouldRegisterConsumer() throws Exception{
//		Consumer c = new Consumer("test","test","test","domestic");
//		when(consumerRepository.save(Mockito.any(Consumer.class))).thenReturn(c);
//		MvcResult result = mockMvc.perform(post("/consumer/register").accept(MediaType.APPLICATION_JSON).content(this.mapToJson(c))
//				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//        .andReturn();
//		assertThat(result.getResponse().getContentAsString()).isEqualTo(String.valueOf(c.getConsumerId()));
//	}
	
	@Test
	public void shouldReturnConsumerBillsByMonth() throws Exception{
		Consumer c = new Consumer("test","test","test","domestic");
		Bill b = new Bill(c,10,2022,"jan",20);
		List<Bill> bills = new ArrayList<Bill>();
		bills.add(b);
		when(billRepository.getBillsByMonth(Mockito.anyString(),Mockito.anyInt(),Mockito.anyInt())).thenReturn(bills);
		MvcResult result = mockMvc.perform(get("/consumer/getBillsByMonth").param("month", "jan").param("year", "2022").
				param("consumerId", "0")).andExpect(status().isOk()).andReturn();
		String expectedJson = this.mapToJson(bills);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	@Test
	public void getBillsByMonth_shouldThrowBillNotFoundException() throws Exception{
		List<Bill> bills = new ArrayList<Bill>();
		when(billRepository.getBillsByMonth(Mockito.anyString(),Mockito.anyInt(),Mockito.anyInt())).thenReturn(bills);
		mockMvc.perform(get("/consumer/getBillsByMonth").param("month", "jan").param("year", "2022").param("consumerId", "0")).andExpect(status().isNotFound()).andExpect(result -> assertTrue(result.getResolvedException() instanceof BillNotFoundException)).andExpect(result -> assertEquals("Bill Not Found!! for these values", result.getResolvedException().getMessage())).andReturn();
	}
	
	
	@Test
	public void shouldReturnConsumerBillsByYear() throws Exception{
		Consumer c = new Consumer("test","test","test","domestic");
		Bill b = new Bill(c,10,2022,"jan",20);
		List<Bill> bills = new ArrayList<Bill>();
		bills.add(b);
		when(billRepository.getBillsByYear(Mockito.anyInt(),Mockito.anyInt())).thenReturn(bills);
		MvcResult result = mockMvc.perform(get("/consumer/getBillsByYear").param("year", "2022").param("consumerId", "0")).andExpect(status().isOk()).andReturn();
		String expectedJson = this.mapToJson(bills);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	@Test
	public void getBillsByYear_shouldThrowBillNotFoundException() throws Exception{
		List<Bill> bills = new ArrayList<Bill>();
		when(billRepository.getBillsByYear(Mockito.anyInt(),Mockito.anyInt())).thenReturn(bills);
		mockMvc.perform(get("/consumer/getBillsByYear").param("month", "jan").param("year", "2022").param("consumerId", "0")).andExpect(status().isNotFound()).andExpect(result -> assertTrue(result.getResolvedException() instanceof BillNotFoundException)).andExpect(result -> assertEquals("Bill Not Found!! for these values", result.getResolvedException().getMessage())).andReturn();
	}
	
	
	@Test
	public void shouldReturnConsumerAllBills() throws Exception{
		Consumer c = new Consumer("test","test","test","domestic");
		Bill b = new Bill(c,10,2022,"jan",20);
		List<Bill> bills = new ArrayList<Bill>();
		bills.add(b);
		when(billRepository.getAllBills(Mockito.anyInt())).thenReturn(bills);
		MvcResult result = mockMvc.perform(get("/consumer/getAllBills").param("consumerId", "1")).andExpect(status().isOk()).andReturn();
		String expectedJson = this.mapToJson(bills);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	@Test
	public void getAllBills_shouldThrowBillNotFoundException() throws Exception{
		List<Bill> bills = new ArrayList<Bill>();
		when(billRepository.getAllBills(Mockito.anyInt())).thenReturn(bills);
		mockMvc.perform(get("/consumer/getAllBills").param("consumerId", "0")).andExpect(status().isNotFound()).andExpect(result -> assertTrue(result.getResolvedException() instanceof BillNotFoundException)).andExpect(result -> assertEquals("Bill Not Found!! for these values", result.getResolvedException().getMessage())).andReturn();
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	
	
}