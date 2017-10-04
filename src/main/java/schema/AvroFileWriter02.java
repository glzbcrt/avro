package schema;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.commons.io.HexDump;
import org.apache.commons.io.output.ByteArrayOutputStream;
import schema.dto.MyRecord;

import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class AvroFileWriter02 {

    public static void main(String[] args) throws Exception {

        DatumWriter<MyRecord> dw = new SpecificDatumWriter<>();
        dw.setSchema(MyRecord.getClassSchema());

        ByteArrayOutputStream buf = new ByteArrayOutputStream();

        DataFileWriter<MyRecord> fw = new DataFileWriter<>(dw);

        fw.create(MyRecord.getClassSchema(), buf, MyConstants.MY_SYNC);

        fw.append(AvroUtils.createMyRecord());
        fw.append(AvroUtils.createMyRecord());
        fw.fSync();
        fw.append(AvroUtils.createMyRecord());

        // Will it work? No, it doesn't work because it adds a signature and schema CRC in the beginning.
        //fw.appendEncoded(schema.AvroUtils.createMyRecord().toByteBuffer());

        // Remove the first 10 bytes. They are the object header.
        byte[] arr = AvroUtils.createMyRecord().toByteBuffer().array();
        arr = Arrays.copyOfRange(arr, MyConstants.HEADER_SIZE, arr.length);

        // Append the raw bytes.
        fw.appendEncoded(ByteBuffer.wrap(arr));

        // Close the file. It will add a sync block.
        fw.close();

        System.out.println("\n> Dump the file content.");
        HexDump.dump(buf.toByteArray(), 0, System.out, 0);

        Files.write(Paths.get(MyConstants.AVRO_FILE_PATH), buf.toByteArray(), StandardOpenOption.TRUNCATE_EXISTING);
    }

}
