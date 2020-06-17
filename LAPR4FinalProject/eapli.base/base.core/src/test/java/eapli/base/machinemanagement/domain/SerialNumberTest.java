package eapli.base.machinemanagement.domain;

import junit.framework.TestCase;
import org.junit.Test;

public class SerialNumberTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureSerialNumberCreationFailWhenCodeDoesNotMeetTheCorrectSize(){
        new SerialNumber("AS");
    }

    @Test
    public void ensureSerialNumberCodeMeetsTheCorrectSize(){
        new SerialNumber("AS244FG");
    }
}