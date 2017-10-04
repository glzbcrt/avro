package schema;

import org.apache.avro.LogicalTypes;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.commons.io.HexDump;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class AvroSchema {

    public static void main(String[] args) throws Exception {

        // One way of creating a schema programmatically is using a schema builder.

        // A logical type is a type which does not exist in Avro natively. It is created by the language reading it.
        // Here I am creating a decimal type.
        Schema myDecimal  = LogicalTypes.decimal(20, 2).addToSchema(Schema.create(Schema.Type.BYTES));

        // And here a timestamp type.
        Schema timestampMilliType = LogicalTypes.timestampMillis().addToSchema(Schema.create(Schema.Type.LONG));

        // Now, using the builder, build the schema.
        Schema schemaWithTimestamp = SchemaBuilder
                .record("MyRecord").namespace("schemabuilder")
                .fields()
                .name("timestamp_with_logical_type").type(timestampMilliType).noDefault()
                .name("timestamp_no_logical_type").type().longType().noDefault()
                .name("decimal").type(myDecimal).noDefault()
                .endRecord();

        // Dump the JSON of this schema.
        System.out.println(schemaWithTimestamp.toString(true));

        // Another way to create a schema is using the objects without a builder.
        List<Schema.Field> fields = new ArrayList<>();
        fields.add( new Schema.Field("id", Schema.create(Schema.Type.LONG), null, null));
        fields.add( new Schema.Field("name", Schema.create(Schema.Type.STRING), null, null));
        fields.add( new Schema.Field("image", Schema.create(Schema.Type.BYTES), null, null));
        fields.add( new Schema.Field("files", Schema.createArray(Schema.create(Schema.Type.STRING)), null, null));

        Schema myRecord = Schema.createRecord("customer", "", "", false, fields);
        System.out.println(myRecord.toString(true));

        // Now let's create a record.
        List<Object> files = new ArrayList<>();
        files.add("file1");
        files.add("file2");
        files.add("file3");

        // To create records using this kind of schema we must use the GenericRecord class.
        GenericRecord r = new GenericData.Record(myRecord);
        r.put("id", 20l);
        r.put(1, "Satoshi Nakamoto");
        r.put("image", ByteBuffer.wrap("Satoshi Nakamoto".getBytes(Charset.forName("UTF-32LE-BOM"))));
        r.put(3, files);

        DatumWriter<GenericRecord> dw = new GenericDatumWriter<>();
        dw.setSchema(myRecord);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        DataFileWriter<GenericRecord> dfw = new DataFileWriter<>(dw);
        dfw.create(myRecord, out);
        dfw.append(r);
        dfw.close();

        HexDump.dump(out.toByteArray(), 0, System.out, 0);
    }

}
