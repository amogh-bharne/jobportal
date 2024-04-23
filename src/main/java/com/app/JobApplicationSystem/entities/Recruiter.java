// package com.app.JobApplicationSystem.entities;

// import java.util.List;

// import javax.persistence.CascadeType;
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
// import javax.persistence.Table;

// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.Setter;
// import lombok.ToString;

// @Entity
// @Table(name = "recruiters")
// @Getter
// @Setter
// @AllArgsConstructor
// @ToString(exclude = {"jobListings"})
// public class Recruiter {
	
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "recruiter_id")
//     private Long recruiterId;

//     @Column(unique = true, nullable = false)
//     private String email;

//     @Column(nullable = false)
//     private String firstName;
    
//     @Column(nullable = false)
//     private String lastName;
    
//     @Column(nullable = false)
//     private String phoneNo;
    
//     @Column(nullable = false)
//     private String recruiterBio;
    
//     @Column(nullable = false)
//     private String companyName;

//     @OneToMany(mappedBy = "postedBy",fetch =FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true )
//     private List<Job> jobListings;

    


//     // Constructors, getters, and setters

//     public Recruiter() {
//         // Default constructor
//     }

   
// }

package com.app.JobApplicationSystem.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "recruiters")
@Getter
@Setter
@ToString(exclude = {"jobListings"})
public class Recruiter {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruiter_id")
    private Long recruiterId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false)
    private String phoneNo;
    
    @Column(nullable = false)
    private String recruiterBio;
    
    @Column(nullable = false)
    private String companyName;

    @OneToMany(mappedBy = "postedBy",fetch =FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Job> jobListings;

    // Private constructor to prevent instantiation from outside
    private Recruiter() {}

    // Nested Builder class
    public static class Builder {
        private String email;
        private String firstName;
        private String lastName;
        private String phoneNo;
        private String recruiterBio;
        private String companyName;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder phoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
            return this;
        }

        public Builder recruiterBio(String recruiterBio) {
            this.recruiterBio = recruiterBio;
            return this;
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Recruiter build() {
            Recruiter recruiter = new Recruiter();
            recruiter.email = this.email;
            recruiter.firstName = this.firstName;
            recruiter.lastName = this.lastName;
            recruiter.phoneNo = this.phoneNo;
            recruiter.recruiterBio = this.recruiterBio;
            recruiter.companyName = this.companyName;
            return recruiter;
        }
    }

    // Static builder method
    public static Builder builder() {
        return new Builder();
    }
}
