package org.projector.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DefaultLoopTest {
	@Test
	public void testSkip() {
		DefaultLoop loop = new DefaultLoop();
		loop.setIndex(0);
		loop.skip();
		
		assertEquals(DefaultLoop.SKIP, loop.getAction());
	}
	
	@Test
	public void testBreak() {
		DefaultLoop loop = new DefaultLoop();
		loop.setIndex(0);
		loop.stop();
		
		assertEquals(DefaultLoop.STOP, loop.getAction());
	}
	
	@Test
	public void testIndex() {
		DefaultLoop loop = new DefaultLoop();
		loop.setIndex(791);
		
		assertEquals(791, loop.index());
	}
}
