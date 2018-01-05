package org.nextprot.commons.statements;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class TargetIsoformSet extends TreeSet<TargetIsoformStatementPosition> {

	private static final long serialVersionUID = -8387267990628898963L;

	public TargetIsoformSet() {
		super();
	}
	
	public TargetIsoformSet(Set<TargetIsoformStatementPosition> targets) {
		super(targets);
	}

	public static TargetIsoformSet deSerializeFromJsonString(String tistatementAsJson) {

		TargetIsoformSet tis = new TargetIsoformSet();
		JsonArray array = Json.parse(tistatementAsJson).asArray();

		array.forEach(a -> {
			JsonObject jo = a.asObject();

			String name = verifyValue(jo.getString("name", "NULL"));
			String isoformAccession = verifyValue(jo.getString("isoformAccession", "NULL"));
			String specificity = verifyValue(jo.getString("specificity", "NULL"));

			Integer begin = verifyValue(jo.getInt("begin", -1));
			Integer end = verifyValue(jo.getInt("end", -1));

			tis.add(new TargetIsoformStatementPosition(isoformAccession, begin, end, specificity, name));

		});

		return tis;

	}

	private static Integer verifyValue(int value){
		if(value == -1) return null;
		else return value;
	}

	private static String verifyValue(String value){
		if(value.equals("NULL")) return null;
		else return value;
	}

	private static void addIfPresent(JsonObject jo, String name, Integer value){
		if(value != null)
			jo.add(name, value);
	}

	private static void addIfPresent(JsonObject jo, String name, String value){
		if(value != null)
			jo.add(name, value);
	}

	public String serializeToJsonString() {

		JsonArray array = Json.array();
		Iterator<TargetIsoformStatementPosition> it = this.iterator();
		while(it.hasNext()){
			TargetIsoformStatementPosition tisp = it.next();
			JsonObject jo = Json.object();

			addIfPresent(jo, "name", tisp.getName());
			addIfPresent(jo, "isoformAccession", tisp.getIsoformAccession());
			addIfPresent(jo, "specificity", tisp.getSpecificity());

			addIfPresent(jo, "begin", tisp.getBegin());
			addIfPresent(jo, "end", tisp.getEnd());

			array.add(jo);

		}

		return array.toString();

	}


}
