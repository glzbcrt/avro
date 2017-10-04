package schema;

import schema.dto.MyEnum;
import schema.dto.MyFixed;
import schema.dto.MyRecord;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AvroUtils {

    public static MyRecord createMyRecord() {

        // A fixed type must have exactly the number of bytes defined in the schema.
        ByteBuffer buf = ByteBuffer.allocate(150);
        buf.put("Hello World".getBytes());

        MyFixed myFixed = new MyFixed(buf.array());

        // Creating a map.
        Map<CharSequence, CharSequence> myMap = new HashMap<>();
        myMap.put("user.meta1", "value1");
        myMap.put("user.meta2", "value2");
        myMap.put("user.meta3", "value3");

        // Creating an array.
        List<CharSequence> myArray = new ArrayList<>();
        myArray.add("element1");
        myArray.add("element2");
        myArray.add("element3");
        myArray.add("element4");

        MyRecord myRecord = MyRecord.newBuilder()
                .setMyLong(MyConstants.MY_LONG)
                .setMyArray(myArray)
                .setMyMap(myMap)
                .setMyFixed(myFixed)
                .setMyBoolean(true)
                .setMyUnion(null)
                .setMyString("Avro r0cks!")
                .setMyFloat(MyConstants.MY_FLOAT)
                .setMyDouble(MyConstants.MY_DOUBLE)
                .setMyInt(MyConstants.MY_INT)
                .setMyEnum(MyEnum.SYM1)
                .setMyBytes(ByteBuffer.wrap("Avro".getBytes()))
                .build();

        return myRecord;
    }

    public static byte[] convetIntToByteArrayInLE(int value){
        ByteBuffer little = ByteBuffer.allocate(4);

        little.order(ByteOrder.LITTLE_ENDIAN);
        little.putInt(value);

        return little.array();
    }

    public static byte[] convetLongToByteArrayInLE(long value){
        ByteBuffer little = ByteBuffer.allocate(8);

        little.order(ByteOrder.LITTLE_ENDIAN);
        little.putLong(value);

        return little.array();
    }

    public static int convertToZigZag(int value) {
        return (value >> 31) ^ (value << 1);
    }

}
