package org.nextprot.commons.constants;

/**
 * Quality qualifier used for Annotation and Evidences
 * 
 * @author Daniel Teixeira http://github.com/ddtxra
 *
 * 
 */
public enum IsoTargetSpecificity {
	
	//See specifications Specifications https://calipho.isb-sib.ch/wiki/display/cal/Iso+Targeting+Specifications
	
	UNKNOWN, // La a manip a ete faite sur une isoforme et le resultat ne s applique qu a cette isoforme
	SPECIFIC, // La manip a ete faite sur une isoforme donnee mais le resultat s'applique a toutes les isoformes par propagation
	BY_DEFAULT // On ne sait pas sur quelle isoforme la manip a ete faite
	

}
