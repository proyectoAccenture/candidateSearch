package com.candidateSearch.searching.dto.response;

import com.candidateSearch.searching.entity.utility.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;

@Schema(name = "PostulationResponse",description = "Model representing a postulation of the candidate on database")
@Data
public class PostulationResponseDto {

    @Schema(name = "id", defaultValue = "1", description = "Unique Id of postulation on database")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate datePresentation;

    @Schema(name = "roleId",description = "Id of role", example = "1")
    private Long roleId;

    @Schema(name = "roleName",description = "Name of role", example = "string")
    private String roleName;

    @Schema(name = "roleDescription",description = "Description of role", example = "string")
    private String roleDescription;

    @Schema(name = "candidateId",description = "Id of candidate that appliqued", example = "1")
    private Long candidateId;

    @Schema(name = "candidateName",description = "Name of candidate that appliqued", example = "string")
    private String candidateName;

    @Schema(name = "candidateLastName",description = "Name of candidate that appliqued", example = "string")
    private String candidateLastName;

    @Schema(name = "status", description = "Status of the postulation (true if active, false if closed)", example = "false")
    private Status status;
}
