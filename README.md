# MakeEntities
Make java objects from PostgreSQL table reverse engineering
A utility tool for quickly migrating PostgreSQL applications in Java EE to Spring Boot Rest Data

1º .- For Tables and Views
```java
import lombok.Data;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
```
Generate a class implements Serializable for table using [Project Lombok](https://projectlombok.org/) utility @Data anotation and @Entity for JPA Hibernate.

2º .- Repository
```java
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
```
Generate a Interface extends CrudRepository @Repository

3º .- Rest Controller
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
```
Generate a class Table or View Controller @RestController
