package com.wf.ivankov.stock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.wf.ivankov.stock.app.AppStockConfig;
import com.wf.ivankov.stock.dao.ProductStockDao;

/**
 * @author Ivankov_A
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { AppStockConfig.class })
@AutoConfigureMockMvc
public class StockRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductStockDao productStockDao;

	@Before
	public void deleteAllBeforeTests() throws Exception {
		productStockDao.deleteAll();
	}

	@Test
	public void checkStock() throws Exception {
		shouldCreateProductStockEntity();
		
		mockMvc.perform(get("/stock/1")).andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.productId").exists())
			.andExpect(jsonPath("$.quantity").exists());
	}
	
	@Test
	public void shouldCreateProductStockEntity() throws Exception {
		mockMvc.perform(post("/stock").content("{\"productId\": 1, \"quantity\":999}").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.productId").exists());
	}
	
	@Test
	public void shouldCreateProductReservationEntity() throws Exception {
		shouldCreateProductStockEntity();
		
		mockMvc.perform(post("/stock/reserve").content("{\"productId\": 1, \"quantity\":999}").contentType(MediaType.APPLICATION_JSON)).andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.productId").exists())
			.andExpect(jsonPath("$.quantity").exists())
			.andExpect(jsonPath("$.userName").exists())
			.andExpect(jsonPath("$.reservationDate").exists());
	}
}
