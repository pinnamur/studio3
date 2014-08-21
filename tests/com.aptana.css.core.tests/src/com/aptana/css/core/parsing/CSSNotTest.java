/**
 * Aptana Studio
 * Copyright (c) 2005-2013 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the GNU Public License (GPL) v3 (with exceptions).
 * Please see the license.html included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 */
package com.aptana.css.core.parsing;

import org.junit.Test;

public class CSSNotTest extends CSSTokensTest
{
	@Test
	public void testNotSelector()
	{
		assertToken("div:not(.home){}", new TokenInfo(CSSTokenType.IDENTIFIER, 0, 3), new TokenInfo(CSSTokenType.COLON,
				3, 1), new TokenInfo(CSSTokenType.NOT, 4, 3));
	}
}
