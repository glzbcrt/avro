/*
 * This is a sample application I wrote to export data from MySQL to HDFS as Avro.
 *
 * I imported the dataset (CSV files) in a MySQL table. It had more than 120 million rows.
 * Then I created a schema (flight.avsc) to represent a row of this table.
 * Finally using the MySQL JDBC driver I created this application to export the rows of a specific year in a Avro file.
 * The program can be parametrized to create one file for each year or create one big file containing all data.
 *
 * I know I could do this import using sqoop, but I wanted to have the full experience of this process.
 */

package schema;

import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import schema.dto.Flight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FlightWriter {

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.jdbc.Driver");

        System.setProperty("HADOOP_USER_NAME", "hadoop");

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", MyConstants.HDFS_NAMENODE);

        // When we have a few datanodes and we do appends, then you should disable this feature.
        conf.set("dfs.client.block.write.replace-datanode-on-failure.enable", "false");

        FileSystem fs = FileSystem.get(conf);

        FSDataOutputStream out;
        MySeekableInput in = null;

        for(int year = 1987; year <= 2008; year++) {

            String yearId = MyConstants.HDFS_SPLIT_BY_YEAR ? "_" + year : "";
            String codecId = MyConstants.HDFS_CODEC.equals("null") ? "" : "_" + MyConstants.HDFS_CODEC;

            Path p = new Path(String.format(MyConstants.HDFS_PATH, yearId, codecId));

            if (fs.exists(p)) {
                out = fs.append(p);

                long len = fs.getContentSummary(p).getLength();
                in = new MySeekableInput(fs.open(p), len);

            } else {
                out = fs.create(p);
            }

            saveYear(Integer.toString(year), out, in);
            out.close();

            if (in != null) {
                in.close();
            }
        }
    }

    public static long saveYear(String year, FSDataOutputStream out, MySeekableInput in) throws Exception {
        System.out.printf(year + ": ");

        long counter = 0;
        long start = System.currentTimeMillis();

        DatumWriter<Flight> userDatumWriter = new SpecificDatumWriter<Flight>(Flight.class);

        DataFileWriter<Flight> dataFileWriter = new DataFileWriter<Flight>(userDatumWriter);
        dataFileWriter.setCodec(CodecFactory.fromString(MyConstants.HDFS_CODEC));

        if (in == null) {
            dataFileWriter.create(Flight.getClassSchema(), out, MyConstants.MY_SYNC);
        } else {
            dataFileWriter.appendTo(in, out);
        }

        Connection conn = DriverManager.getConnection(MyConstants.MYSQL_CONNECTION_STRING, MyConstants.MYSQL_CONNECTION_USER, MyConstants.MYSQL_CONNECTION_PWD);
        conn.setAutoCommit(false);

        // Be careful when creating this Statement object. The way it is created below a server side cursor will be used.
        // We need to do this way to not cause an OutOfMemoryException when dealing with large tables.
        Statement stmt = conn.createStatement(
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY
        );

        stmt.setFetchSize(50000);

        ResultSet rs = stmt.executeQuery(String.format("select * from flight where year = '%s'", year));

        while (rs.next()) {
            Flight flight = new Flight();

            flight.setYear(getIntValue(rs.getObject(1)));
            flight.setMonth(getIntValue(rs.getObject(2)));
            flight.setDayOfMonth(getIntValue(rs.getObject(3)));
            flight.setDayOfWeek(getIntValue(rs.getObject(4)));
            flight.setDepTime(getIntValue(rs.getObject(5)));
            flight.setCrsDepTime(getIntValue(rs.getObject(6)));
            flight.setArrTime(getIntValue(rs.getObject(7)));
            flight.setCsArrTime(getIntValue(rs.getObject(8)));
            flight.setUniqueCarrier(rs.getString(9));
            flight.setFlightNum(getIntValue(rs.getObject(10)));
            flight.setTailNum(rs.getString(11));
            flight.setActualElapsedTime(getIntValue(rs.getObject(12)));
            flight.setCrsElapsedTime(getIntValue(rs.getObject(13)));
            flight.setAirTime(rs.getString(14));
            flight.setArrDelay(rs.getString(15));
            flight.setDepDelay(rs.getString(16));
            flight.setOrigin(rs.getString(17));
            flight.setDest(rs.getString(18));
            flight.setDistance(getIntValue(rs.getObject(19)));
            flight.setTaxiIn(rs.getString(20));
            flight.setTaxiOut(rs.getString(21));
            flight.setCancelled(getIntValue(rs.getObject(22)));
            flight.setCancellationCode(rs.getString(23));
            flight.setDiverted(getIntValue(rs.getObject(24)));
            flight.setCarrierDelay(rs.getString(25));
            flight.setWeatherDelay(rs.getString(26));
            flight.setNasDelay(rs.getString(27));
            flight.setSecurityDelay(rs.getString(28));
            flight.setLateAircraftDelay(rs.getString(29));

            dataFileWriter.append(flight);

            counter++;
        }

        System.out.println(Long.toString(counter) + " records in " + (System.currentTimeMillis() - start)  + " msecs");
        dataFileWriter.close();
        conn.close();

        return counter;
    }

    private static int getIntValue(Object obj) {
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception ex) {
            return 0;
        }
    }

}
