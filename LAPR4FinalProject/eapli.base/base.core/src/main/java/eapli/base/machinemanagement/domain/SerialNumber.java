package eapli.base.machinemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class SerialNumber implements ValueObject {

    private final static Integer MINIMUM_LENGTH = 6;
    private final static Integer MAXIMUM_LENGTH = 20;

    private String serialNumber;

    protected SerialNumber() {
    }

    public SerialNumber(String serialNumber) {
        this.serialNumber = ensureSerialNumberIsBetweenSixAndTwenty(serialNumber);
    }

    private String ensureSerialNumberIsBetweenSixAndTwenty(String serialNumber) {
        if (serialNumber.length() < MINIMUM_LENGTH || serialNumber.length() > MAXIMUM_LENGTH) {
            throw new IllegalArgumentException(serialNumber + "is not a valid Serial Number");
        }
        return serialNumber;
    }

    public String serialNumber() {
        return this.serialNumber;
    }
}
