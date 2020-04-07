package com.fss.empdb;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import com.fss.empdb.controller.EmployeeController;
import com.fss.empdb.domain.Employee;
import com.fss.empdb.service.EmployeeService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeDbApplicationTests {

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeController employeeController;

	@Test
	public void getAllEmployeeTest(){
		when(employeeController.getAllEmployee()).thenReturn(Stream.of(new Employee(1,10001,"kirti",101,1,1,11,1,1,1,"development","vinod",1,2,12,"12","dev","java",null,"1","java","java","Y",null,null,"dev")).collect(Collectors.toList()));
		assertEquals(2,employeeService.getAllEmployees().size());
	}



	/*@Test
	void contextLoads() {
	}*/

}
