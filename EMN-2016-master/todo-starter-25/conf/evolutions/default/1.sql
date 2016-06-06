# Users schema

# --- !Ups

CREATE TABLE Task (
    id varchar(40) NOT NULL,
    name varchar(255) NOT NULL,
    done boolean NOT NULL,
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE Task;