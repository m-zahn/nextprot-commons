package org.nextprot.commons.statements;

import org.junit.Assert;
import org.junit.Test;

public class TargetIsoformDeserializationTest {

	@Test
	public void testRawStatementEquals() {

		TargetIsoformSet tis = new TargetIsoformSet();
		TargetIsoformStatementPosition tisp1 = new TargetIsoformStatementPosition("NX-P01308-1", 30, 40, null, "main");
		TargetIsoformStatementPosition tisp2 = new TargetIsoformStatementPosition("NX-P01308-2", 50, 40, "specific", "secondary");

		tis.add(tisp1);
		tis.add(tisp2);


		String jsonString = tis.serializeToJsonString();
		System.out.println(jsonString);
		TargetIsoformSet tis2 = TargetIsoformSet.deSerializeFromJsonString(jsonString);


		Assert.assertEquals(tis, tis2);


	}
	

	
}