package school.lichangxin.druid;

import school.lichangxin.utils.JDBCUtils;

import java.sql.SQLException;

public class DuridTest2 {
    public static void main(String[] args) {
        try {
            JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
