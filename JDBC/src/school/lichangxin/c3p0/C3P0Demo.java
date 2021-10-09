package school.lichangxin.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

public class C3P0Demo {
    public static void main(String[] args) {
        Connection conn = null;
        try{
            //创建数据库连接池对象
            DataSource ds = new ComboPooledDataSource();
            //获取数据库连接对象
            conn = ds.getConnection();
            System.out.println(conn);
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
