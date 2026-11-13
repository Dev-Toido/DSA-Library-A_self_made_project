/*========================================================
  Institute of Computer Technology
  Ganpat University
  (2CSE301) DATABASE MANAGEMENT SYSTEM
  PRACTICAL 14 - MySQL Triggers
  Enrolment Number: 25162201094
  Database: Sakila
========================================================*/

USE sakila;

/*========================================================
  REMOVE OLD TRIGGERS AND AUDIT TABLES
  Re-running this practical resets the audit tables.
========================================================*/

DROP TRIGGER IF EXISTS trg_language_uppercase_before_insert;
DROP TRIGGER IF EXISTS trg_language_insert_message_after_insert;
DROP TRIGGER IF EXISTS trg_language_audit_before_update;
DROP TRIGGER IF EXISTS trg_language_audit_after_update;
DROP TRIGGER IF EXISTS trg_language_before_delete;
DROP TRIGGER IF EXISTS trg_language_after_delete;

DROP TABLE IF EXISTS audit_language_update;
DROP TABLE IF EXISTS language_before_delete;
DROP TABLE IF EXISTS language_after_delete;

/*========================================================
  Q3. TABLE FOR AUDITING LANGUAGE UPDATES
========================================================*/

CREATE TABLE audit_language_update (
    language_id TINYINT(3),
    name CHAR(20),
    last_update TIMESTAMP NULL DEFAULT NULL,
    `Status` CHAR(20),
    CONSTRAINT chk_audit_language_status
        CHECK (`Status` IN ('Before Update', 'After Update'))
);

/*========================================================
  Q4. TABLE FOR BACKING UP DELETED LANGUAGE RECORDS
========================================================*/

CREATE TABLE language_before_delete (
    language_id TINYINT(3),
    name CHAR(20),
    last_update TIMESTAMP NULL DEFAULT NULL,
    `Status` CHAR(20) DEFAULT 'Before Delete'
);

/*========================================================
  Q5. TABLE FOR DELETION STATUS MESSAGES
========================================================*/

CREATE TABLE language_after_delete (
    language_id TINYINT(3),
    name CHAR(20),
    `Status` VARCHAR(200)
);

DELIMITER $$

/*========================================================
  Q1. CONVERT EACH NEW LANGUAGE NAME TO UPPERCASE
========================================================*/

CREATE TRIGGER trg_language_uppercase_before_insert
BEFORE INSERT ON language
FOR EACH ROW
BEGIN
    SET NEW.name = UPPER(NEW.name);
END$$

/*========================================================
  Q2. SET MESSAGE WHENEVER A LANGUAGE IS INSERTED

  MySQL triggers cannot directly return a result set.
  This trigger sets a session variable; select it after
  the INSERT to display the required message.
========================================================*/

CREATE TRIGGER trg_language_insert_message_after_insert
AFTER INSERT ON language
FOR EACH ROW
BEGIN
    SET @language_insert_message =
        'The record has been inserted successfully';
END$$

/*========================================================
  Q3(a). SAVE THE OLD LANGUAGE RECORD BEFORE AN UPDATE
========================================================*/

CREATE TRIGGER trg_language_audit_before_update
BEFORE UPDATE ON language
FOR EACH ROW
BEGIN
    INSERT INTO audit_language_update
        (language_id, name, last_update, `Status`)
    VALUES
        (OLD.language_id, OLD.name, OLD.last_update, 'Before Update');
END$$

/*========================================================
  Q3(b). SAVE THE UPDATED LANGUAGE RECORD AFTER AN UPDATE
========================================================*/

CREATE TRIGGER trg_language_audit_after_update
AFTER UPDATE ON language
FOR EACH ROW
BEGIN
    INSERT INTO audit_language_update
        (language_id, name, last_update, `Status`)
    VALUES
        (NEW.language_id, NEW.name, NEW.last_update, 'After Update');
END$$

/*========================================================
  Q4. BACK UP A LANGUAGE RECORD BEFORE DELETION
========================================================*/

CREATE TRIGGER trg_language_before_delete
BEFORE DELETE ON language
FOR EACH ROW
BEGIN
    INSERT INTO language_before_delete
        (language_id, name, last_update)
    VALUES
        (OLD.language_id, OLD.name, OLD.last_update);
END$$

/*========================================================
  Q5. SAVE A MESSAGE AFTER A LANGUAGE IS DELETED
========================================================*/

CREATE TRIGGER trg_language_after_delete
AFTER DELETE ON language
FOR EACH ROW
BEGIN
    INSERT INTO language_after_delete
        (language_id, name, `Status`)
    VALUES
        (
            OLD.language_id,
            OLD.name,
            CONCAT(
                'Language ', TRIM(OLD.name),
                ' with ID ', OLD.language_id,
                ' was deleted on ',
                DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s')
            )
        );
END$$

DELIMITER ;

/*========================================================
  TEST Q1 AND Q2: INSERT A LANGUAGE
========================================================*/

INSERT INTO language (name)
VALUES ('hindi');

SET @test_language_id = LAST_INSERT_ID();

-- The inserted language name should be HINDI.
SELECT language_id, name, last_update
FROM language
WHERE language_id = @test_language_id;

-- Displays: The record has been inserted successfully
SELECT @language_insert_message AS message;

/*========================================================
  TEST Q3: UPDATE THE INSERTED LANGUAGE
========================================================*/

UPDATE language
SET name = 'SPANISH'
WHERE language_id = @test_language_id;

-- Should show both Before Update and After Update records.
SELECT language_id, name, last_update, `Status`
FROM audit_language_update
WHERE language_id = @test_language_id
ORDER BY CASE `Status`
    WHEN 'Before Update' THEN 1
    WHEN 'After Update' THEN 2
END;

/*========================================================
  TEST Q4 AND Q5: DELETE THE INSERTED LANGUAGE
========================================================*/

DELETE FROM language
WHERE language_id = @test_language_id;

-- Backup of the deleted language record.
SELECT *
FROM language_before_delete
WHERE language_id = @test_language_id;

-- Deletion status with the deletion timestamp.
SELECT *
FROM language_after_delete
WHERE language_id = @test_language_id;

/*========================================================
  Q6. DISPLAY ALL TRIGGERS IN THE SAKILA DATABASE
========================================================*/

SHOW TRIGGERS FROM sakila;

/*========================================================
  END OF PRACTICAL 14
========================================================*/
