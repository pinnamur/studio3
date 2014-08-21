package com.aptana.editor.css.formatter.tests;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import com.aptana.editor.common.formatting.AbstractFormatterTestCase;

public class FormattingTests extends AbstractFormatterTestCase
{
	@Parameters(name = "{0}")
	public static Iterable<Object[]> data()
	{
		return Arrays.asList(AbstractFormatterTestCase.getFiles(TEST_BUNDLE_ID, FILE_TYPE));
	}

	private static String FORMATTER_FACTORY_ID = "com.aptana.editor.css.formatterFactory"; //$NON-NLS-1$
	private static String TEST_BUNDLE_ID = "com.aptana.editor.css.formatter.tests"; //$NON-NLS-1$
	private static String FILE_TYPE = "css"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * @see com.aptana.editor.common.formatting.AbstractFormatterTestCase#getTestBundleId()
	 */
	@Override
	protected String getTestBundleId()
	{
		return TEST_BUNDLE_ID;
	}

	/*
	 * (non-Javadoc)
	 * @see com.aptana.editor.common.formatting.AbstractFormatterTestCase#getFormatterId()
	 */
	@Override
	protected String getFormatterId()
	{
		return FORMATTER_FACTORY_ID;
	}

	/*
	 * (non-Javadoc)
	 * @see com.aptana.editor.common.formatting.AbstractFormatterTestCase#getFileType()
	 */
	@Override
	protected String getFileType()
	{
		return FILE_TYPE;
	}

	// Tests that are not working are named without the .css extension

	// Notes on Tests:

	/*
	 * Test 7: We are not able to parse invalid css, so this test does not work for now.
	 */

	/*
	 * Test 8: Corresponds to test 15 in studio 1.5 test. Result is different than 1.5 test since the newlines within an
	 * @media rule does not have its own setting
	 */

	/*
	 * Test 11: Studio 1.5 tests seems to move all the selectors to a new line... The current behavior matches Studio 2
	 * behavior.
	 */

	/*
	 * Test 14: The asterisk before a declaration is not valid CSS, which results in incorrect formatting (the parser is
	 * reading * as a different declaration). We may want to make it so it does not modify declarations with incorrect
	 * syntax like that
	 */

}
