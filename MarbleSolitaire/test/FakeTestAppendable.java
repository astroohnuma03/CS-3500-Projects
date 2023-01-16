import java.io.IOException;

/**
 * This class represents a fake appendable class which throws IOExceptions on all its methods.
 */
public class FakeTestAppendable implements Appendable {
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Appendable Failed");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Appendable Failed");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Appendable Failed");
  }
}
