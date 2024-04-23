// package com.app.Job_Portal.dto;

// import java.time.LocalDate;

// import javax.validation.constraints.Max;
// import javax.validation.constraints.Min;
// import javax.validation.constraints.NotBlank;
// import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;

// import lombok.Getter;
// import lombok.Setter;
// import lombok.ToString;

// @Getter
// @Setter
// @ToString
// public class EducationalDetailsDto {
	
// 	@NotNull
//     @NotBlank(message="Please enter your qualification")
// 	@Size(max =30,message ="qualification should not be greater than 30 character")
//     private String qualification;

// 	@NotNull
//     @NotBlank(message="Please enter your Institute name")
// 	@Size(max =30,message ="qualification should not be greater than 30 character")
//     private String institute;

//     private LocalDate admissionDate;

//     private LocalDate completionDate;

//     @Max(value = 100, message = "Percentage can't be exceed than 100")
// 	@Min(value = 45, message = "Percentage can't be less than 45")
//     private double percentage;

// }

package com.app.Job_Portal.dto;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class EducationalDetailsDto {
    
    @NotNull
    @NotBlank(message="Please enter your qualification")
    @Size(max =30,message ="qualification should not be greater than 30 character")
    private String qualification;

    @NotNull
    @NotBlank(message="Please enter your Institute name")
    @Size(max =30,message ="qualification should not be greater than 30 character")
    private String institute;

    private LocalDate admissionDate;

    private LocalDate completionDate;

    @Max(value = 100, message = "Percentage can't be exceed than 100")
    @Min(value = 45, message = "Percentage can't be less than 45")
    private double percentage;

    private EducationalDetailsDto(Builder builder) {
        this.qualification = builder.qualification;
        this.institute = builder.institute;
        this.admissionDate = builder.admissionDate;
        this.completionDate = builder.completionDate;
        this.percentage = builder.percentage;
    }

    public static class Builder {
        private String qualification;
        private String institute;
        private LocalDate admissionDate;
        private LocalDate completionDate;
        private double percentage;

        public Builder qualification(String qualification) {
            this.qualification = qualification;
            return this;
        }

        public Builder institute(String institute) {
            this.institute = institute;
            return this;
        }

        public Builder admissionDate(LocalDate admissionDate) {
            this.admissionDate = admissionDate;
            return this;
        }

        public Builder completionDate(LocalDate completionDate) {
            this.completionDate = completionDate;
            return this;
        }

        public Builder percentage(double percentage) {
            this.percentage = percentage;
            return this;
        }

        public EducationalDetailsDto build() {
            return new EducationalDetailsDto(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
