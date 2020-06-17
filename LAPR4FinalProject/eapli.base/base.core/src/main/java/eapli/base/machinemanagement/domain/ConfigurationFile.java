package eapli.base.machinemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class ConfigurationFile implements ValueObject {

    private String description;

    protected ConfigurationFile() {
    }

    public ConfigurationFile(String description) {
        this.description = description;
    }

    public String configFileDescription() {
        return this.description;
    }
}
