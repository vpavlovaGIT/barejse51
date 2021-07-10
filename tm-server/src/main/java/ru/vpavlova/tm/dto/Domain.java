package ru.vpavlova.tm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@XmlRootElement
@JsonRootName("domain")
@XmlAccessorType(XmlAccessType.FIELD)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Domain implements Serializable {

    @NotNull
    @JsonProperty("project")
    @XmlElement(name = "project")
    @XmlElementWrapper(name = "projects")
    @JacksonXmlElementWrapper(localName = "projects")
    private List<Project> projects;

    @NotNull
    @JsonProperty("task")
    @XmlElement(name = "task")
    @XmlElementWrapper(name = "tasks")
    @JacksonXmlElementWrapper(localName = "tasks")
    private List<Task> tasks;

    @NotNull
    @JsonProperty("user")
    @XmlElement(name = "user")
    @XmlElementWrapper(name = "users")
    @JacksonXmlElementWrapper(localName = "users")
    private List<User> users;

}
