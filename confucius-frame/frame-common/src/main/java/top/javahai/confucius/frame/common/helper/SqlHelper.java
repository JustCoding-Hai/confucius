package top.javahai.confucius.frame.common.helper;

import java.util.List;
import java.util.stream.Collectors;

public class SqlHelper {

	public static final String INSERT_INTO = "insert into";
	public static final String UPDATE = "update";
	public static final String STR_BLANK_2 = "  ";
	public static final String STR_BRANK = " ";
	private static final String DELETE = "delete from";

	private SqlHelper() {
	}

	public static String stripNewLineAndSpace(String sql) {
		return sql.replaceAll("[\\t\\n\\r]", STR_BRANK).replaceAll("\\s+", STR_BRANK);
	}

	public static String getTableName(String sql) {

		if (StringHelper.isEmpty(sql)) {
			return "";
		}

		String formattedSql = stripNewLineAndSpace(sql);
		formattedSql = formattedSql.toLowerCase();
		if (formattedSql.startsWith(INSERT_INTO) || formattedSql.startsWith(DELETE)) {
			String[] strings = formattedSql.split(STR_BRANK);
			return strings[2];
		} else if (formattedSql.startsWith(UPDATE)) {
			String[] strings = formattedSql.split(STR_BRANK);
			return strings[1];
		}

		return null;
	}

	public static String generateInStatement(List<String> ids) {
		return "('" + ids.stream().collect(Collectors.joining("','")) + "')";
	}
}
