/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package schema.dto;
@SuppressWarnings("all")
@org.apache.avro.specific.FixedSize(150)
@org.apache.avro.specific.AvroGenerated
public class MyFixed extends org.apache.avro.specific.SpecificFixed {
  private static final long serialVersionUID = 4670852106673953296L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"fixed\",\"name\":\"MyFixed\",\"namespace\":\"schema.dto\",\"size\":150}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  /** Creates a new MyFixed */
  public MyFixed() {
    super();
  }

  /**
   * Creates a new MyFixed with the given bytes.
   * @param bytes The bytes to create the new MyFixed.
   */
  public MyFixed(byte[] bytes) {
    super(bytes);
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter<MyFixed>(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, org.apache.avro.specific.SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader<MyFixed>(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, org.apache.avro.specific.SpecificData.getDecoder(in));
  }

}
