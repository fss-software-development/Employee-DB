package com.fss.empdb;

/*
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
*/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fss.empdb.controller.EmployeeController;
import com.fss.empdb.domain.Employee;
import com.fss.empdb.service.EmployeeService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeDbApplicationTests {

	private MockMvc mockMvc;


	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private WebApplicationContext context;

	ObjectMapper om = new ObjectMapper();

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

	}


	@Test
	public void getAllEmployeeTest() throws Exception {

		MvcResult result=mockMvc.perform(get("/services/get-all-employee").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		/*String resultContent = result.getResponse().getContentAsString();
		System.out.println("response _____________1"+result.getResponse());
		ResponseEntity response=om.readValue(resultContent, ResponseEntity.class);*/
		//System.out.println("response _____________"+response);
		Assert.assertTrue(result.getResponse().getStatus()==(200));
		//result.getResponse().getStatus()
				/*.andExpect(jsonPath(responseCode).value(HttpStatus.OK.value()))
				.andExpect(jsonPath(responseMessage).value(CommonConstants.AVAILABLE_DATE_SUCCESS))
				.andExpect(jsonPath(error).value(false));*/

	/*	when(employeeController.getAllEmployee()).thenReturn(Stream.of(new Employee(1,10001,"kirti",101,1,1,11,1,1,1,"development","vinod",1,2,12,"12","dev","java",null,"1","java","java","Y",null,null,"dev")).collect(Collectors.toList()));
		assertEquals(2,employeeService.getAllEmployees().size());*/
/*Employee emp=new Employee();
		emp.setEmployeeSqId((long) 1);
		emp.setEmployeeId((long) 10062);

		Employee emp2=new Employee();
		emp.setEmployeeSqId((long) 2);
		emp.setEmployeeId((long) 10063);

		Employee emp3=new Employee();
		emp.setEmployeeSqId((long) 3);
		emp.setEmployeeId((long) 10064);

		Employee emp4=new Employee();
		emp.setEmployeeSqId((long) 4);
		emp.setEmployeeId((long) 16005);

		when(employeeController.getAllEmployee()).thenReturn((ResponseEntity<List<Employee>>) Stream.of(emp,emp2,emp3,emp4).collect(Collectors.toList()));
		assertEquals(4,employeeService.getAllEmployees().size());*/
	}

	@Test
	public void getEmployeeBySearchCriteria1() throws Exception {
      Employee emp =new Employee();
      emp.setEmployeeSqId(Long.valueOf(1));
      emp.setEmployeeId(Long.valueOf(10062));
String jsonRequest = om.writeValueAsString(emp);

		JSONObject obj=new JSONObject();
		obj.put("employeeSqId",Long.valueOf(1));
		obj.put("employeeId",Long.valueOf(10062));
		String jsonRequestt = om.writeValueAsString(obj);
		System.out.print(obj);

		System.out.println("testing ----"+String.valueOf(obj));
		MvcResult searchResult = mockMvc.perform(post("/services/emp-search-criteria").content(String.valueOf(obj)).content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		System.out.println("testing ----"+searchResult.getResponse());
		Assert.assertTrue(searchResult.getResponse().getStatus() == (200));
	}

	/*@Test
	void contextLoads() {
	}*/

}
