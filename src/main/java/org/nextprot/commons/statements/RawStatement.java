package org.nextprot.commons.statements;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

public class RawStatement {

	public String getSeparatedValues(String separator) {

		List<String> list = new ArrayList<>();
		list.add(entry_accession);
		list.add(gene_name);
		list.add(isoform_accession);
		list.add(annot_loc_begin_canonical_ref);
		list.add(annot_loc_end_canonical_ref);
		list.add(annot_loc_begin_genomic_ref);
		list.add(annot_loc_end_genomic_ref);
		list.add(annotation_category);
		list.add(annot_cv_term_terminology);
		list.add(annot_cv_term_name);
		list.add(annot_cv_term_accession);
		list.add(biological_object_type);
		list.add(biological_object_accession);
		list.add(biological_object_database);
		list.add(annot_description);
		list.add(getAnnot_hash()); // Careful with this one
		list.add(annot_source_accession);
		list.add(annot_source_database);
		list.add(variant_origin);
		list.add(variant_original_amino_acid);
		list.add(variant_original_genomic);
		list.add(variant_variation_genomic);
		list.add(variant_name_synonym_genomic);
		list.add(variant_name_synonym_protein);
		list.add(variant_name_synonym_isoform);
		list.add(variant_name_synonym_error);
		list.add(modified_entry_name);
		list.add(reference_annot_hash);
		list.add(evidence_source_accession);
		list.add(reference_pubmed);

		StringBuilder sb = new StringBuilder();
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			sb.append(it.next());
			sb.append(separator);
		}
		return sb.toString();

	}

	private String entry_accession;
	private String gene_name;
	private String isoform_accession;
	private String annot_loc_begin_canonical_ref;
	private String annot_loc_end_canonical_ref;
	private String annot_loc_begin_genomic_ref;
	private String annot_loc_end_genomic_ref;
	private String annotation_category;
	private String annot_cv_term_terminology;
	private String annot_cv_term_name;
	private String annot_cv_term_accession;
	private String biological_object_type;
	private String biological_object_accession;
	private String biological_object_database;
	private String annot_description;
	private String annot_hash;
	private String annot_source_accession;
	private String annot_source_database;
	private String variant_origin;
	private String variant_original_amino_acid;
	private String variant_variation_amino_acid;
	private String variant_original_genomic;
	private String variant_variation_genomic;
	private String variant_name_synonym_genomic;
	private String variant_name_synonym_protein;
	private String variant_name_synonym_isoform;
	private String variant_name_synonym_error;
	private String modified_entry_name;
	private String reference_annot_hash;
	private String evidence_source_accession;
	private String reference_pubmed;

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

	public String getAnnot_hash() throws NoSuchAlgorithmException {
		StringBuffer payload = new StringBuffer();
		// according to
		// https://calipho.isb-sib.ch/wiki/display/cal/Raw+statements+specifications
		payload.append(entry_accession);
		payload.append(isoform_accession);
		payload.append(annot_loc_begin_canonical_ref);
		payload.append(annot_loc_end_canonical_ref);
		payload.append(annotation_category);
		payload.append(annot_cv_term_terminology);
		payload.append(annot_cv_term_accession);
		payload.append(annot_cv_term_name);
		payload.append(biological_object_type);
		payload.append(biological_object_accession);
		payload.append(biological_object_database);
		payload.append(annot_description);
		payload.append(annotation_category);
		payload.append(variant_original_amino_acid);
		payload.append(variant_variation_amino_acid);
		payload.append(variant_original_genomic);
		payload.append(variant_variation_genomic);
		payload.append(modified_entry_name);
		payload.append(reference_annot_hash);
		payload.append(annotation_category);
		payload.append(annot_cv_term_terminology);
		payload.append(annot_cv_term_accession);
		payload.append(annot_cv_term_name);

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(payload.toString().getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		System.out.println("Digest(in hex format):: " + sb.toString());

		// convert the byte to hex format method 2
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}

		return hexString.toString();
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

	public String getReference_annot_hash() {
		return reference_annot_hash;
	}

	public void setReference_annot_hash(String reference_annot_hash) {
		this.reference_annot_hash = reference_annot_hash;
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

}
