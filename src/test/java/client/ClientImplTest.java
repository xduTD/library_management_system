package client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientImplTest {
    @Test
    public void testSplit() {
        String[] s = "12345".split(" ", 2);
        Assertions.assertEquals(2, s.length);
    }
}
