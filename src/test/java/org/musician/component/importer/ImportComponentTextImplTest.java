package org.musician.component.importer;

import org.junit.Test;

public class ImportComponentTextImplTest {

	private ImportComponent importComponent = new ImportComponentTextImpl();

	@Test
	public void testImportSong()
			throws Exception {

		importComponent.importSong("song_text.txt");


	}
}