package nz.govt.linz.AdminBoundariesTest;

/**
 * AdminBoundaries Test
 *
 * Copyright 2014 Crown copyright (c)
 * Land Information New Zealand and the New Zealand Government.
 * All rights reserved
 *
 * This program is released under the terms of the new BSD license. See the
 * LICENSE file for more information.
 */

import nz.govt.linz.AdminBoundaries.ProcessControl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import java.io.File;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class ProcessControl_Test {

	private ProcessControl controller;	
	
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
	 * Tests the string length of the generated test table
	 */
	@Test
	public void test_10_shstub() {	
		File testfile = new File("test/linz_admin_boundaries_uploader_stub.sh");
		controller  = new ProcessControl(testfile);	
		assertEquals("LOAD",controller.readProcessOutput(ProcessControl.getProcessBuilder(new String[] {"load"}),""));
		assertEquals("MAP",controller.readProcessOutput(ProcessControl.getProcessBuilder(new String[] {"map"}),""));
		assertEquals("TRANSFER",controller.readProcessOutput(ProcessControl.getProcessBuilder(new String[] {"transfer"}),""));
	}	
	
	@Test
	public void test_20_pystub() {	
		File testfile = new File("test/linz_admin_boundaries_uploader_stub.py");
		controller  = new ProcessControl(testfile);
		assertEquals("LOAD",controller.readProcessOutput(ProcessControl.getProcessBuilder(new String[] {"load"}),""));
		assertEquals("MAP",controller.readProcessOutput(ProcessControl.getProcessBuilder(new String[] {"map"}),""));
		assertEquals("TRANSFER",controller.readProcessOutput(ProcessControl.getProcessBuilder(new String[] {"transfer"}),""));
	}
	
	

}
