# Cashflow

### Description:
Ucode project: develop the app for financial accounting.

### Developer team:
 [Tetiana Rohalska](https://github.com/trohalska) | [Alexandr Arnopolin](https://github.com/arni30) | [Anastasiia Svyryda](https://github.com/NastiaSvyryda)

### Implementation:
- Front-End - HTML/CSS/JavaScript
- Back-End - Java 14, Maven, SQL

### Technologies:
- Spring Framework, Spring Boot, Spring Security
- PostgreSQL

### Usage:

1. ```git clone https://github.com/arni30/Cashflow```

2. PostgreSQL database:

    2.1. You have to install PostgreSQL.

    2.2. Run postgresql server: ```psql postgres```

    2.3. Create user: ```CREATE USER root WITH PASSWORD 'qwerty'```
    
    2.4. Create database via terminal:
        ```createdb
        psql -f Cashflow/src/main/resources/create_db.sql```

3. apply maven wrapper, if necessary: ```mvn -N io.takari:maven:wrapper```

4. write postgresql username and password in hibernate config file if its different from 2.3. (Ubay/src/main/resources/hibernate.cfg.xml)

5. run server: ```./mvnw springboot:run```

6. open in browser website: http://localhost:8080/cashflow

### View:
![view game](https://raw.githubusercontent.com/arni30/Ubay/main/src/main/resources/01.png)


