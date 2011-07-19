/**
 * Copyright (c) 2005-2011 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Eclipse Public License (EPL).
 * Please see the license.txt included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 */
package com.aptana.editor.findbar.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;

import com.aptana.core.util.StringUtil;

/**
 * Combo which will override the paste action in text editor when the combo receives focus so that the text is quoted
 * to consider multi-lines.
 * 
 * @author fabioz
 */
public class ComboWithMultiLinePasteSupport extends Combo implements FocusListener 
{
    
    private final boolean onPasteQuoteForFind;
    private final ITextEditor textEditor;
    private IAction originalPaste;

    /**
     * @param onPasteQuoteForFind whether the text should be quoted for the find or for the replace.
     * @param textEditor the text editor where the paste action will be replaced when this combo receives focus.
     */
    public ComboWithMultiLinePasteSupport(
            Composite parent, int style, boolean onPasteQuoteForFind, ITextEditor textEditor) 
    {
        super(parent, style);
        this.onPasteQuoteForFind = onPasteQuoteForFind;
        this.addFocusListener(this);
        this.textEditor = textEditor;
    }
    
    @Override
    protected void checkSubclass() 
    {
        
    }
    
    /**
     * The paste is overridden so that we can quote considering multi-lines.
     */
    @Override
    public void paste() 
    {
        checkWidget ();
        if ((getStyle() & SWT.READ_ONLY) != 0)
        {
            return;
        }
        Clipboard clipboard = new Clipboard(this.getDisplay());
        String contents = (String) clipboard.getContents(TextTransfer.getInstance());
        if(contents == null)
        {
            return;
        }
        if(onPasteQuoteForFind)
        {
            this.replaceSelection(quote(contents, true));
        }
        else
        {
            this.replaceSelection(quote(contents, false));
        }
    }

    /**
     * @param text the text which should replace the current text selected.
     */
    private void replaceSelection(String text) 
    {
        Point selection = this.getSelection();
        String current = this.getText();
        StringBuilder buf = new StringBuilder(current);
        buf.replace(selection.x, selection.y, text);
        this.setText(buf.toString());
        int coord = selection.x + text.length();
        this.setSelection(new Point(coord, coord));
    }

    /**
     * @param text the text to be quoted.
     * @param quoteForFind whether the text should be quoted for a find or a replace.
     * @return the quoted text.
     */
    protected static String quote(String text, boolean quoteForFind)
    {
        //If we don't have a new line character, just give the original input as the result.
        if(text.indexOf('\r') == -1 && text.indexOf('\n') == -1)
        {
            return text;
        }
        
        List<String> lines = StringUtil.splitInLines(text);
        for(int i=0;i<lines.size();i++)
        {
            String line = lines.get(i);
            if(quoteForFind && line.length() > 0)
            {
                line = Pattern.quote(line);
            }
            lines.set(i, line);
        }
        //\R is the universal new line.
        String findRegexp = StringUtil.join("\\R", lines); //$NON-NLS-1$
        return findRegexp;
    }


    /**
     * @return the action that'll do the paste in this combo.
     */
    private IAction createCustomPasteAction() 
    {
        Action action = new Action() 
        {
            @Override
            public void run() 
            {
                paste();
            }
        };
        action.setActionDefinitionId(IWorkbenchCommandConstants.EDIT_PASTE);

        return action;
    }
    
    
    /**
     * When gaining focus, create the custom paste action.
     */
    public void focusGained(FocusEvent e) 
    {
        if(originalPaste == null)
        {
            originalPaste = textEditor.getAction(ITextEditorActionConstants.PASTE);
            textEditor.setAction(ITextEditorActionConstants.PASTE, createCustomPasteAction());
        }
    }

    /**
     * When loosing the focus, restore the original paste action.
     */
    public void focusLost(FocusEvent e) 
    {
        if(originalPaste != null)
        {
            textEditor.setAction(ITextEditorActionConstants.PASTE, originalPaste);
            originalPaste = null;
        }
    }

    
    
}
