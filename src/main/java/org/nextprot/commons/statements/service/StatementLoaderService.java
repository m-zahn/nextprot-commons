package org.nextprot.commons.statements.service;

import java.util.Set;

import org.nextprot.commons.statements.Statement;
import org.nextprot.commons.statements.constants.NextProtSources;

public interface StatementLoaderService {

	void loadRawStatementsForSource(Set<Statement> statements, NextProtSources source);
	void loadStatementsMappedToIsoSpecAnnotationsForSource(Set<Statement> statements, NextProtSources source);
	void loadStatementsMappedToEntrySpecAnnotationsForSource(Set<Statement> statements, NextProtSources source);
	
	void deleteStatementsForSource(NextProtSources source);

}