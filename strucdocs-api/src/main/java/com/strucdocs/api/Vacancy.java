package com.strucdocs.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@JsonAutoDetect
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy
    implements Serializable {

    private static final long serialVersionUID = 4296217062480831258L;

    private Long id;

    @JsonIgnore
    private Musician musician;

    @NotNull
    private VacancyType type;
}
