DROP TABLE IF EXISTS pet;
DROP TABLE IF EXISTS owner;

CREATE TABLE Owner (
    id_owner BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(200) NOT NULL,
    phone_number VARCHAR(20) NOT NULL UNIQUE,
    CONSTRAINT OwnerPK PRIMARY KEY (id_owner)
) ENGINE = InnoDB;

CREATE TABLE Pet (
    id_pet BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    breed VARCHAR(100) NOT NULL,
    weight FLOAT NOT NULL,
    has_chip BOOLEAN NOT NULL,
    is_adopted BOOLEAN NOT NULL,
    id_owner BIGINT,
    photo_url VARCHAR(255) NOT NULL UNIQUE,
    CONSTRAINT PetPK PRIMARY KEY (id_pet),
    CONSTRAINT PetOwnerFK FOREIGN KEY (id_owner)
        REFERENCES Owner (id_owner)
        ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE INDEX PetIndexByName ON Pet (name);
CREATE INDEX OwnerIndexByPhoneNumber ON Owner (phone_number);
