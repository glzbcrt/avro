package schema;

public class MyConstants {

    public final static String AVRO_FILE_PATH = "c:\\temp\\out.avro";
    public final static int HEADER_SIZE = 10;
    public final static byte[] MY_SYNC = new byte[] { (byte)0xCA, (byte)0xFE, (byte)0xBA, (byte)0xBE, (byte)0xCA, (byte)0xFE, (byte)0xBA, (byte)0xBE, (byte)0xCA, (byte)0xFE, (byte)0xBA, (byte)0xBE, (byte)0xCA, (byte)0xFE, (byte)0xBA, (byte)0xBE };

    public final static long MY_LONG = 451l;
    public final static int MY_INT = 314;
    public final static float MY_FLOAT = 15.5f;
    public final static double MY_DOUBLE = 20.4d;

    public final static String MYSQL_CONNECTION_STRING = "jdbc:mysql://localhost:3306/flight?useCursorFetch=true";
    public final static String MYSQL_CONNECTION_USER = "root";
    public final static String MYSQL_CONNECTION_PWD = "my@123";

    public final static String HDFS_NAMENODE = "hdfs://hadoop01";
    public final static String HDFS_PATH = "/datasets/flight/avro/flight%s%s.avro";

    public final static boolean HDFS_SPLIT_BY_YEAR = false;
    public final static String HDFS_CODEC = "snappy";

}
