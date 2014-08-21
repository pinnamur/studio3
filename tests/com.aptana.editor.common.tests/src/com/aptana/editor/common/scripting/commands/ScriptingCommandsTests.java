/**
 * Aptana Studio
 * Copyright (c) 2005-2011 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the GNU Public License (GPL) v3 (with exceptions).
 * Please see the license.html included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 */
package com.aptana.editor.common.scripting.commands;

import org.junit.runners.Suite.SuiteClasses;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;

@RunWith(Suite.class)
@SuiteClasses({UtilitiesTest.class, TextEditorUtilsTest.class, })
public class ScriptingCommandsTests
{

//	public static Test suite()
//	{
//		TestSuite suite = new TestSuite(ScriptingCommandsTests.class.getName())
//		{
//			@Override
//			public void runTest(Test test, TestResult result)
//			{
//				System.err.println("Running test: " + test.toString());
//				super.runTest(test, result);
//			}
//		};
//		//$JUnit-BEGIN$
//		suite.addTestSuite(UtilitiesTest.class);
//		suite.addTestSuite(TextEditorUtilsTest.class);
//		//$JUnit-END$
//		return suite;
//	}
//
}
