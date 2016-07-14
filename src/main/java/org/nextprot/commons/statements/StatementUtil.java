package org.nextprot.commons.statements;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.nextprot.commons.algo.MD5Algo;

public class StatementUtil {

	public static String getEntryAnnotationId(RawStatement statement) {
		return getAnnotationId(statement, AnnotationType.ENTRY);
	}

	public static String getIsoAnnotationId(RawStatement statement) {
		return getAnnotationId(statement, AnnotationType.ISOFORM);
	}

	private static String getAnnotationId(RawStatement statement, AnnotationType type) {

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

}
