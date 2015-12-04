package hello;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class Controller {
	private static final String[] USER_NAME = { "iplat_assets", "iplat_transfer", "iplat_logistics", "iplat_crew",
			"iplat_station", "iplat_security", "iplat_equipmentmaintain", "iplat_produce", "iplat_text", "iplat" };

	private JdbcTemplate jdbcTemplate;
	Logger logger = LoggerFactory.getLogger("log");

	public Controller() {
	}

	@Autowired
	@Qualifier("dataSourceTest")
	private void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void execute() {
		for (int i = 0; i < USER_NAME.length; ++i) {
			List<String> tables = getNamesByUserName(USER_NAME[i].toUpperCase());
			List<String> sqls = createGrantSql(tables, USER_NAME[i].toUpperCase());
			for (String sql : sqls) {
				try {
					jdbcTemplate.execute(sql);
				} catch (Exception e) {
					logger.error(USER_NAME[i] + "-" + sql);
				}
				logger.info(sql);
			}
		}
	}

	private List<String> getNamesByUserName(String userNmae) {
		List<String> names = new ArrayList<String>();
		names.addAll(getTableNameByUserName(userNmae));
		names.addAll(getViewNameByUserName(userNmae));
		return names;
	}

	private List<String> getTableNameByUserName(String userName) {
		List<String> names = new ArrayList<String>();
		String sql = "select TABLE_NAME from all_tables t where t.owner = '" + userName + "'";
		names = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet arg0, int arg1) throws SQLException {
				return arg0.getString(1);
			}
		});
		return names;
	}

	private List<String> getViewNameByUserName(String userName) {
		List<String> names = new ArrayList<String>();
		String sql = "select VIEW_NAME from all_views t where t.owner = '" + userName + "'";
		names = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet arg0, int arg1) throws SQLException {
				return arg0.getString(1);
			}
		});
		return names;
	}

	private List<String> createGrantSql(List<String> tableNames, String userName) {
		List<String> sqls = new ArrayList<String>();
		for (String table : tableNames) {
			StringBuilder sb = new StringBuilder();
			sb.append("grant select ,insert ,update,delete on ");
			sb.append(userName);
			sb.append("." + table);
			sb.append(" to " + "iplat_scgl");
			sqls.add(sb.toString());
		}
		return sqls;
	}

}
