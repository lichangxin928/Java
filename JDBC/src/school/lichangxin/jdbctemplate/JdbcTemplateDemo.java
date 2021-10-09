package school.lichangxin.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import school.lichangxin.utils.JDBCUtils;

public class JdbcTemplateDemo {
    public static void main(String[] args) {
        // 导入 jar 包
        // 创建 jdbcTemplate 对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update user set psw = 1234 where name = ?";
        int count = jdbcTemplate.update(sql,"111");
        System.out.println(count);
    }
}
