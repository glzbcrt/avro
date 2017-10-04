package schema;

import org.apache.avro.SchemaNormalization;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.HexDump;
import schema.dto.MyRecord;

import java.nio.ByteBuffer;

public class AvroBase {

    public static void main(String[] args) throws Exception {

        // This is how a float is encoded to be used by Avro.
        System.out.println("\n> 3.5f encoded in Avro:");
        byte[] number = AvroUtils.convetIntToByteArrayInLE(Float.floatToIntBits(MyConstants.MY_FLOAT));
        System.out.println(String.format("0x%s", new String(Hex.encodeHex(number))));

        // This is how a double is encoded to be used by Avro.
        System.out.println("\n> 20.5d encoded in Avro:");
        number = AvroUtils.convetLongToByteArrayInLE(Double.doubleToLongBits(MyConstants.MY_DOUBLE));
        System.out.println(String.format("0x%s", new String(Hex.encodeHex(number))));

        // Create a sample record to use in our examples.
        MyRecord myRecord = AvroUtils.createMyRecord();

        // We can print using the field position in the schema.
        System.out.println("\n> Getting fields using their position:");
        System.out.println(myRecord.get(0));
        System.out.println(myRecord.get(1));

        // Using the field name in the schema.
        System.out.println("\n> Getting fields using their name:");
        System.out.println(myRecord.get("MyLong"));
        System.out.println(myRecord.get("MyString"));

        // Or using the Java property.
        System.out.println("\n> Getting fields using their Java property:");
        System.out.println(myRecord.getMyLong());
        System.out.println(myRecord.getMyString());

        // This way we can extract the schema. Note that this string is not exactly the same as the one defined in the schema file.
        System.out.println("\n> Getting the field schema:");
        System.out.println(myRecord.getSchema());

        // Extract the byte representation of this single object. There is no schema in this format.
        System.out.println("\n> Dumping the bytes in the single object format:");
        ByteBuffer buffer = myRecord.toByteBuffer();
        HexDump.dump(buffer.array(), 0, System.out, 0);

        // Create a MyRecord from the previous byte buffer and dump some info.
        System.out.println("\n> Getting the object from the single object bytes:");
        MyRecord myRecord2 = MyRecord.fromByteBuffer(buffer);
        System.out.println(myRecord2.getMyLong());
        System.out.println(myRecord2.getMyString());

        // This is how the schema CRC is calculated.
        System.out.println("\n> Schema CRC in LE");
        long l = SchemaNormalization.parsingFingerprint64(myRecord.getSchema());
        HexDump.dump(AvroUtils.convetLongToByteArrayInLE(l), 0, System.out, 0);

        System.out.println("\n> 20 in ZigZag");
        int i = AvroUtils.convertToZigZag(20);
        HexDump.dump(AvroUtils.convetIntToByteArrayInLE(i), 0, System.out, 0);

        System.out.println("\n> 30 in ZigZag");
        i = AvroUtils.convertToZigZag(30);
        HexDump.dump(AvroUtils.convetIntToByteArrayInLE(i), 0, System.out, 0);

        System.out.println("\n> 23 in ZigZag");
        i = AvroUtils.convertToZigZag(23);
        HexDump.dump(AvroUtils.convetIntToByteArrayInLE(i), 0, System.out, 0);

    }

}
