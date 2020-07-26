package org.projector.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringJoinerTest {
    @Test
    public void testToString() {
        StringJoiner sj = new StringJoiner(":");
        assertEquals("", sj.toString());

        sj = new StringJoiner("Hello", ", ");
        assertEquals("Hello", sj.toString());

        sj.append("man!");
        assertEquals("Hello, man!", sj.toString());
    }

	@Test
	public void testLength() {
		StringJoiner sj = new StringJoiner("Hello world!", ":");
		assertEquals(12, sj.length());

		sj = new StringJoiner("", ":");
		assertEquals(0, sj.length());
	}

	@Test
	public void testCharAt() {
		StringJoiner sj = new StringJoiner("Hello world!", ":");
		assertEquals('w', sj.charAt(6));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testCharAtNegative() {
		StringJoiner sj = new StringJoiner("Hello world!", ":");
		sj.charAt(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testCharAtPositive() {
		StringJoiner sj = new StringJoiner("Hello world!", ":");
		sj.charAt(18);
	}

	@Test
	public void testSubSequence() {
		StringJoiner sj = new StringJoiner("Hello world!", ":");
		assertEquals("Hello", sj.subSequence(0, 5));
		assertEquals("wo", sj.subSequence(6, 8));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSubSequenceNegativeStart() {
		StringJoiner sj = new StringJoiner("Hello world!", ":");
		sj.subSequence(-1, 5);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSubSequenceNegativeEnd() {
		StringJoiner sj = new StringJoiner("Hello world!", ":");
		sj.subSequence(5, -1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSubSequenceStartAfterEnd() {
		StringJoiner sj = new StringJoiner("Hello world!", ":");
		sj.subSequence(6, 5);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSubSequenceStartAfterLength() {
		StringJoiner sj = new StringJoiner("Hello world!", ":");
		sj.subSequence(18, 19);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSubSequenceEndAfterLength() {
		StringJoiner sj = new StringJoiner("Hello world!", ":");
		sj.subSequence(18, 29);
	}

	@Test
	public void testAppend() {
		StringJoiner sj = new StringJoiner("Hello world", ", ");
		assertEquals("Hello world, man!", sj.append("man!").toString());

		sj = new StringJoiner("Hello world", ":");
		assertEquals("Hello world:", sj.append("").toString());

		sj = new StringJoiner("", ";");
		assertEquals(";Hello world", sj.append("Hello world").toString());

		sj = new StringJoiner("Hello", " ");
		StringJoiner sj2 = new StringJoiner("world!", " ");
		assertEquals("Hello world!", sj.append(sj2).toString());
	}

	@Test
	public void testAppendRange() {
		StringJoiner sj = new StringJoiner("Hello world", ", ");
		assertEquals("Hello world, man!", sj.append("man! What's up?", 0, 4).toString());
	}

	@Test
	public void testAppendChar() {
		StringJoiner sj = new StringJoiner("Hello world", "");
		assertEquals("Hello world!", sj.append('!').toString());
	}

	@Test
	public void testDeleteCharAt() {
		StringJoiner sj = new StringJoiner("Hello world, man!", "");
		assertEquals("Helloworld, man!", sj.deleteCharAt(5).toString());
	}

	@Test
	public void testDelete() {
		StringJoiner sj = new StringJoiner("Hello world, man!", "");
		assertEquals("Hello, man!", sj.delete(5, 11).toString());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testDeleteStartNegative() {
		StringJoiner sj = new StringJoiner("Hello world, man!", "");
		sj.delete(-1, 11);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testDeleteEndNegative() {
		StringJoiner sj = new StringJoiner("Hello world, man!", "");
		sj.delete(5, -1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testDeleteStartAfterLength() {
		StringJoiner sj = new StringJoiner("Hello world, man!", "");
		sj.delete(28, 29);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testDeleteEndAfterLength() {
		StringJoiner sj = new StringJoiner("Hello world, man!", "");
		sj.delete(5, 29);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testDeleteStartAfterEnd() {
		StringJoiner sj = new StringJoiner("Hello world, man!", "");
		sj.delete(7, 3);
	}

	@Test
	public void testLineJoining() {
		StringJoiner sj = new StringJoiner("\n");

		String[] lines = new String[] {
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
		    "Ut non venenatis quam. Cras at laoreet enim.",
		    "Donec dignissim ultrices urna ut cursus.",
			"Curabitur consequat tellus magna, a efficitur ante porttitor id."
		};

		String expected = "";

		for (int i = 0; i < lines.length; i++) {
			expected += lines[i];
			if (i < lines.length - 1) {
				expected += "\n";
			}

			sj.append(lines[i]);
		}

		assertEquals(expected, sj.toString());
	}
}
