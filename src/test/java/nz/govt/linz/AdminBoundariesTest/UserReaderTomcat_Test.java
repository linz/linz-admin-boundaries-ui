package nz.govt.linz.AdminBoundariesTest;

import nz.govt.linz.AdminBoundaries.UserAdmin.UserReader;
import nz.govt.linz.AdminBoundaries.UserAdmin.UserReaderTomcat;
import nz.govt.linz.AdminBoundaries.UserAdmin.UserTomcat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class UserReaderTomcat_Test {
	
	private static int user_count;
	
	private static final String samplefile = "src/test/resources/tomcat-users.sample.xml";
	
	/** reader obj */
	private UserReader reader;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {	
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	public void setUp() throws Exception {
		reader = new UserReaderTomcat(samplefile);
		user_count = reader.getUserList().size();
	}

	@AfterEach
	public void tearDown() throws Exception {
		reader.save();
	}
		
	@Test
	public void test_10_checkUserList() {
		assertEquals("user1pass",((UserTomcat)reader.findInUserList("user1")).getPassword());
		assertEquals("user2pass",((UserTomcat)reader.findInUserList("user2")).getPassword());
	}
	
	@Test
	public void test_20_addUser() {
		String dummyuser = "dummyuser";
		String dummypass = "dummypass";
		String dummyrole = "AIMS";
		//System.out.println("DR1-"+reader);
		UserTomcat user = new UserTomcat(dummyuser,dummypass,dummyrole);
		reader.addUser(user);
		//System.out.println("DR2-"+reader);
		assertEquals(user_count+1,reader.getUserList().size());
		assertEquals(dummypass,((UserTomcat)reader.findInUserList(dummyuser)).getPassword());
		//assertEquals(reader.encrypt(dummypass),((UserTomcat)reader.findInUserList(dummyuser)).getPassword());
	}
	
	@Test
	public void test_30_deleteUser() {
		reader.delUser("dummyuser");
		assertEquals(user_count-1,reader.getUserList().size());
	}
	
	@Test
	public void test_40_transformer() {
		List<List<String>> table_data = reader.transformUserList(reader.getUserList());
		//System.out.println("DR40-"+table_data);
		assertEquals(user_count+1,table_data.size());
	}


}
