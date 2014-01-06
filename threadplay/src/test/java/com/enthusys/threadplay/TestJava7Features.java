package com.enthusys.threadplay;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

import static org.junit.Assert.assertEquals;


/**
 * User: mchernyak
 * Date: 1/6/14
 * Time: 8:15 AM
 */
public class TestJava7Features {
	private static final Logger logger = LoggerFactory.getLogger(TestCallCounter.class);

	public static final int[] phases = {
			0b00110001,
			0b01100010,
			0b11000100,
			0b10001001,
			0b00010011,
			0b00100110,
			0b01001100,
			0b10011000
	};

	/**
	 * Binary Literals and String switch
	 */
	//@Test
	public void testStringSwitch() {
		for (int i = 0; i < phases.length; i++) {
			logger.info("****** {} - {}", Integer.toBinaryString(phases[i]), phases[i]);
			switch (Integer.toBinaryString(phases[i])) {
				case ("1100010"):
					logger.info("----- {}", phases[i]);
					assertEquals(98, phases[i]);
					break;
				case ("1001100"):
					logger.info("----- {}", phases[i]);
					assertEquals(76, phases[i]);
					break;
				case ("10011000"):
					logger.info("----- {}", phases[i]);
					assertEquals(152, phases[i]);
					break;
			}
		}
	}

	@Test
	public void testTryCatch() {
		String zipfile = "/home/mchernyak/data/zipfile.zip";
		String outfile = "/home/mchernyak/data/zipfile.out";

		try {
			writeToFileZipFileContents(zipfile, outfile);
		} catch (IOException | ArithmeticException e) {
			logger.error("***** catching exception: {}", e.getMessage());
		}

	}

	/**
	 * @param zipFileName
	 * @param outputFileName
	 * @throws java.io.IOException
	 */
	private void writeToFileZipFileContents(String zipFileName, String outputFileName)
			throws java.io.IOException {

		java.nio.charset.Charset charset = java.nio.charset.Charset.forName("US-ASCII");
		java.nio.file.Path outputFilePath = java.nio.file.Paths.get(outputFileName);

		// Generate a divide by zero exception.
		// Without the try() clause, the method does not close the output file which stays empty
		// Open zip file and create output file with try-with-resources statement
		try (
				java.util.zip.ZipFile zf = new java.util.zip.ZipFile(zipFileName);
				java.io.BufferedWriter writer = java.nio.file.Files.newBufferedWriter(outputFilePath, charset)
		) {
			// Enumerate each entry
			for (java.util.Enumeration entries = zf.entries(); entries.hasMoreElements(); ) {
				// Get the entry name and write it to the output file
				String newLine = System.getProperty("line.separator");
				String zipEntryName = ((java.util.zip.ZipEntry) entries.nextElement()).getName() + newLine;
				writer.write(zipEntryName, 0, zipEntryName.length());
			}
			// generate a divide-by-zero exception
			int x = 0, y = 3;
			int n = y / x;
		}
	}

}
