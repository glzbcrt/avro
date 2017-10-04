package schema;

import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.JsonEncoder;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.commons.io.HexDump;
import schema.dto.MyRecord;

import java.io.ByteArrayOutputStream;

public class AvroFileWriter01 {

    public static void main(String[] args) throws Exception {

        MyRecord myRecord = AvroUtils.createMyRecord();

        DatumWriter<MyRecord> dw = new SpecificDatumWriter<>();
        dw.setSchema(myRecord.getSchema());

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        JsonEncoder json = EncoderFactory.get().jsonEncoder(myRecord.getSchema(), out);
        dw.write(myRecord, json);
        dw.write(myRecord, json);

        System.out.println("\n> Dump the two objects. They will be serialized in sequence as JSON.");
        HexDump.dump(out.toByteArray(), 0, System.out, 0);

        // Empty the buffer.
        out.reset();

        BinaryEncoder bin = EncoderFactory.get().directBinaryEncoder(out, null);
        dw.write(myRecord, bin);
        dw.write(myRecord, bin);

        System.out.println("\n> Dump the two objects. They will be serialized in sequence as BINARY.");
        HexDump.dump(out.toByteArray(), 0, System.out, 0);

    }

}
