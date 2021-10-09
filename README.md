# SDA8-COVID-Vaccine-Reservation-System

This repository contains structure related reports and source code for the COVID vaccine reservation system

Link to application: https://covidvaccinesystem-sda8.herokuapp.com/

**Admin info**

email - admin@gmail.com

password - admin

### **Team Members**

| Name               | Student ID | Email                               |
| ------------------ | ---------- | ----------------------------------- |
| Jiashuai Yu        | 954064     | jiashuaiy@student.unimelb.edu.au    |
| Sufan Xia          | 828840     | sufanx@student.unimelb.edu.au       |
| Thomas Capicchiano | 831209     | tcapicchiano@student.unimelb.edu.au |
| Zhuolun Wu         | 954465     | zhuolunw@student.unimelb.edu.au     |

### **Change Log**

| Date       | Changes                                                      |
| ---------- | ------------------------------------------------------------ |
| 15/08/2021 | Part 1 - Use Cases and Domain Model                          |
| 16/09/2021 | Part 2 - Implemented some functionalities based on back-end servlets |
| 26/09/2021 | Part 2 - Implemented patterns, Detailed Use Case and Architecture Documentation |
|            |                                                              |

### **Project Overview**

The Federal Government has recently released its plan to re-establish a "COVID-normal" in Australia, precipitated primarily on Australians receiving a dose of a COVID-19 vaccine. In order to assist with what is likely the single largest logistical challenge ever faced by this government, they require your help to build a vaccination booking and management system.

The application will be a centralised vaccine management platform that can help public health officials manage vaccine distribution at scale and expedite vaccine administration for a large population. The solution should provide real-time access to vaccine-administration data to support decision-making and distribution efforts.

### **Repository Index**

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

### **Database**

```sql
CREATE TYPE identity AS ENUM ('Admin', 'Health Care Provider', 'Recipient');

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

CREATE TABLE timerange
(
    timeid SERIAL UNIQUE,
    date date,
    fromtime time,
    totime time,
    PRIMARY KEY (timeid),
);

CREATE TABLE timeslots
(
    id SERIAL UNIQUE,
    timerange integer,
    provider text,
    numberofshots integer,
    vaccinename text,
    PRIMARY KEY (id),
    FOREIGN KEY (provider) REFERENCES users(hcpname),
    FOREIGN KEY (timerange) REFERENCES timerange(timeid),
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

CREATE TABLE certificates
(
    id SERIAL UNIQUE,
    email text,
    bookingid integer,
    vaccinename text,
    PRIMARY KEY (id),
    FOREIGN KEY (email) REFERENCES users(email),
    FOREIGN KEY (bookingid) REFERENCES bookings(bookingid),
    FOREIGN KEY (vaccinename) REFERENCES vaccines(name)
);

INSERT INTO users(email, password,user_identity) VALUES ('admin@gmail.com', 'admin','Admin');
INSERT INTO vaccines(name, fromAge,toAge) VALUES ('AstraZeneca', '50','200');
INSERT INTO vaccines(name, fromAge,toAge) VALUES ('Pfizer', '0','200');
```

### **Part 3 Timeline**

Meeting date: 7/10/2021

| Group member       | 7/10/2021 - 11/10/2021                                       | 12/10/2021 - 13/10/2021            | 14/10/2021 - 16/10/2021         | 17/10/2021                               |
| ------------------ | ------------------------------------------------------------ | ---------------------------------- | ------------------------------- | ---------------------------------------- |
| Jiashuai Yu        | Fix patterns inappropriately used in part 2 and complete use cases | Apply concurrency patterns         |                                 | Final review on codes and documentations |
| Sufan Xia          | Front-end UI polishing                                       | Certificate and questionnaire JSPs | Architecture documentation      | Final review on codes and documentations |
| Thomas Capicchiano | Abstract testing plan or equivalent                          |                                    | Testing plan and JMeter scripts | Final review on codes and documentations |
| Zhuolun Wu         | Fix patterns inappropriately used in part 2 and complete use cases | Architecture documentation         | Architecture documentation      | Final review on codes and documentations |

