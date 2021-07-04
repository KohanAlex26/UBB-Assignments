package domain.entities;

import java.io.*;

public class Message {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static final String OK = "ok";
    public static final String ERROR = "error";

    public static final String HOST = "localhost" ;
    public static final int PORT = 8784;

    private String header;
    private String body;

    public Message() {
    }

    public Message(String header, String body) {
        this.header = header;
        this.body = body;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "domain.entities.Message{" +
                "header='" + header + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

//    public void readFrom(InputStream is) throws IOException {
//        var br = new BufferedReader(new InputStreamReader(is));
//        header = br.readLine();
//        body = br.readLine();
//    }
public void readFrom(InputStream is) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    String bufferMessage = "";
    do {
        //System.out.println(bufferMessage);
        bufferMessage += br.readLine();
        bufferMessage += System.lineSeparator();
    } while (br.ready());
    bufferMessage = bufferMessage.substring(0, bufferMessage.length() - System.lineSeparator().length());
    String[] inputParsed = bufferMessage.split(System.lineSeparator(), 2);

    header = inputParsed[0];
    if (inputParsed.length > 1) {
        body = inputParsed[1];
    }
}

    public void writeTo(OutputStream os) throws IOException {
        os.write((header + LINE_SEPARATOR + body + LINE_SEPARATOR).getBytes());
    }
}
