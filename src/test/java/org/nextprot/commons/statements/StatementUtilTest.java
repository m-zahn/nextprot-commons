package org.nextprot.commons.statements;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.junit.Assert;
import org.junit.Test;
import org.nextprot.commons.statements.constants.AnnotationType;

public class StatementUtilTest {

	@Test
	public void testRawStatementEquals() throws IOException {
		
		
		//Copied directly from kant.isb-sib.ch:9000/bioeditor/gene/msh6/statements
		
		String subjectOne = "{\n" + 
				"  \"ANNOTATION_CATEGORY\" : \"variant\",\n" + 
				"  \"ANNOTATION_NAME\" : \"MSH6-p.Phe1088Leufs*5\",\n" + 
				"  \"ANNOT_SOURCE_ACCESSION\" : \"\",\n" + 
				"  \"ASSIGMENT_METHOD\" : \"curated\",\n" + 
				"  \"ASSIGNED_BY\" : \"NextProt\",\n" + 
				"  \"DEBUG_NOTE\" : \"Publication not found for CAVA-VD012367nullremoving one allele for multiple genes CAVA-VP014556 set: Set(MSH6-p.Phe1088Leufs*5, MSH6-p.Phe1088Serfs*2, PMS2-p.Arg315*) filtered set Set(MSH6-p.Phe1088Leufs*5, MSH6-p.Phe1088Serfs*2) subject: MSH6-p.Phe1088Serfs*2 gene name msh6\\n\",\n" + 
				"  \"ENTRY_ACCESSION\" : \"NX_P52701\",\n" + 
				"  \"EVIDENCE_CODE\" : \"ECO:0000219\",\n" + 
				"  \"EVIDENCE_QUALITY\" : \"GOLD\",\n" + 
				"  \"GENE_NAME\" : \"MSH6\",\n" + 
				"  \"LOCATION_BEGIN\" : \"1088\",\n" + 
				"  \"LOCATION_END\" : \"1092\",\n" + 
				"  \"NEXTPROT_ACCESSION\" : \"NX_P52701\",\n" + 
				"  \"RESOURCE_TYPE\" : \"publication\",\n" + 
				"  \"SOURCE\" : \"BioEditor\",\n" + 
				"  \"VARIANT_ORIGINAL_AMINO_ACID\" : \"F\",\n" + 
				"  \"VARIANT_VARIATION_AMINO_ACID\" : \"L\"\n" + 
				"}";
		
		String subjectTwo = "{\n" + 
				"  \"ANNOTATION_CATEGORY\" : \"variant\",\n" + 
				"  \"ANNOTATION_NAME\" : \"MSH6-p.Phe1088Leufs*5\",\n" + 
				"  \"ANNOT_SOURCE_ACCESSION\" : \"\",\n" + 
				"  \"ASSIGMENT_METHOD\" : \"curated\",\n" + 
				"  \"ASSIGNED_BY\" : \"NextProt\",\n" + 
				"  \"DEBUG_NOTE\" : \"Publication not found for CAVA-VD012367\",\n" + 
				"  \"ENTRY_ACCESSION\" : \"NX_P52701\",\n" + 
				"  \"EVIDENCE_CODE\" : \"ECO:0000219\",\n" + 
				"  \"EVIDENCE_QUALITY\" : \"GOLD\",\n" + 
				"  \"GENE_NAME\" : \"MSH6\",\n" + 
				"  \"LOCATION_BEGIN\" : \"1088\",\n" + 
				"  \"LOCATION_END\" : \"1092\",\n" + 
				"  \"NEXTPROT_ACCESSION\" : \"NX_P52701\",\n" + 
				"  \"RESOURCE_TYPE\" : \"publication\",\n" + 
				"  \"SOURCE\" : \"BioEditor\",\n" + 
				"  \"STATEMENT_ID\" : \"c62d75e6d330281361fdf22c7bc55586\",\n" + 
				"  \"VARIANT_ORIGINAL_AMINO_ACID\" : \"F\",\n" + 
				"  \"VARIANT_VARIATION_AMINO_ACID\" : \"L\"\n" + 
				"}";
		
		
		String subjectThree = "{\n" + 
				"  \"ANNOTATION_CATEGORY\" : \"variant\",\n" + 
				"  \"ANNOTATION_NAME\" : \"MSH6-p.Phe1088Leufs*5\",\n" + 
				"  \"ANNOT_SOURCE_ACCESSION\" : \"\",\n" + 
				"  \"ASSIGMENT_METHOD\" : \"curated\",\n" + 
				"  \"ASSIGNED_BY\" : \"NextProt\",\n" + 
				"  \"DEBUG_NOTE\" : \"Publication not found for CAVA-VD012367nullremoving one allele for multiple genes CAVA-VP011959 set: Set(MSH6-p.Phe1088Leufs*5, MSH6-p.Phe1088Serfs*2, PMS2-p.Arg315*) filtered set Set(MSH6-p.Phe1088Leufs*5, MSH6-p.Phe1088Serfs*2) subject: MSH6-p.Phe1088Leufs*5 gene name msh6\\n\",\n" + 
				"  \"ENTRY_ACCESSION\" : \"NX_P52701\",\n" + 
				"  \"EVIDENCE_CODE\" : \"ECO:0000219\",\n" + 
				"  \"EVIDENCE_QUALITY\" : \"GOLD\",\n" + 
				"  \"GENE_NAME\" : \"MSH6\",\n" + 
				"  \"LOCATION_BEGIN\" : \"1088\",\n" + 
				"  \"LOCATION_END\" : \"1092\",\n" + 
				"  \"NEXTPROT_ACCESSION\" : \"NX_P52701\",\n" + 
				"  \"RESOURCE_TYPE\" : \"publication\",\n" + 
				"  \"SOURCE\" : \"BioEditor\",\n" + 
				"  \"STATEMENT_ID\" : \"c1288fcf4a5c1c29c5bec4320563cba0\",\n" + 
				"  \"VARIANT_ORIGINAL_AMINO_ACID\" : \"F\",\n" + 
				"  \"VARIANT_VARIATION_AMINO_ACID\" : \"L\"\n" + 
				"}";
		
		Statement sub1 = buildStatementFromJsonString(subjectOne);
		Statement sub2 = buildStatementFromJsonString(subjectTwo);

		Statement vp1 = StatementBuilder.createNew().addField(StatementField.ANNOTATION_CATEGORY, "phenotypic").addSubjects(Arrays.asList(sub1, sub2)).build();
		Statement vp2 = StatementBuilder.createNew().addField(StatementField.ANNOTATION_CATEGORY, "phenotypic").addSubjects(Arrays.asList(sub2, sub1)).build();
		
		String vp1Hash = StatementUtil.computeAndGetAnnotationId(vp1, AnnotationType.ENTRY);
		String vp2Hash = StatementUtil.computeAndGetAnnotationId(vp2, AnnotationType.ENTRY);
		
		Assert.assertEquals(vp1Hash, vp2Hash);
				
	}
	
	
	
	
	
	Statement buildStatementFromJsonString(String jsonString) throws IOException{

		JsonObject jo = Json.parse(jsonString).asObject();

		StatementBuilder sb = StatementBuilder.createNew();

		Arrays.asList(StatementField.values()).forEach(sf -> {
			String value = jo.getString(sf.name(), null);
			if(value != null){
				sb.addField(sf, value);
			}
		});


		return sb.build();
		
	}

	
}