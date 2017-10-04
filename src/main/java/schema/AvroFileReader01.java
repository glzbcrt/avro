package schema;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.io.DatumReader;
import schema.dto.MyRecord;

import java.io.File;

public class AvroFileReader01 {

    public static void main(String[] args) throws Exception {

        DatumReader<MyRecord> dr = new GenericDatumReader<>();

        DataFileReader<MyRecord> dfr = new DataFileReader(new File(MyConstants.AVRO_FILE_PATH), dr);

        System.out.println(dfr.tell());

        System.out.println(dfr.previousSync());

        dfr.sync(1533);

        System.out.println(dfr.tell());

        for(MyRecord myRecord : dfr) {
            System.out.println(myRecord.getMyLong());
        }

    }

}
