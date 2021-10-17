# SDA8-COVID-Vaccine-Reservation-System

This repository contains structure related reports and source code for the COVID vaccine reservation system

Link to application: https://covidvaccinesystem-sda8.herokuapp.com/

### Team Members

| Name               | Student ID | Email                               |
| ------------------ | ---------- | ----------------------------------- |
| Jiashuai Yu        | 954064     | jiashuaiy@student.unimelb.edu.au    |
| Sufan Xia          | 828840     | sufanx@student.unimelb.edu.au       |
| Thomas Capicchiano | 831209     | tcapicchiano@student.unimelb.edu.au |
| Zhuolun Wu         | 954465     | zhuolunw@student.unimelb.edu.au     |

### Change Log

| Date       | Changes                                                      |
| ---------- | ------------------------------------------------------------ |
| 15/08/2021 | Part 1 - Use Cases and Domain Model                          |
| 16/09/2021 | Part 2 - Implemented some functionalities based on back-end servlets |
| 26/09/2021 | Part 2 - Implemented patterns, Detailed Use Case and Architecture Documentation |
| 17/10/2021 | Part 3 - Implemented patterns and concurrency testing        |

### Project Overview

The Federal Government has recently released its plan to re-establish a "COVID-normal" in Australia, precipitated primarily on Australians receiving a dose of a COVID-19 vaccine. In order to assist with what is likely the single largest logistical challenge ever faced by this government, they require your help to build a vaccination booking and management system.

The application will be a centralized vaccine management platform that can help public health officials manage vaccine distribution at scale and expedite vaccine administration for a large population. The solution should provide real-time access to vaccine-administration data to support decision-making and distribution efforts.

### Data samples

| Username (email)    | Password | Identity             |
| ------------------- | -------- | -------------------- |
| admin@gmail.com     | admin    | Admin                |
| hcp@gmail.com       | test     | Health Care Provider |
| recipient@gmail.com | test     | Recipient            |
| test1@gmail.com     | test     | Recipient            |
| ...                 | ...      | ...                  |
| test20@gmail.com    | test     | Recipient            |

There is one admin account, one HCP account, one recipient account for demonstration and 20 recipient accounts for testing.

In addition, there is no other important data to be included in this section, timeslot, question, and booking can be created during runtime /demonstrating.

### Repository Index

[Part 1 - Use Cases and Domain Model](docs/part1)

- [Report](docs/part1/part_1_use_cases.pdf)
- [Checklist](docs/part1/Checklist%20Part%201.pdf)

[Part 2](docs/part2)

* [Detailed use cases](docs/part2/detailed%20use%20cases.pdf)
* [Architecture documentation](docs/part2/architecture%20documention.pdf)
* [Checklist](docs/part2/Checklist%20Part%202.pdf)

[Part 3](docs/part3)

[Part 4](docs/part4)

[Data samples](docs/data-samples)

[Source code](src)

### Database
<details>
<summary>SQL for tables in database</summary>

```sql
CREATE TYPE identity AS ENUM ('Admin', 'Health Care Provider', 'Recipient');

CREATE TABLE users
(
    email text UNIQUE,
    password text default 123456,
    dateOfBirth date default '1900-01-01',
    firstName text default 'Default firstname',
    lastName text default 'Default lastname',
    user_identity identity,
    postCode integer default 0,
    typeOfProvider text default '----',
    hcpName text UNIQUE,
    vaccinated boolean DEFAULT False,
    PRIMARY KEY (email)
);

CREATE TABLE vaccines
(
    name text,
    fromage text,
    toage text,
    PRIMARY KEY (name)
);


CREATE TABLE timeslots
(
    id SERIAL UNIQUE,
    date date,
    fromtime time,
    totime time,
    provider text,
    numberofshots integer,
    vaccinename text,
    PRIMARY KEY (id),
    FOREIGN KEY (provider) REFERENCES users(hcpname),
    FOREIGN KEY (vaccinename) REFERENCES vaccines(name)
);

CREATE TABLE bookings
(
    bookingid SERIAL UNIQUE,
    email text,
    timeslotid integer,
    vaccinename text,
    PRIMARY KEY (email, timeslotid),
    FOREIGN KEY (email) REFERENCES users(email),
    FOREIGN KEY (timeslotid) REFERENCES timeslots(id),
    FOREIGN KEY (vaccinename) REFERENCES vaccines(name)
);

CREATE TABLE questions
(
    id SERIAL UNIQUE,
    vaccinename text,
    question varchar(1000),
    desiredanswer bool,
    PRIMARY KEY (id),
    FOREIGN KEY (vaccinename) REFERENCES vaccines(name)
);

CREATE TABLE user_answers_question
(
    id SERIAL UNIQUE,
    userid text,
    questionid integer,
    answer bool,
    PRIMARY KEY (id),
    FOREIGN KEY (userid) REFERENCES users(email),
    FOREIGN KEY (questionid) REFERENCES questions(id)
);

INSERT INTO users(email, password,user_identity) VALUES ('admin@gmail.com', '$shiro1$SHA-256$500000$v4eixUCvn6V9KSudK8Ne7g==$Nelv3843Bf6h3oBPp4EBU+qNCDu+tf5dDGcdQIIngDs=','Admin');
INSERT INTO vaccines(name, fromAge,toAge) VALUES ('AstraZeneca', '50','200');
INSERT INTO vaccines(name, fromAge,toAge) VALUES ('Pfizer', '0','200');
INSERT INTO questions(vaccinename, question, desiredanswer) VALUES ('AstraZeneca', 'Is your age above 50?', true);
INSERT INTO questions(vaccinename, question, desiredanswer) VALUES ('Pfizer', 'Do you have heart disease?', false);
INSERT INTO questions(vaccinename, question, desiredanswer) VALUES ('AstraZeneca', 'Are you a SUPERMAN?', true);
```
</details>

![](docs\report%20src\database.png)
