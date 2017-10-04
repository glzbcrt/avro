package schema;

import org.apache.avro.file.SeekableInput;
import org.apache.hadoop.fs.FSDataInputStream;

import java.io.IOException;

public class MySeekableInput implements SeekableInput {

    FSDataInputStream in;
    long size;

    public MySeekableInput(FSDataInputStream in, long size) {
        this.in = in;
        this.size = size;
    }

    @Override
    public void seek(long l) throws IOException {
        in.seek(l);
    }

    @Override
    public long tell() throws IOException {
        return in.getPos();
    }

    @Override
    public long length() throws IOException {
        return size;
    }

    @Override
    public int read(byte[] bytes, int i, int i1) throws IOException {
        return in.read(bytes, i, i1);
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

}
