# Introduction

This document is aimed for developers who are responsible for developing msr-api

## Open a project from STS
1. Import
1. Maven
1. Existing Maven projects
1. Select msr-api directory created in the preceding phase


## Add dependencies to pom.xml
```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>        
		<dependency>
		    <groupId>org.modelmapper</groupId>
		    <artifactId>modelmapper</artifactId>
		    <version>0.7.4</version>
		</dependency>          
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-rest</artifactId>
		</dependency>		
```

## Change Java version to 1.8 
```
    <properties>
        <java.version>1.8</java.version>
        <maven.compiler.source>${java.version}</maven.compiler.source>
        <maven.compiler.target>${java.version}</maven.compiler.target>
        <springfox-version>2.6.1</springfox-version>
    </properties>
```

## Create packages
* ocap.msr.entity 
* ocap.msr.repository
* ocap.msr.service

## Create Entity classes in the ocap.msr.entity package
* Team
* User
* Seat
* Reservation

### Team.java
generate followings after writing User.java like below
* default constructor
* constructor with fields: 
* constructor with fields: id, name, managerName, description
* getter / setter
* toString
* hashCode and equals 

'''
package ocap.msr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Team {

	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String name;
	
	@Column
	private String managerName;
	
	@Column
	private String description;

}
'''

### User.java
generate followings after writing User.java like below
* default constructor
* constructor with fields: email, password
* constructor with fields: id, email, password
* getter / setter
* toString
* hashCode and equals 

'''
package ocap.msr.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7437621130386820555L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String email;
	
	@Column 
	private String password;

	@ManyToOne 
	private Team team;	

}
'''

### Seat.java
generate followings after writing Seat.java like below
* default constructor
* constructor with fields: seatNo, location
* constructor with fields: id, seatNo, location
* getter / setter
* toString
* hashCode and equals 
```
package ocap.msr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Seat {

	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String seatNo;
	
	@Column
	private String location;

	@ManyToOne 
	private Team team;	
}
```
### ReservationStatus.java
This enum is used to store the status of a reservation
```
package ocap.msr.entity;

public enum ReservationStatus {
	AVAILABLE,
	OCCUPIED;
}
```
### Reservation.java
generate followings after writing Seat.java like below
* default constructor
* constructor with all fields
* getter / setter
* toString
* hashCode and equals 

```
package ocap.msr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Seat seat;
	
	@ManyToOne
	private User user;
	
	@Column
	java.sql.Date date;
	
	@Column
	java.sql.Timestamp startingTime;
	
	@Column
	java.sql.Timestamp endingTime;
	
	@Column
	ReservationStatus status;
	
	@Column
	java.sql.Timestamp reservedAt;
	
	@Column
	java.sql.Timestamp lastModifiedAt;
	
}
```

## Create Repository Interfaces in the ocap.msr.repository package
* SeatRepository
* UserRepository
* ReservationRepository

### SeatRepository.java

```
package ocap.msr.repository;

import org.springframework.data.repository.CrudRepository;

import ocap.msr.entity.Seat;

public interface SeatRepository extends CrudRepository<Seat, Long>{

}
```

### UserRepository.java

```
package ocap.msr.repository;

import org.springframework.data.repository.CrudRepository;

import ocap.msr.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{

}

```

### ReservationRepository.java

```
package ocap.msr.repository;

import org.springframework.data.repository.CrudRepository;

import ocap.msr.entity.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long>{

}

```

## Swagger2SpringBoot.java
This part is deprecated. We decided to create new SpringBootApplication name ocap.msr.MsrApplication.java 

So you need to add comments to @SpringBootApplication and @EnableSwagger2 and get rid of main method

<s>add annotation below to Swagger2SpringBoot class</s>

```
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "io.swagger", "ocap.msr.api", "ocap.msr.service" })
@EnableJpaRepositories("ocap.msr.repository")
@EntityScan("ocap.msr.entity")
```

## MsrApplication.java

### How Spring Boot's context is resolved from a test:
>The search algorithm works up from the package that contains the test until it finds a @SpringBootApplication or @SpringBootConfiguration annotated class. As long as you’ve structure your code in a sensible way your main configuration is usually found.

### About using the default package with Spring Boot:
>When a class doesn’t include a package declaration it is considered to be in the “default package”. The use of the “default package” is generally discouraged, and should be avoided. It can cause particular problems for Spring Boot applications that use @ComponentScan, @EntityScan or @SpringBootApplication annotations, since every class from every jar, will be read.
>
>We recommend that you follow Java’s recommended package naming conventions and use a reversed domain name (for example, com.example.project).

```
package ocap.msr;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "io.swagger", "ocap.msr.api", "ocap.msr.service", "ocap.msr.util" })
@EnableJpaRepositories("ocap.msr.repository")
@EntityScan("ocap.msr.entity")
public class MsrApplication  {

    public static void main(String[] args) throws Exception {
        new SpringApplication(MsrApplication.class).run(args);
    }
    
    @Bean
    public ModelMapper modelMapper() {
    		return new ModelMapper(); 
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    		return new BCryptPasswordEncoder();
    }
}

```

## application.properties
add mysql properties to application.properties file.

```
spring.jpa.hibernate.ddl-auto=create
spring.datasource.url=jdbc:mysql://140.86.13.169:3306/MSR_DB
spring.datasource.username=msrdb
spring.datasource.password=KimKyudong1!
spring.jpa.properties.javax.persistence.schema-generation.scripts.create-target=my-schema.sql
spring.jpa.properties.javax.persistence.schema-generation.scripts.action=create

spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true
```


## API and APIController
add @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") annotation to a parameter which indicates date-time
```
    @RequestMapping(value = "/reservations",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<ReservationVO>> findReservations( @NotNull@ApiParam(value = "starting time you want to reserve a seat", required = false) @RequestParam(value = "startingTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") DateTime startingTime, @NotNull@ApiParam(value = "ending time you want to reserve a seat", required = false) @RequestParam(value = "endingTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") DateTime endingTime,@ApiParam(value = "available or occupied, upon this value is null, all seats will be returned", allowableValues = "available, occupied") @RequestParam(value = "status", required = false) String status);
```

## ReservationApi
Email address may contain '.' so you need to specify path like this
RequestMapping(value = "/reservations/findByUser/{email:.+}"

```
 @RequestMapping(value = "/reservations/findByUser/{email:.+}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<ReservationVO>> findReservationsByUser(@ApiParam(value = "email",required=true ) @PathVariable("email") String email,@ApiParam(value = "the begining date of search") @RequestParam(value = "startingTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") DateTime startingTime,@ApiParam(value = "the end date of search. '2017-09-30T18:00:00.000Z'") @RequestParam(value = "endingTime", required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") DateTime endingTime);
```
