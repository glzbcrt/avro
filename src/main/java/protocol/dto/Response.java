/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package protocol.dto;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Response extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 1562651385087495301L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Response\",\"namespace\":\"protocol.dto\",\"fields\":[{\"name\":\"nameToReturn\",\"type\":[\"string\",\"null\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Response> ENCODER =
      new BinaryMessageEncoder<Response>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Response> DECODER =
      new BinaryMessageDecoder<Response>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Response> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Response> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Response>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Response to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Response from a ByteBuffer. */
  public static Response fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence nameToReturn;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Response() {}

  /**
   * All-args constructor.
   * @param nameToReturn The new value for nameToReturn
   */
  public Response(java.lang.CharSequence nameToReturn) {
    this.nameToReturn = nameToReturn;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return nameToReturn;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: nameToReturn = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'nameToReturn' field.
   * @return The value of the 'nameToReturn' field.
   */
  public java.lang.CharSequence getNameToReturn() {
    return nameToReturn;
  }

  /**
   * Sets the value of the 'nameToReturn' field.
   * @param value the value to set.
   */
  public void setNameToReturn(java.lang.CharSequence value) {
    this.nameToReturn = value;
  }

  /**
   * Creates a new Response RecordBuilder.
   * @return A new Response RecordBuilder
   */
  public static protocol.dto.Response.Builder newBuilder() {
    return new protocol.dto.Response.Builder();
  }

  /**
   * Creates a new Response RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Response RecordBuilder
   */
  public static protocol.dto.Response.Builder newBuilder(protocol.dto.Response.Builder other) {
    return new protocol.dto.Response.Builder(other);
  }

  /**
   * Creates a new Response RecordBuilder by copying an existing Response instance.
   * @param other The existing instance to copy.
   * @return A new Response RecordBuilder
   */
  public static protocol.dto.Response.Builder newBuilder(protocol.dto.Response other) {
    return new protocol.dto.Response.Builder(other);
  }

  /**
   * RecordBuilder for Response instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Response>
    implements org.apache.avro.data.RecordBuilder<Response> {

    private java.lang.CharSequence nameToReturn;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(protocol.dto.Response.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.nameToReturn)) {
        this.nameToReturn = data().deepCopy(fields()[0].schema(), other.nameToReturn);
        fieldSetFlags()[0] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Response instance
     * @param other The existing instance to copy.
     */
    private Builder(protocol.dto.Response other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.nameToReturn)) {
        this.nameToReturn = data().deepCopy(fields()[0].schema(), other.nameToReturn);
        fieldSetFlags()[0] = true;
      }
    }

    /**
      * Gets the value of the 'nameToReturn' field.
      * @return The value.
      */
    public java.lang.CharSequence getNameToReturn() {
      return nameToReturn;
    }

    /**
      * Sets the value of the 'nameToReturn' field.
      * @param value The value of 'nameToReturn'.
      * @return This builder.
      */
    public protocol.dto.Response.Builder setNameToReturn(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.nameToReturn = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'nameToReturn' field has been set.
      * @return True if the 'nameToReturn' field has been set, false otherwise.
      */
    public boolean hasNameToReturn() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'nameToReturn' field.
      * @return This builder.
      */
    public protocol.dto.Response.Builder clearNameToReturn() {
      nameToReturn = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response build() {
      try {
        Response record = new Response();
        record.nameToReturn = fieldSetFlags()[0] ? this.nameToReturn : (java.lang.CharSequence) defaultValue(fields()[0]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Response>
    WRITER$ = (org.apache.avro.io.DatumWriter<Response>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Response>
    READER$ = (org.apache.avro.io.DatumReader<Response>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
