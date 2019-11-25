package happysolverserver.controller;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import happysolverserver.controller.resources.BinPackingItemRest;
import happysolverserver.controller.resources.BinPackingModelRest;
import happysolverserver.service.JsonSerializer;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BinPackingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void receive_model() throws Exception {

		BinPackingModelRest modelRest = buildModelRest();
		String jsonModelRest = JsonSerializer.toJson(modelRest, true);

		String currentUrl = BinPackingController.MAPPING + BinPackingController.MODELS + BinPackingController.RECEIVE;
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(currentUrl).content(jsonModelRest)
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		ResultActions perform = mockMvc.perform(asyncDispatch(mvcResult));

		// 1. created model successful in database
		String resultString = perform.andReturn().getResponse().getContentAsString();
		assertThat(resultString).contains("MyFirstModel with id: 1");

		// 2. get all models
		currentUrl = BinPackingController.MAPPING + BinPackingController.MODELS;
		requestBuilder = MockMvcRequestBuilders.get(currentUrl);
		mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		perform = mockMvc.perform(asyncDispatch(mvcResult));

		String modelList = perform.andReturn().getResponse().getContentAsString();

		assertThat(modelList).contains("\"id\":1,\"name\":\"MyFirstModel\"");
	}

	private BinPackingModelRest buildModelRest() {

		BinPackingItemRest itemRest = new BinPackingItemRest();
		itemRest.setAmount(3);
		itemRest.setName("1");

		BinPackingItemRest itemRest2 = new BinPackingItemRest();
		itemRest2.setAmount(3);
		itemRest2.setName("2");

		List<BinPackingItemRest> items = asList(itemRest, itemRest2);

		BinPackingModelRest modelRest = new BinPackingModelRest();
		modelRest.setName("MyFirstModel");
		modelRest.setCapacity(10);
		modelRest.setItems(items);

		return modelRest;
	}
}
