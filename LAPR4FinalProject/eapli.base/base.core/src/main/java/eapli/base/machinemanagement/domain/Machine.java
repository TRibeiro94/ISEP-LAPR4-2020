package eapli.base.machinemanagement.domain;

import eapli.base.productionlinemanagement.domain.ProductionLine;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The information about a machine
 */
@Entity
public class Machine implements AggregateRoot<String> {

    @Id
    private String code;

    private String brand;

    private String model;

    private String protocolCode;

    private Date installationDate;

    private String description;

    @Column(unique = true)
    private SerialNumber serialNumber;

    @Column(length = 10000)
    private MachinePosition machinePos;

    @ElementCollection
    private Set<ConfigurationFile> configFile = new HashSet<>();

    protected Machine() {
        //for ORM
    }

    public Machine(String machineCode, String serialNum, String protocol, String desc, String brand, String model, Date installDate, ProductionLine prodLine, Integer position) {
        Preconditions.noneNull(machineCode, serialNum, protocol, desc, brand, model, installDate);
        this.code = machineCode;
        this.brand = brand;
        this.description = desc;
        this.serialNumber = new SerialNumber(serialNum);
        this.protocolCode = protocol;
        this.model = model;
        this.installationDate = installDate;
        this.machinePos = new MachinePosition(prodLine, position);
    }

    /**
     * This method sets the protocolCode of the instance
     *
     * @param protocolCode The protocolCode to be set
     */
    public void setProtocolCode(String protocolCode) {
        this.protocolCode = protocolCode;
    }

    /**
     * This method compares an instance of Machine to other objects
     *
     * @param other The object to be compared
     * @return true if equal, false if not
     */
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Machine)) {
            return false;
        }

        final Machine that = (Machine) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    /**
     * This method returns the identity of the class
     *
     * @return the code field
     */
    @Override
    public String identity() {
        return this.code;
    }

    public Integer getPosition() {
        return machinePos.getPosition();
    }

    public static void verifyPosition(List<Machine> machines, Integer position) {
        for (Machine m : machines) {
            if (m.getPosition() == position) {
                throw new IllegalArgumentException("Posição inválida");
            }
        }
    }

    public boolean addConfigFile(String configFile) {
        File file = new File(configFile);

        BufferedReader br = null;
        StringBuilder strBuil = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null) {
                strBuil.append(st);
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File on path not found");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        ConfigurationFile newConfigFile = new ConfigurationFile(strBuil.toString());
        return this.configFile.add(newConfigFile);
    }

    public String brand() {
        return this.brand;
    }

    public String model() {
        return this.model;
    }

    public String protocol() {
        return this.protocolCode;
    }

    public Date installDate() {
        return this.installationDate;
    }

    public String description() {
        return this.description;
    }

    public String serialNumber() {
        return this.serialNumber.serialNumber();
    }

    public Integer position() {
        return this.machinePos.getPosition();
    }

    public String prodLineCode() {
        return this.machinePos.prodLineCode();
    }

    public Set<ConfigurationFile> configFiles() {
        return this.configFile;
    }
}
