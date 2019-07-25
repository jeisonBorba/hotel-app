# Hotel APP
 
This project was developed to demonstrate knowledge of using Java APIs using SpringBoot and other technologies.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Java 8
Gradle
PostgreSQl
```

### Installing

A step by step series of examples that tell you have to get a development env running

*Clone project into your enviroment
*mvn clean install -DskipTests
*mvn spring-boot:run


### Scripts

Run the scripts to set up the database

	CREATE SEQUENCE hospede_id_seq
	 INCREMENT 1
	 MINVALUE 1
	 MAXVALUE 9223372036854775807
	 START 1
	 CACHE 1;
	CREATE TABLE hospede(
		id BIGINT NOT NULL DEFAULT nextval('hospede_id_seq'),
		nome CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
		documento VARCHAR(11) NOT NULL,
		telefone VARCHAR(11) NOT NULL,
		PRIMARY KEY(id)
	);
	ALTER SEQUENCE hospede_id_seq OWNED BY hospede.id;

	CREATE SEQUENCE checkin_id_seq
	 INCREMENT 1
	 MINVALUE 1
	 MAXVALUE 9223372036854775807
	 START 1
	 CACHE 1;
	CREATE TABLE checkin(
		id BIGINT NOT NULL DEFAULT nextval('checkin_id_seq'),
		data_entrada TIMESTAMP WITHOUT TIME ZONE NOT NULL,
		data_saida TIMESTAMP WITHOUT TIME ZONE NULL,
		adicional_veiculo BOOLEAN NOT NULL DEFAULT FALSE,
		valor_pago DOUBLE NULL,
		hospede_id INTEGER NOT NULL,
		PRIMARY KEY(id),
		CONSTRAINT checkin_hospede_id_fk FOREIGN KEY (hospede_id)
		  REFERENCES hospede (id) MATCH SIMPLE
		  ON UPDATE NO ACTION ON DELETE NO ACTION
	);
	ALTER SEQUENCE checkin_id_seq OWNED BY checkin.id;


