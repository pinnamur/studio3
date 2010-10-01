package com.aptana.editor.markdown;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

class UnnumberedListScanner extends MarkdownScanner
{
	private boolean fFirstToken;

	@Override
	public IToken nextToken()
	{
		// HACK to avoid matching italics on un-numbered lists
		if (fFirstToken)
		{
			fTokenOffset = fOffset;
			read();
			fFirstToken = false;
			return getToken(""); //$NON-NLS-1$
		}

		IToken returnToken = super.nextToken();
		if (returnToken != null && returnToken.equals(Token.WHITESPACE))
		{
			// Check if it's a newline
			try
			{
				String src = fDocument.get(getTokenOffset(), getTokenLength());
				if (src.endsWith("\n") || src.endsWith("\r")) //$NON-NLS-1$ //$NON-NLS-2$
				{
					fFirstToken = true;
				}
			}
			catch (BadLocationException e)
			{
				// ignore
			}
		}
		return returnToken;
	}

	@Override
	public void setRange(IDocument document, int offset, int length)
	{
		fFirstToken = true;
		super.setRange(document, offset, length);
	}
}