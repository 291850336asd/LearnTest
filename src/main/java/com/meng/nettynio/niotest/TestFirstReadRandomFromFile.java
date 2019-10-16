package com.meng.nettynio.niotest;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestFirstReadRandomFromFile {

//    Using a Buffer to read and write data typically follows this little 4-step process:
//
//    Write data into the Buffer
//    Call buffer.flip()
//    Read data out of the Buffer
//    Call buffer.clear() or buffer.compact()
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("data/niotest-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocateDirect(10).allocate(48);

        int bytesRead = inChannel.read(buf);
//        Writing Data to a Buffer
//        You can write data into a Buffer in two ways:
//        Write data from a Channel into a Buffer
//        Write data into the Buffer yourself, via the buffer's put() methods.

        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();
//            The flip() method switches a Buffer from writing mode to reading mode.
//            Calling flip() sets the position back to 0, and sets the limit to where position just was.
//            In other words, position now marks the reading position,
//            and limit marks how many bytes, chars etc.
//            were written into the buffer - the limit of how many bytes, chars etc. that can be read.
//             the buf.flip() call.
//             First you read into a Buffer.
//             Then you flip it. Then you read out of it.
//             I'll get into more detail about that in the next text about Buffer's.

            while(buf.hasRemaining()){
//                Reading Data from a Buffer
//                There are two ways you can read data from a Buffer.
//
//                        Read data from the buffer into a channel.
//                        Read data from the buffer yourself, using one of the get() methods.
//                        Here is an nettyexample of how you can read data from a buffer into a channel:
//
////read from buffer into channel.
//                int bytesWritten = inChannel.write(buf);
//                Here is an nettyexample that reads data from a Buffer using the get() method:
//
//                byte aByte = buf.get();
                System.out.print((char) buf.get());
            }

            buf.compact().clear();

            //When you write data into a buffer, the buffer keeps track of how much data you have written.
            // Once you need to read the data
            // you need to switch the buffer from writing mode into reading mode using the flip() method call.
            // In reading mode the buffer lets you read all the data written into the buffer.
            // Once you have read all the data,
            // you need to clear the buffer, to make it ready for writing again.
            // You can do this in two ways: By calling clear() or by calling compact().
            // The clear() method clears the whole buffer.
            // The compact() method only clears the data which you have already read.
            // Any unread data is moved to the beginning of the buffer,
            // and data will now be written into the buffer after the unread data.
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

}
