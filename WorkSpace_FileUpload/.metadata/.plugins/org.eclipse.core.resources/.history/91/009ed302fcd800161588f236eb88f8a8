package local.filestorage.rest.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import local.filestorage.rest.util.StorageUtil;

public class StorageUtilTests {
	private static final String FOLDER = "C:\\storage";
	private static final String FILENAME = "example.txt";

	@Test
	public void testStoreFile() {
		try {
			byte[] expected = "foo-bar".getBytes();
			StorageUtil.store(FILENAME, FOLDER, expected);

			byte[] actual = StorageUtil.retrieve(FILENAME, FOLDER);
			
			assertArrayEquals(expected, actual);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
