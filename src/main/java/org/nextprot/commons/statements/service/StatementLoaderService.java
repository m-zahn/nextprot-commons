package org.nextprot.commons.statements.service;

import java.util.Set;

import org.nextprot.commons.statements.RawStatement;

public interface StatementLoaderService {

	void load(Set<RawStatement> statements);

	void deleteAll();

}