package nz.govt.linz.AdminBoundariesTest;

/**
 * UserAdmin DABFormatterUser Test.
 *
 * Copyright 2014 Crown copyright (c)
 * Land Information New Zealand and the New Zealand Government.
 * All rights reserved
 *
 * This program is released under the terms of the new BSD license. See the
 * LICENSE file for more information.
 */

import nz.govt.linz.AdminBoundaries.DABFormatterUser;
import nz.govt.linz.AdminBoundaries.DABFormatterUser.TPA;
import nz.govt.linz.AdminBoundaries.UserAdmin.User;
import nz.govt.linz.AdminBoundaries.UserAdmin.UserAIMS;
import nz.govt.linz.AdminBoundaries.UserAdmin.UserPostgreSQL;
import nz.govt.linz.AdminBoundaries.UserAdmin.UserTomcat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class DABFormatterUser_Test {

	
	private final static int tc_form_len = 1579;//1328;//1295;
	private final static int pg_form_len = 881;
	private final static int aa_form_len = 1453;
	
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	public void tearDown() throws Exception {
	}
	
	
	/**
	 * Tests the string length of the generated TC test form
	 */
	@Test
	public void test_10_formTC() {
		List<String> u1 = Arrays.asList("tuser1","pass1","AIMS,manager_gui");
		List<String> u2 = Arrays.asList("tuser2","pass2","AIMS");
		List<User> tc_u = new ArrayList<>();
		tc_u.add(new UserTomcat(u1.get(0),u1.get(1),u1.get(2)));
		tc_u.add(new UserTomcat(u2.get(0),u2.get(1),u2.get(2)));
		String form = DABFormatterUser.formatUserForm(TPA.Tomcat,tc_u);
		assertEquals(tc_form_len,form.length());
		assertTrue(form.contains(u1.get(0)+"\">"+u1.get(0)));
		assertTrue(form.contains(u2.get(0)+"\">"+u2.get(0)));
	}
	/**
	 * Tests the string length of the generated PG test form
	 */
	@Test
	public void test_20_formPG() {
		List<String> u1 = Arrays.asList("puser1","pass1","aims_admin,aims_user");
		List<String> u2 = Arrays.asList("puser2","pass2","aims_user");
		List<User> pg_u = new ArrayList<>();
		pg_u.add(new UserPostgreSQL(u1.get(0),u1.get(1),u1.get(2)));
		pg_u.add(new UserPostgreSQL(u2.get(0),u2.get(1),u2.get(2)));
		String form = DABFormatterUser.formatUserForm(TPA.PostgreSQL,pg_u);
		assertEquals(pg_form_len,form.length());
		assertTrue(form.contains(u1.get(0)+"\">"+u1.get(0)));
		assertTrue(form.contains(u2.get(0)+"\">"+u2.get(0)));
	}
	
	/**
	 * Tests the string length of the generated AA test form
	 */
	@Test
	public void test_30_formAA() {
		List<String> u1 = Arrays.asList("auser1","aemail1","LINZ","Reviewer","false");
		List<String> u2 = Arrays.asList("auser2","aemail2","e-Spatial","Follower","false");
		List<User> aa_u = new ArrayList<>();
		aa_u.add(new UserAIMS(u1.get(0),u1.get(1),u1.get(2),u1.get(3),u1.get(4)));
		aa_u.add(new UserAIMS(u2.get(0),u2.get(1),u2.get(2),u2.get(3),u2.get(4)));
		String form = DABFormatterUser.formatUserForm(TPA.AIMS,aa_u);
		assertEquals(aa_form_len,form.length());
		assertTrue(form.contains(u1.get(0)+"\">"+u1.get(0)));
		assertTrue(form.contains(u2.get(0)+"\">"+u2.get(0)));
	}
	
}
