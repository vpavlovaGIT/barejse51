package ru.vpavlova.tm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
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
public class DomainDTO implements Serializable {

    @NotNull
    @JsonProperty("project")
    @XmlElement(name = "project")
    @XmlElementWrapper(name = "projects")
    @JacksonXmlElementWrapper(localName = "projects")
    private List<ProjectDTO> projects;

    @NotNull
    @JsonProperty("task")
    @XmlElement(name = "task")
    @XmlElementWrapper(name = "tasks")
    @JacksonXmlElementWrapper(localName = "tasks")
    private List<TaskDTO> tasks;

    @NotNull
    @JsonProperty("user")
    @XmlElement(name = "user")
    @XmlElementWrapper(name = "users")
    @JacksonXmlElementWrapper(localName = "users")
    private List<UserDTO> users;

}