package org.nextprot.commons.dblinks;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class DBLinkTest {

	@Test
	public void testEnsemblDBLink() {

		String url = "http://some-uri-that-will-be-discared.org/";
		String accession = "ENSG00000139618";
		String database = "Ensembl";
		Map<String, String> properties = new HashMap<>(); // empty properties

		String linkResolved = DBLinkResolver.resolveLinkTarget(url, accession, database, properties);
		Assert.assertEquals(linkResolved, "http://www.ensembl.org/Homo_sapiens/Gene/Summary?db=core;g=ENSG00000139618");

	}

}
