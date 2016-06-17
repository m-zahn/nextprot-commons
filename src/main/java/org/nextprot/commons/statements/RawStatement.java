package org.nextprot.commons.statements;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class RawStatement {

	@AnnotationUnicity
	private String entry_accession;

	private String gene_name;

	@AnnotationUnicity
	private String isoform_accession;

	@AnnotationUnicity
	private String annot_loc_begin_canonical_ref;

	@AnnotationUnicity
	private String annot_loc_end_canonical_ref;

	private String annot_loc_begin_genomic_ref;
	private String annot_loc_end_genomic_ref;

	@AnnotationUnicity
	private String annotation_category;

	@AnnotationUnicity
	private String annot_cv_term_terminology;

	private String annot_cv_term_name;

	@AnnotationUnicity
	private String annot_cv_term_accession;

	@AnnotationUnicity
	private String biological_object_type;

	@AnnotationUnicity
	private String biological_object_accession;

	@AnnotationUnicity
	private String biological_object_database;

	@AnnotationUnicity
	private String annot_description;

	@AnnotationUnicity
	private String biological_subject_annot_hash;

	public String getExp_context_eco_detect_method() {
		return exp_context_eco_detect_method;
	}

	public void setExp_context_eco_detect_method(String exp_context_eco_detect_method) {
		this.exp_context_eco_detect_method = exp_context_eco_detect_method;
	}

	@AnnotationUnicity
	private String biological_object_annot_hash;

	private String annot_name;

	private String annot_source_database;
	private String variant_origin;

	@AnnotationUnicity
	private String variant_original_amino_acid;

	@AnnotationUnicity
	private String variant_variation_amino_acid;

	private String variant_original_genomic;
	private String variant_variation_genomic;
	private String variant_name_synonym_genomic;
	private String variant_name_synonym_protein;
	private String variant_name_synonym_isoform;
	private String variant_name_synonym_error;
	private String modified_entry_name;
	private String evidence_source_accession;
	private String reference_pubmed;
	private String exp_context_property_intensity;
	private String exp_context_eco_detect_method;

	public String getEntry_accession() {
		return entry_accession;
	}

	public void setEntry_accession(String entry_accession) {
		this.entry_accession = entry_accession;
	}

	public String getGene_name() {
		return gene_name;
	}

	public void setGene_name(String gene_name) {
		this.gene_name = gene_name;
	}

	public String getIsoform_accession() {
		return isoform_accession;
	}

	public void setIsoform_accession(String isoform_accession) {
		this.isoform_accession = isoform_accession;
	}

	public String getAnnot_loc_begin_canonical_ref() {
		return annot_loc_begin_canonical_ref;
	}

	public void setAnnot_loc_begin_canonical_ref(String annot_loc_begin_canonical_ref) {
		this.annot_loc_begin_canonical_ref = annot_loc_begin_canonical_ref;
	}

	public String getAnnot_loc_end_canonical_ref() {
		return annot_loc_end_canonical_ref;
	}

	public void setAnnot_loc_end_canonical_ref(String annot_loc_end_canonical_ref) {
		this.annot_loc_end_canonical_ref = annot_loc_end_canonical_ref;
	}

	public String getAnnot_loc_begin_genomic_ref() {
		return annot_loc_begin_genomic_ref;
	}

	public void setAnnot_loc_begin_genomic_ref(String annot_loc_begin_genomic_ref) {
		this.annot_loc_begin_genomic_ref = annot_loc_begin_genomic_ref;
	}

	public String getAnnot_loc_end_genomic_ref() {
		return annot_loc_end_genomic_ref;
	}

	public void setAnnot_loc_end_genomic_ref(String annot_loc_end_genomic_ref) {
		this.annot_loc_end_genomic_ref = annot_loc_end_genomic_ref;
	}

	public String getAnnotation_category() {
		return annotation_category;
	}

	public void setAnnotation_category(String annotation_category) {
		this.annotation_category = annotation_category;
	}

	public String getAnnot_cv_term_terminology() {
		return annot_cv_term_terminology;
	}

	public void setAnnot_cv_term_terminology(String annot_cv_term_terminology) {
		this.annot_cv_term_terminology = annot_cv_term_terminology;
	}

	public String getAnnot_cv_term_name() {
		return annot_cv_term_name;
	}

	public void setAnnot_cv_term_name(String annot_cv_term_name) {
		this.annot_cv_term_name = annot_cv_term_name;
	}

	public String getAnnot_cv_term_accession() {
		return annot_cv_term_accession;
	}

	public void setAnnot_cv_term_accession(String annot_cv_term_accession) {
		this.annot_cv_term_accession = annot_cv_term_accession;
	}

	public String getBiological_object_type() {
		return biological_object_type;
	}

	public void setBiological_object_type(String biological_object_type) {
		this.biological_object_type = biological_object_type;
	}

	public String getBiological_object_accession() {
		return biological_object_accession;
	}

	public void setBiological_object_accession(String biological_object_accession) {
		this.biological_object_accession = biological_object_accession;
	}

	public String getBiological_object_database() {
		return biological_object_database;
	}

	public void setBiological_object_database(String biological_object_database) {
		this.biological_object_database = biological_object_database;
	}

	public String getAnnot_description() {
		return annot_description;
	}

	public void setAnnot_description(String annot_description) {
		this.annot_description = annot_description;
	}

	public String getAnnot_hash() {

		TreeSet<String> contentItems = new TreeSet<String>();
		// https://calipho.isb-sib.ch/wiki/display/cal/Raw+statements+specifications

		Field[] fields = RawStatement.class.getDeclaredFields();
		for (Field f : fields) {

			boolean isFieldUsedForAnnotationUnicity = false;
			for (Annotation a : f.getAnnotations()) {
				if (a.annotationType().equals(AnnotationUnicity.class)) {
					isFieldUsedForAnnotationUnicity = true;
					break;
				}
			}

			if (isFieldUsedForAnnotationUnicity) {
				try {
					Object fieldValue = f.get(this);
					if(fieldValue != null){
						contentItems.add(fieldValue.toString());
					};

				} catch (IllegalArgumentException e) {
					throw new RuntimeException("illegal argument");
				} catch (IllegalAccessException e) {
					throw new RuntimeException("illegal access");
				}
			}
		}

		StringBuffer payload = new StringBuffer();
		Iterator<String> icItemsIterator = contentItems.iterator();
		while (icItemsIterator.hasNext()) {
			payload.append(icItemsIterator.next());
		}

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(payload.toString().getBytes());
			byte byteData[] = md.digest();

			// convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			// convert the byte to hex format method 2
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {

			throw new RuntimeException("Not possible to compute MD5");
		}

	}

	public static String[] getFieldNames(RawStatement object) {
		List<String> fieldNames = new ArrayList<String>();
		Field[] fields = RawStatement.class.getDeclaredFields();
		for (Field f : fields) {
			fieldNames.add(f.getName());
		}
		fieldNames.add("annot_hash");
		return fieldNames.toArray(new String[0]);
	}

	public static String[] getFieldValues(RawStatement object) {
		List<String> fieldValues = new ArrayList<String>();
		Field[] fields = RawStatement.class.getDeclaredFields();
		for (Field f : fields) {
			try {
				Object o = f.get(object);
				if (o == null) {
					fieldValues.add(null);
				} else
					fieldValues.add(f.get(object).toString());

			} catch (IllegalArgumentException e) {
				throw new RuntimeException("illegal argument");
			} catch (IllegalAccessException e) {
				throw new RuntimeException("illegal access");
			}
		}
		fieldValues.add(object.getAnnot_hash());
		return fieldValues.toArray(new String[0]);
	}

	public String getAnnot_source_accession() {
		return annot_source_accession;
	}

	public void setAnnot_source_accession(String annot_source_accession) {
		this.annot_source_accession = annot_source_accession;
	}

	public String getAnnot_source_database() {
		return annot_source_database;
	}

	public void setAnnot_source_database(String annot_source_database) {
		this.annot_source_database = annot_source_database;
	}

	public String getVariant_origin() {
		return variant_origin;
	}

	public void setVariant_origin(String variant_origin) {
		this.variant_origin = variant_origin;
	}

	public String getVariant_original_amino_acid() {
		return variant_original_amino_acid;
	}

	public void setVariant_original_amino_acid(String variant_original_amino_acid) {
		this.variant_original_amino_acid = variant_original_amino_acid;
	}

	public String getVariant_variation_amino_acid() {
		return variant_variation_amino_acid;
	}

	public void setVariant_variation_amino_acid(String variant_variation_amino_acid) {
		this.variant_variation_amino_acid = variant_variation_amino_acid;
	}

	public String getVariant_original_genomic() {
		return variant_original_genomic;
	}

	public void setVariant_original_genomic(String variant_original_genomic) {
		this.variant_original_genomic = variant_original_genomic;
	}

	public String getVariant_variation_genomic() {
		return variant_variation_genomic;
	}

	public void setVariant_variation_genomic(String variant_variation_genomic) {
		this.variant_variation_genomic = variant_variation_genomic;
	}

	public String getVariant_name_synonym_genomic() {
		return variant_name_synonym_genomic;
	}

	public void setVariant_name_synonym_genomic(String variant_name_synonym_genomic) {
		this.variant_name_synonym_genomic = variant_name_synonym_genomic;
	}

	public String getVariant_name_synonym_protein() {
		return variant_name_synonym_protein;
	}

	public void setVariant_name_synonym_protein(String variant_name_synonym_protein) {
		this.variant_name_synonym_protein = variant_name_synonym_protein;
	}

	public String getVariant_name_synonym_isoform() {
		return variant_name_synonym_isoform;
	}

	public void setVariant_name_synonym_isoform(String variant_name_synonym_isoform) {
		this.variant_name_synonym_isoform = variant_name_synonym_isoform;
	}

	public String getVariant_name_synonym_error() {
		return variant_name_synonym_error;
	}

	public void setVariant_name_synonym_error(String variant_name_synonym_error) {
		this.variant_name_synonym_error = variant_name_synonym_error;
	}

	public String getModified_entry_name() {
		return modified_entry_name;
	}

	public void setModified_entry_name(String modified_entry_name) {
		this.modified_entry_name = modified_entry_name;
	}

	public String getEvidence_source_accession() {
		return evidence_source_accession;
	}

	public void setEvidence_source_accession(String evidence_source_accession) {
		this.evidence_source_accession = evidence_source_accession;
	}

	public String getReference_pubmed() {
		return reference_pubmed;
	}

	public void setReference_pubmed(String reference_pubmed) {
		this.reference_pubmed = reference_pubmed;
	}

	private String annot_source_accession;

	public String getAnnot_name() {
		return annot_name;
	}

	public void setAnnot_name(String annot_name) {
		this.annot_name = annot_name;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public String getBiological_subject_annot_hash() {
		return biological_subject_annot_hash;
	}

	public void setBiological_subject_annot_hash(String biological_subject_annot_hash) {
		this.biological_subject_annot_hash = biological_subject_annot_hash;
	}

	public String getBiological_object_annot_hash() {
		return biological_object_annot_hash;
	}

	public void setBiological_object_annot_hash(String biological_object_annot_hash) {
		this.biological_object_annot_hash = biological_object_annot_hash;
	}

	public String getExp_context_property_intensity() {
		return exp_context_property_intensity;
	}

	public void setExp_context_property_intensity(String exp_context_property_intensity) {
		this.exp_context_property_intensity = exp_context_property_intensity;
	}

}
