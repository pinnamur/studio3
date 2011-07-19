/**
 * Copyright (c) 2005-2011 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Eclipse Public License (EPL).
 * Please see the license.txt included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 */
package com.aptana.editor.findbar.impl;

import com.aptana.editor.findbar.impl.ComboWithMultiLinePasteSupport;

import junit.framework.TestCase;


/**
 * @author fabioz
 *
 */
public class QuoteMultiLineTest extends TestCase
{

    public void testQuote() throws Exception {
        assertEquals("a", ComboWithMultiLinePasteSupport.quote("a", true));
        assertEquals("", ComboWithMultiLinePasteSupport.quote("", true));
        assertEquals("\\R\\Qa\\E", ComboWithMultiLinePasteSupport.quote("\na", true));
        assertEquals("\\Qa\\E\\R", ComboWithMultiLinePasteSupport.quote("a\n", true));
        assertEquals("\\R", ComboWithMultiLinePasteSupport.quote("\n", true));
        assertEquals("\\R\\R", ComboWithMultiLinePasteSupport.quote("\n\n", true));
        assertEquals("\\R\\R", ComboWithMultiLinePasteSupport.quote("\r\n\n", true));
    }
}
