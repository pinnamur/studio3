package com.aptana.core.build;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Collections;

import junit.framework.TestCase;

import org.eclipse.core.runtime.IProgressMonitor;

import com.aptana.buildpath.core.BuildPathCorePlugin;
import com.aptana.core.build.IBuildParticipant.BuildType;
import com.aptana.index.core.build.BuildContext;

public class RequiredBuildParticipantTest
{

	private RequiredBuildParticipant participant;

	@Before
	public void setUp() throws Exception
	{
//		super.setUp();
		participant = new TestParticipant();
	}

	@After
	public void tearDown() throws Exception
	{
		participant = null;
//		super.tearDown();
	}

	@Test
	public void testRequiredParticipantCantBeDisabled() throws Exception
	{
		assertTrue(participant.isRequired());
		assertTrue(participant.isEnabled(BuildType.BUILD));
		assertTrue(participant.isEnabled(BuildType.RECONCILE));

		IBuildParticipantWorkingCopy wc = participant.getWorkingCopy();
		wc.setEnabled(BuildType.BUILD, false);
		wc.setEnabled(BuildType.RECONCILE, false);
		wc.doSave();

		assertTrue(participant.isEnabled(BuildType.BUILD));
		assertTrue(participant.isEnabled(BuildType.RECONCILE));
	}

	@Test
	public void testRequiredParticipantCantSetFilters() throws Exception
	{
		assertTrue(participant.isRequired());
		assertEquals(Collections.emptyList(), participant.getFilters());

		IBuildParticipantWorkingCopy wc = participant.getWorkingCopy();
		String[] filters = new String[] { ".*-wbkit-.*" };
		wc.setFilters(filters);
		wc.doSave();

		assertEquals(Collections.emptyList(), participant.getFilters());
	}

	private static final class TestParticipant extends RequiredBuildParticipant
	{
		protected String getPreferenceNode()
		{
			return BuildPathCorePlugin.PLUGIN_ID;
		}

		public void buildFile(BuildContext context, IProgressMonitor monitor)
		{
			// no-op
		}

		public void deleteFile(BuildContext context, IProgressMonitor monitor)
		{
			// no-op
		}
	}
}
