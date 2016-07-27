package org.nextprot.commons.statements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.nextprot.commons.algo.MD5Algo;
import org.nextprot.commons.constants.QualityQualifier;
import org.nextprot.commons.statements.constants.AnnotationType;
import org.nextprot.commons.utils.StringUtils;

public class StatementUtil {
	
	public static String computeAndGetAnnotationId(Statement statement, AnnotationType type) {

		// Filter fields which are used to compute unicity
		Set<StatementField> unicityFields = new TreeSet<StatementField>();
		StatementField[] fields = StatementField.values();
		for (StatementField field : fields) {
			// According with
			// https://calipho.isb-sib.ch/wiki/display/cal/Raw+statements+specifications

			if (type.equals(AnnotationType.ISOFORM)) {
				if (field.isIsoUnicity()) {
					unicityFields.add(field);
				}
			} else if (type.equals(AnnotationType.ENTRY)) {
				if (field.isEntryUnicity()) {
					unicityFields.add(field);
				}
			}else if (type.equals(AnnotationType.STATEMENT)) { // All fields for the statement

				if(!field.equals(StatementField.STATEMENT_ID)){
					unicityFields.add(field);
				}
			}
		}

		TreeSet<String> contentItems = new TreeSet<String>();
		for (StatementField unicityField : unicityFields) {
			String value = statement.getValue(unicityField);
			if (value != null) {
				contentItems.add(value);
			}
		}

		StringBuffer payload = new StringBuffer();
		Iterator<String> icItemsIterator = contentItems.iterator();
		while (icItemsIterator.hasNext()) {
			payload.append(icItemsIterator.next());
		}

		return MD5Algo.computeMD5(payload.toString());

	}
	
	static void checkStatementsValidity(Collection<Statement> statements){
		checkQuality(statements);
	}
	
	static void checkQuality(Collection<Statement> statements){
		
		for(Statement s : statements){
			String evidenceQuality = s.getValue(StatementField.EVIDENCE_QUALITY);
			if(evidenceQuality == null){
				throw new RuntimeException("Statement field EVIDENCE_QUALITY can't be null");
			}
			try {
				QualityQualifier.valueOf(s.getValue(StatementField.EVIDENCE_QUALITY));
			}catch (IllegalArgumentException e){
				throw new RuntimeException("Statement field EVIDENCE_QUALITY must be set to either GOLD or SILVER, but was" + evidenceQuality);
			}
		}
	}
		
		
	public static void computeAndSetAnnotationIdsForRawStatements(Collection<Statement> statements, AnnotationType annotationType){
		
		checkStatementsValidity(statements);
		
		HashMap<String, Statement> normalStatementsMap = new HashMap<String, Statement>();
		List<Statement> statementsOnModifiedSubjects = new ArrayList<Statement>();

		//Takes all normal statements and put them in a map and all the other put them in a list 
		for(Statement s : statements){
			if(!s.hasModifiedSubject()){
				s.computeAndSetAnnotationIds(annotationType);
				normalStatementsMap.put(s.getStatementId(), s);
			}else {
				statementsOnModifiedSubjects.add(s);
			}
		}
		
		for(Statement complexStatement : statementsOnModifiedSubjects){
			
			String subjectIds = complexStatement.getSubjectStatementIds();
			setValues(complexStatement, StatementField.SUBJECT_ANNOTATION_IDS, subjectIds, StatementField.ANNOTATION_ID, normalStatementsMap);

			String objectStatementsId = complexStatement.getObjectStatementId();
			if(objectStatementsId != null){
				setValues(complexStatement, StatementField.OBJECT_ANNOTATION_IDS, objectStatementsId, StatementField.ANNOTATION_ID, normalStatementsMap);
			}
			
			//Compute annotation ids for this complex statement
			complexStatement.computeAndSetAnnotationIds(annotationType);
		}
	}
	
	
	
	public static void setValues(
			Statement complexStatement, 
			StatementField fieldToSetInComplexStatement, 
			String referenceIds, 
			StatementField fieldTotakeFromSubject,
			Map<String, Statement> statementsDictionary) {
		
		Set<String> subjectIsoIds = new TreeSet<>();
		
		String[] referenceIdsArray = referenceIds.split(",");

		//Can be 1 or multiple subjects but most of the time it's just one
		for(String referenceId : referenceIdsArray){
			Statement referedStatement = statementsDictionary.get(referenceId); 
			
			if(referedStatement == null){
				throw new RuntimeException("Invalid statements. Can't find referenced statement " +  referenceId + ", referenced by statement " + complexStatement.getStatementId());
			}

			subjectIsoIds.add(referedStatement.getValue(fieldTotakeFromSubject));
		}

		// Setting subjects
		complexStatement.putValue(fieldToSetInComplexStatement, StringUtils.mkString(subjectIsoIds, "", ",", ""));
		
	}

}
