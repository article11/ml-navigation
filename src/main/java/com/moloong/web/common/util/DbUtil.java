package com.moloong.web.common.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author:yuan-hai
 * date:2016/9/25 19:50
 * description:数据库工具类
 */
public class DbUtil {

    private static Logger logger = Logger.getLogger(DbUtil.class);

    private static String DEFAULT_DATASOURCE = "default";

    private static String configName = "dbconfig.properties";

    private static ConcurrentHashMap<String,DataSource> dataSourceMap = new ConcurrentHashMap<String, DataSource>();

    static {
        init();
    }

    private static void init() {
        Properties config = PropertiesUtil.getProperties(configName);
        if (config == null) {
            logger.error("DbUtils初始化失败,无法获取数据库配置.");
            return;
        }
        String dataSources = config.get("dataSource").toString();
        if(dataSources == null || "".equals(dataSources)){
            logger.error("DbUtils初始化失败,dataSource配置为空");
        }else{
            String[] dataSourceNameArray = dataSources.split(",");
            DruidDataSource druidDataSource = null;
            for (String dataSourceName : dataSourceNameArray) {
                try {
                    druidDataSource = new DruidDataSource();
                    druidDataSource.setUrl(config.get(dataSourceName+".jdbc.url").toString());
                    druidDataSource.setUsername(config.get(dataSourceName+".jdbc.username").toString());
                    druidDataSource.setPassword(config.get(dataSourceName+".jdbc.password").toString());
                    if (config.get(dataSourceName+".jdbc.initialSize") != null) {
                        druidDataSource.setInitialSize((Integer) config.get(dataSourceName+".jdbc.initialSize"));
                    } else {
                        druidDataSource.setInitialSize(1);
                    }
                    if (config.get(dataSourceName+".jdbc.minIdle") != null) {
                        druidDataSource.setMinIdle((Integer) config.get(dataSourceName+".jdbc.minIdle"));
                    } else {
                        druidDataSource.setMinIdle(1);
                    }
                    if (config.get(dataSourceName+".jdbc.maxActive") != null) {
                        druidDataSource.setMaxActive((Integer) config.get(dataSourceName+".jdbc.maxActive"));
                    } else {
                        druidDataSource.setMaxActive(5);
                    }
                    if (config.get(dataSourceName+".jdbc.maxWait") != null) {
                        druidDataSource.setMaxWait((Integer) config.get(dataSourceName+".jdbc.maxWait"));
                    } else {
                        druidDataSource.setMaxWait(60000);
                    }
                    druidDataSource.setTimeBetweenEvictionRunsMillis(30000);
                    druidDataSource.setValidationQuery("select 1");
                    druidDataSource.init();
                    if(!dataSourceMap.containsKey(DEFAULT_DATASOURCE)){
                        dataSourceMap.put(DEFAULT_DATASOURCE, druidDataSource);
                        logger.info("DbUtil默认数据源："+dataSourceName);
                    }
                    logger.info("数据源："+dataSourceName+",初始化成功");
                    dataSourceMap.put(dataSourceName,druidDataSource);
                } catch (Exception e) {
                    druidDataSource.close();
                    logger.error("dataSource:"+dataSourceName+",初始化失败", e);
                }
            }

        }
    }

    public static DataSource getDataSource() {
        return dataSourceMap.get(DEFAULT_DATASOURCE);
    }

    public static DataSource getDataSource(String dataSourceName){
        return dataSourceMap.get(dataSourceName);
    }

    private static QueryRunner getQueryRunner() {
        return new QueryRunner(getDataSource());
    }

    private static QueryRunner getQueryRunner(String dataSourceName){
        return new QueryRunner(getDataSource(dataSourceName));
    }

    public static Connection getConnection(String dataSourceName){
        Connection connection = null;
        DataSource dataSource = getDataSource(dataSourceName);
        if (dataSource != null) {
            try {
                connection = dataSource.getConnection();
            } catch (Exception e) {
                logger.error("DbUtil获取连接失败", e);
            }
        }
        return connection;
    }

    public static Connection getConnection() {
        Connection connection = null;
        DataSource dataSource = getDataSource();
        if (dataSource != null) {
            try {
                connection = dataSource.getConnection();
            } catch (Exception e) {
                logger.error("DbUtil获取连接失败", e);
            }
        }
        return connection;
    }

    /**
     * 动态取连接
     * @param server 数据库地址，port 端口号，gameDBName 数据库名,username 用户名，password 用户名
     * */
    public static Connection getConditionConnection(String server,int port,String gameDBName,String username,String password) {
        Connection connection = null;
        String url = "jdbc:mysql://"+server+":"+port+"/"+gameDBName+"?user="+username+"&password="+password+"&useUnicode=true&characterEncoding=UTF8";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            logger.error("加载驱动失败", e);
        }catch (SQLException e) {
            logger.error("获取连接失败", e);
        }
        return connection;
    }

    /**
     * 得到查询int值
     *
     * @param sql
     * @return
     */
    public static int getInt(String sql, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner();
            Integer value = runner.query(sql, new ScalarHandler<Integer>(), params);
            if (value==null){
                return 0;
            }
            return value;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * 得到查询记录的条数
     *
     * @param sql    必须为select count(*) from t_user的格式
     * @param params
     * @return
     */
    public static int getCount(String sql, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner();
            Integer value = runner.query(sql, new ScalarHandler<Integer>(), params);
            return value;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * 得到查询记录的条数
     * @param dataSourceName  数据源名字
     * @param sql  必须为select count(*) from t_user的格式
     * @param params
     * @return
     */
    public static int getCount(String dataSourceName,String sql,Object... params){
        try {
            QueryRunner runner = DbUtil.getQueryRunner(dataSourceName);
            Integer value = runner.query(sql, new ScalarHandler<Integer>(), params);
            return value;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * 得到查询记录的条数
     *
     * @param sql    必须为select count(*) from t_user的格式
     * @param params
     * @return
     */
    public static int getCount(Connection connection, String sql, Object... params) {
        try {
            QueryRunner runner = new QueryRunner();
            Integer value = runner.query(connection, sql, new ScalarHandler<Integer>(), params);
            return value;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }



    /**
     * 根据传入的sql，查询记录，以数组形式返回第一行记录。 注意：如果有多行记录，只会返回第一行，所以适用场景需要注意，可以使用根据主键来查询的场景
     *
     * @param sql
     * @param params
     * @return
     */
    public static Object[] getFirstRowArray(String sql, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner();
            return runner.query(sql, new ArrayHandler(), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据传入的sql，查询记录，以数组形式返回第一行记录。 注意：如果有多行记录，只会返回第一行，所以适用场景需要注意，可以使用根据主键来查询的场景
     *
     * @param dataSourceName
     * @param sql
     * @param params
     *
     * @return
     */
    public static Object[] getFirstRowArray(String dataSourceName,String sql, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner(dataSourceName);
            return runner.query(sql, new ArrayHandler(), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据传入的sql，查询记录，以数组形式返回第一行记录。 注意：如果有多行记录，只会返回第一行，所以适用场景需要注意，可以使用根据主键来查询的场景
     *
     * @param sql
     * @param params
     * @return
     */
    public static Object[] getFirstRowArray(Connection connection, String sql, Object... params) {
        try {
            QueryRunner runner = new QueryRunner();
            return runner.query(connection, sql, new ArrayHandler(), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    /**
     * 根据sql查询返回所有记录，以List数组形式返回
     *
     * @param sql
     * @param params
     * @return
     */
    public static List getListArray(String sql, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner();
            return runner.query(sql, new ArrayListHandler(), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据sql查询返回所有记录，以List数组形式返回
     *
     * @param sql
     * @param params
     * @return
     */
    public static List getListArray(String dataSourceName,String sql, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner(dataSourceName);
            return runner.query(sql, new ArrayListHandler(), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据sql查询返回所有记录，以List数组形式返回
     *
     * @param sql
     * @param params
     * @return
     */
    public static List getListArray(Connection connection, String sql, Object... params) {
        try {
            QueryRunner runner = new QueryRunner();
            return runner.query(connection, sql, new ArrayListHandler(), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据传入的sql，查询记录，以Map形式返回第一行记录。 注意：如果有多行记录，只会返回第一行，所以适用场景需要注意，可以使用根据主键来查询的场景
     *
     * @param sql
     * @param params
     * @return
     */
    public static Map getFirstRowMap(String sql, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner();
            return runner.query(sql, new MapHandler(), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据传入的sql，查询记录，以Map形式返回第一行记录。 注意：如果有多行记录，只会返回第一行，所以适用场景需要注意，可以使用根据主键来查询的场景
     *
     * @param sql
     * @param params
     * @return
     */
    public static Map getFirstRowMap(String dataSourceName,String sql, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner(dataSourceName);
            return runner.query(sql, new MapHandler(), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据传入的sql，查询记录，以Map形式返回第一行记录。 注意：如果有多行记录，只会返回第一行，所以适用场景需要注意，可以使用根据主键来查询的场景
     *
     * @param sql
     * @param params
     * @return
     */
    public static Map getFirstRowMap(Connection connection, String sql, Object... params) {
        try {
            QueryRunner runner = new QueryRunner();
            return runner.query(connection, sql, new MapHandler(), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据传入的sql查询所有记录，以List Map形式返回
     *
     * @param sql
     * @param params
     * @return
     */
    public static List getListMap(String sql, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner();
            return runner.query(sql, new MapListHandler(), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据传入的sql查询所有记录，以List Map形式返回
     *
     * @param sql
     * @param params
     * @return
     */
    public static List getListMap(String dataSourceName,String sql, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner(dataSourceName);
            return runner.query(sql, new MapListHandler(), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据传入的sql查询所有记录，以List Map形式返回
     *
     * @param sql
     * @param params
     * @return
     */
    public static List getListMap(Connection connection, String sql, Object... params) {
        try {
            QueryRunner runner = new QueryRunner();
            return runner.query(connection, sql, new MapListHandler(), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据sql和对象，查询结果并以对象形式返回
     *
     * @param sql
     * @param type
     * @param params
     * @return
     */
    public static <T> T getBean(String sql, Class<T> type, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner();
            return (T)runner.query(sql, new BeanHandler(type), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据sql和对象，查询结果并以对象形式返回
     *
     * @param sql
     * @param type
     * @param params
     * @return
     */
    public static <T> T getBean(String dataSourceName,String sql, Class<T> type, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner(dataSourceName);
            return (T)runner.query(sql, new BeanHandler(type), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据sql和对象，查询结果并以对象形式返回
     *
     * @param sql
     * @param type
     * @param params
     * @return
     */
    public static <T> T getBean(Connection connection, String sql, Class<T> type, Object... params) {
        try {
            QueryRunner runner = new QueryRunner();
            return (T)runner.query(connection, sql, new BeanHandler(type), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据sql查询list对象
     *
     * @param sql
     * @param type
     * @param params
     * @return
     */
    public static List getListBean(String sql, Class type, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner();
            return (List)runner.query(sql, new BeanListHandler(type), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据sql查询list对象
     *
     * @param sql
     * @param type
     * @param params
     * @return
     */
    public static List getListBean(String dataSourceName,String sql, Class type, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner(dataSourceName);
            return (List)runner.query(sql, new BeanListHandler(type), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据sql查询list对象
     *
     * @param sql
     * @param type
     * @param params
     * @return
     */
    public static List getListBean(Connection connection, String sql, Class type, Object... params) {
        try {
            QueryRunner runner = new QueryRunner();
            return (List)runner.query(connection, sql, new BeanListHandler(type), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 保存操作
     *
     * @param sql
     * @param params
     * @return
     */
    public static int save(String sql, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner();
            return runner.update(sql, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * 保存操作
     *
     * @param sql
     * @param params
     * @return
     */
    public static int save(String dataSourceName,String sql, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner(dataSourceName);
            return runner.update(sql, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * 保存操作
     *
     * @param sql
     * @param params
     * @return
     */
    public static int save(Connection connection, String sql, Object... params) {
        try {
            QueryRunner runner = new QueryRunner();
            return runner.update(connection, sql, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }


    /**
     * 保存操作
     *
     * @param sql
     * @param params
     * @return id
     */
    public static int saveOne(String sql,Object... params){
        try {
            QueryRunner runner = DbUtil.getQueryRunner();
            Integer value = runner.insert(sql, new ScalarHandler<Integer>(), params);
            return value;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }



    /**
     * 保存操作
     *
     * @param sql
     * @param params
     * @return id
     */
    public static Long saveOneByDataSource(String dataSourceName,String sql,Object... params){
        try {
            QueryRunner runner = DbUtil.getQueryRunner(dataSourceName);
            Long value = runner.insert(sql, new ScalarHandler<Long>(), params);
            return value;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0L;
    }


    /**
     * 更新操作
     *
     * @param sql
     * @param params
     * @return
     */
    public static int update(String sql, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner();
            return runner.update(sql, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * 更新操作
     *
     * @param sql
     * @param params
     * @return
     */
    public static int updateByDataSource(String dataSourceName,String sql, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner(dataSourceName);
            return runner.update(sql, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * 更新操作
     *
     * @param sql
     * @param params
     * @return
     */
    public static int update(Connection connection, String sql, Object... params) {
        try {
            QueryRunner runner = new QueryRunner();
            return runner.update(connection, sql, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * 删除操作
     *
     * @param sql
     * @param params
     * @return
     */
    public static int delete(String sql, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner();
            return runner.update(sql, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * 删除操作
     *
     * @param sql
     * @param params
     * @return
     */
    public static int delete(String dataSourceName,String sql, Object... params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner(dataSourceName);
            return runner.update(sql, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * 删除操作
     *
     * @param sql
     * @param params
     * @return
     */
    public static int delete(Connection connection, String sql, Object... params) {
        try {
            QueryRunner runner = new QueryRunner();
            return runner.update(connection, sql, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * 批量操作，包括批量保存、修改、删除
     *
     * @param sql
     * @param params
     * @return
     */
    public static int[] batch(String sql, Object[][] params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner();
            return runner.batch(sql, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 批量操作，包括批量保存、修改、删除
     *
     * @param sql
     * @param params
     * @return
     */
    public static int[] batch(String dataSourceName,String sql, Object[][] params) {
        try {
            QueryRunner runner = DbUtil.getQueryRunner(dataSourceName);
            return runner.batch(sql, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 批量操作，包括批量保存、修改、删除
     *
     * @param sql
     * @param params
     * @return
     */
    public static int[] batch(Connection connection, String sql, Object[][] params) {
        try {
            QueryRunner runner = new QueryRunner();
            return runner.batch(connection, sql, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 开启事务
     */
    public static void beginTransaction(Connection conn) {
        try {
            // 开启事务
            conn.setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 回滚事务
     */
    public static void rollback(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 提交事务
     */
    public static void commit(Connection conn) {
        try {
            conn.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
