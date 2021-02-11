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

    2.2. Create user and databases: 
        ``` psql postgres; 
            CREATE USER root WITH PASSWORD 'qwerty';
            CREATE DATABASE [your login];
            CREATE DATABASE cashflow;
            exit;```

3. apply maven wrapper, if necessary: ```mvn -N io.takari:maven:wrapper```

4. write postgresql username and password in hibernate config file if its different from 2.3. (Ubay/src/main/resources/hibernate.cfg.xml)

5. run app: ```./mvnw spring-boot:run```

6. open in browser website: http://localhost:8080/cashflow

### View:
![view game](https://raw.githubusercontent.com/arni30/Cashflow/main/pictures/cashflow-7.png)
![view game](https://raw.githubusercontent.com/arni30/Cashflow/main/pictures/cashflow-8.png)
![view game](https://raw.githubusercontent.com/arni30/Cashflow/main/pictures/cashflow-3.png)
![view game](https://raw.githubusercontent.com/arni30/Cashflow/main/pictures/cashflow-4.png)
![view game](https://raw.githubusercontent.com/arni30/Cashflow/main/pictures/cashflow-2.png)
![view game](https://raw.githubusercontent.com/arni30/Cashflow/main/pictures/cashflow-1.png)
![view game](https://raw.githubusercontent.com/arni30/Cashflow/main/pictures/cashflow-5.png)
![view game](https://raw.githubusercontent.com/arni30/Cashflow/main/pictures/cashflow-6.png)
![view game](https://raw.githubusercontent.com/arni30/Cashflow/main/pictures/cashflow-9.png)
