/*
 *
 * This code will iterate over all records in a flight Avro file and do a group by year counting the number of flights.
 *
 */
package schema;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;
import schema.dto.Flight;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FlightReader {

    private static Map<Integer, Integer> groupByYear = new HashMap<>();

    public static void main(String[] args) throws Exception {

        DatumReader<Flight> dr = new SpecificDatumReader<>();
        dr.setSchema(Flight.getClassSchema());

        DataFileReader<Flight> dfr = new DataFileReader<>(new File(MyConstants.AVRO_FILE_PATH), dr);

        int counter = 0;

        while (dfr.hasNext()) {
            Flight f = dfr.next();

            if (groupByYear.containsKey(f.getYear())) {
                groupByYear.put(f.getYear(), groupByYear.get(f.getYear()) + 1);
            } else {
                groupByYear.put(f.getYear(), 1);
            }

            counter++;
        }

        System.out.println(counter);

        for(Map.Entry<Integer, Integer> entry : groupByYear.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }

}
