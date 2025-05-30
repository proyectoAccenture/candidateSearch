package com.candidateSearch.searching.dto.response;

import com.candidateSearch.searching.entity.utility.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CandidateWithPaginationResponseDto",description = "Model representing a candidate on the database")
@Data
public class CandidateResponseDto {
    @Schema(name = "id",description = "id of the candidate")
    private Long id;

    @Schema(name = "name", description = "name of the candidate")
    private String name;

    @Schema(name = "lastName", description = "last name of the candidate")
    private String lastName;

    @Schema(name = "card",description = "id card of the candidate")
    private String card;

    @Schema(name = "phone",description = "phone of the candidate")
    private String phone;

    @Schema(name = "city", description = "city of the candidate")
    private String city;

    @Schema(name = "email", description = "email of the candidate")
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @Schema(name = "source", description = "source where the vacancy was launched ")
    private String source;

    @Schema(name = "skills", description = "Skills should have of the candidate")
    private String skills;

    @Schema(name = "yearsExperience", description = "Years of experience of the candidate")
    private String yearsExperience;

    @Schema(name = "workExperience", description = "Experience should have of the candidate")
    private String workExperience;

    @Schema(name = "seniority", description = "Level should have of the candidate")
    private String seniority;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#,###")
    private Long salaryAspiration;

    @Schema(name = "level", description = "Salary scale")
    private Integer level;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate datePresentation;

    @Schema(name = "hasResume", description = "Indicates whether the candidate has a resume uploaded")
    private Boolean hasResume;

    @Schema(name = "resumeFileName", description = "Resume file name")
    private String resumeFileName;

    @Schema(name = "status", description = "Status of the candidate (true if active, false if closed)", example = "false")
    private Status status;

    @Schema(name = "originId",description = "Id of origin")
    private Long originId;

    @Schema(name = "originName",description = "Name of origin")
    private String originName;

    @Schema(name = "jobProfileId",description = "Id of job profile")
    private Long jobProfileId;

    @Schema(name = "jobProfileName",description = "Name of job profile")
    private String jobProfileName;
}
