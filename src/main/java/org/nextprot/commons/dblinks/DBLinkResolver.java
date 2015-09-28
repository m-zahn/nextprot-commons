package org.nextprot.commons.dblinks;

import java.util.Map;

/**
 * Resolve DataBase links with preferred link
 * 
 * @author Calipho Team ndu@isb-sib.ch
 *
 */
public class DBLinkResolver {

	public static String resolveLinkTarget(String url, String accession, String databaseName, Map<String, String> properties) {

		// Deal 1rst with special cases
		String primaryId = accession;
		String db = databaseName;

		// link to a web page
		if (db.equals("WEBINFO")) {
			return accession;
		}

		String templateURL = url;
		if (templateURL.isEmpty() && !PreferredDBLink.dbHasPreferredLink(db)){
			return "";
		}

		if (!templateURL.startsWith("http")) {
			templateURL = "http://" + templateURL;
		}
		
		if (db.equals("EMBL") && primaryId.indexOf('.') > 0) { 		// There is a valid cds: use emblcds

			primaryId = primaryId.split("\\.")[0];
			templateURL = PreferredDBLink.EMBL.getLink();
			
		}else  if (db.equals("Ensembl")) {
			// organism always human: hardcode it
			if (primaryId.startsWith("ENST")) {
				templateURL = PreferredDBLink.ENSEMBL_TRANSCRIPT.getLink();
			} else if (primaryId.startsWith("ENSP")) {
				templateURL = PreferredDBLink.ENSEMBL_PROTEIN.getLink();
			} else if (primaryId.startsWith("ENSG")) {
				templateURL = PreferredDBLink.ENSEMBL_GENE.getLink();
			}
		}else  if (db.equals("Cosmic")) {
			if (primaryId.startsWith("COSM")) {
				templateURL = PreferredDBLink.COSMIC_MUTATION.getLink();
				primaryId = primaryId.replaceFirst("COSM", "");
			} else if (primaryId.startsWith("COSS")) {
				templateURL = PreferredDBLink.COSMIC_SAMPLE.getLink();
				primaryId = primaryId.replaceFirst("COSS", "");
			} else {
				templateURL = PreferredDBLink.COSMIC_GENE.getLink();
			}
		} else if (db.equals("Clinvar")) {
			if (primaryId.matches("RCV\\d+")) {
				templateURL = PreferredDBLink.CLINVAR_MUTATION.getLink();
			} else {
				templateURL = PreferredDBLink.CLINVAR_GENE.getLink();
			}
		}else  if (db.equals("PIR")) {
			primaryId = properties.get("entry name");
		}else  if (db.equals("GermOnline")) {
			templateURL = PreferredDBLink.GERMONLINE.getLink();
		}else  if (db.equals("Genevestigator")) {
			templateURL = PreferredDBLink.GENEVESTIGATOR.getLink();
		}else  if (db.equals("HPA")) {
			if (primaryId.startsWith("ENSG")) {
				if (primaryId.endsWith("subcellular")) {
					templateURL = PreferredDBLink.HPA_SUBCELL.getLink();
				} else {
					templateURL = PreferredDBLink.HPA_GENE.getLink();
				}
			} else {
				templateURL = PreferredDBLink.HPA_ANTIBODY.getLink();
			}
		}else if (db.equals("Genevisible")) {
			// organism always human: hardcode it
			templateURL = templateURL.replaceFirst("%s1", primaryId);
			templateURL = templateURL.replaceFirst("%s2", "HS");
			return templateURL;
		}else  if (db.equals("UniGene")) {
			// organism always human: hardcode it
			templateURL = templateURL.replaceFirst("%s1", "Hs");
			templateURL = templateURL.replaceFirst("%s2", primaryId.split("\\.")[1]);
			return templateURL;
		}else  if (db.equals("UCSC")) {
			// organism always human: hardcode it
			templateURL = templateURL.replaceFirst("%s2", "human");
			templateURL = templateURL.replaceFirst("%s1", primaryId);
			return templateURL;
		} else  if (db.equals("IntAct")) {
			if (accession.startsWith("EBI")) {
				templateURL = PreferredDBLink.INTACT_BINARY.getLink();
			}
		}else  if (db.equals("PROSITE")) {
			// Overwrite native dbxref raw link w a more user-friendly one
			templateURL = PreferredDBLink.PROSITE.getLink();
		}else  if (db.equals("HSSP")) {
			String pdbAccession = properties.get("PDB accession");
			if (pdbAccession != null) {
				primaryId = pdbAccession.toLowerCase();
			} else {
				primaryId = accession.toLowerCase();
			}
		}else  if (db.equals("Bgee")) {
			if (accession.contains("ENSG"))
				templateURL = templateURL.replace("uniprot_id=", "page=expression&action=data&");
		}else  if (db.equals("PeptideAtlas")) {
			// protein URL
			if (!accession.startsWith("PAp")) {
				templateURL = "https://db.systemsbiology.net/sbeams/cgi/PeptideAtlas/GetProtein?protein_name=%s;organism_name=Human;action=GO";
			}
			// peptide URL
			else {
				templateURL = "https://db.systemsbiology.net/sbeams/cgi/PeptideAtlas/GetPeptide?searchWithinThis=Peptide+Name&searchForThis=%s;organism_name=Human";
			}
		}else  if (db.equals("SRMAtlas")) {
			primaryId = properties.get("sequence");
		}else  if (db.equals("PDB")) {
			// Overwrite native dbxref raw link w a more user-friendly one
			templateURL = PreferredDBLink.PDB.getLink();
		}else  if (db.equals("WEBINFO")) {
			templateURL = accession;
			if (!templateURL.startsWith("http")) {
				templateURL = "http://" + templateURL;
			}
			return templateURL;
		}

		// Db_URL:
		// http://www2.idac.tohoku.ac.jp/dep/ccr/TKGdate/TKGvo10%n/%s.html
		// Note: n% is the second digit of the cell line AC and %s is the cell
		// line AC without the 'TKG'
		// Example: for "DR   TKG; TKG 0377": n%=3 s%=0377
		else if (db.equals("TKG")) {
			templateURL = templateURL.replaceAll("%n", String.valueOf(primaryId.charAt(1)));
		}

		// Db_URL:
		// https://www.aidsreagent.org/reagentdetail.cfm?t=cell_lines&id=%s
		// Note: %s is the value after the dash in the DR line.
		// Example: for "DR   NIH-ARP; 11411-223": s%=223
		else if (db.equals("NIH-ARP")) {
			primaryId = primaryId.replaceAll("^.+-", "");
		}

		// Db_URL:
		// http://www.cghtmd.jp/CGHDatabase/mapViewer?hid=%s&aid=%t&lang=en
		// Note: %s and %t are respectively the values before and after the dash
		// in the DR line.
		// Example: for "DR   CGH-DB; 9029-4": s%=9029, t%=4
		else if (db.equals("CGH-DB")) {
			templateURL = templateURL.replaceAll("%t", primaryId.replaceAll("^.+-", ""));
			primaryId = primaryId.replaceAll("-.+$", "");
		}

		else if (db.equals("IFO") || db.equals("JCRB")) {
			primaryId = primaryId.toLowerCase();
		}

		else if (url.contains("purl.obolibrary.org/obo")) {
			primaryId = primaryId.replaceFirst(":", "_");
		}

		// general case
		if (templateURL.matches(".*%s\\b.*")) {
			return templateURL.replaceAll("\"", "").replaceAll("%s", primaryId);
		}

		// failed: return home url for db
		return url;
	}
}
