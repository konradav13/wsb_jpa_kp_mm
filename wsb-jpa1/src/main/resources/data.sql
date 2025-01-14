INSERT INTO ADDRESS (city, address_line1, address_line2, postal_code)
VALUES
    ('Birmingham', 'Pall Mall', 'Avenue', 'W1D 3DG'),
    ('Liverpool', 'Kings Road', 'Avenue', 'B1 1AA'),
    ('Manchester', 'Portobello Road', 'Lane', 'M1 1AE'),
    ('Liverpool', 'Baker Street', 'Avenue', 'W1D 3DG'),
    ('Liverpool', 'Baker Street', 'Lane', 'EH1 1BB'),
    ('Liverpool', 'Kings Road', 'Boulevard', 'BS1 4TR')
    ('Chelsea', 'Nostreet Street', 'Avenue', 'AAA BEE'),
    ('Arsenal', 'City Street', 'Lane', 'UUU 1BB'),
    ('Gdynia', 'Wojska Polskiego', 'Boulevard', 'CCC 4TR');

INSERT INTO PATIENT (first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, gender)
VALUES
    ('Adam', 'Taylor', '987654321', 'adam.taylor@gmail.com', 'P001', '1989-11-12', 4, 'MALE'),
    ('Emma', 'Jackson', '777888999', 'emma.jackson@gmail.com', 'P004', '1979-09-19', 3, 'FEMALE'),
    ('Sophia', 'Wrong', '321654987', 'sophia.wrong@gmail.com', 'P006', '1992-05-05', 5, 'FEMALE'),
    ('Julia', 'Granger', '987654321', 'julia.granger@gmail.com', 'P003', '1981-02-13', 5, 'FEMALE'),
    ('James', 'Lovegood', '321654987', 'james.lovegood@gmail.com', 'P009', '1979-09-19', 3, 'MALE'),
    ('Sophia', 'White', '321657987', 'sophia.white@gmail.com', 'P010', '1989-11-12', 5, 'FEMALE');

INSERT INTO DOCTOR (first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
VALUES
    ('John', 'Johnjohn', '567492872', 'john.johnjohn@gmail.com', 'D001', 'SURGEON', 3),
    ('Albert', 'Car', '111222333', 'albert.car@gmail.com', 'D002', 'OCULIST', 4),
    ('Bad', 'Good', '987654321', 'bad.good@gmail.com', 'D003', 'GP', 5),
    ('Victor', 'Loose', '098777123', 'victor.loose@gmail.com', 'D003', 'GP', 5);

INSERT INTO VISIT (description, time, doctor_id, patient_id)
VALUES
    ('Treatment for sprained ankle', '2024-01-10 09:00:00', 1, 1),
    ('Follow-up after knee surgery', '2024-01-15 11:00:00', 2, 2),
    ('Burn healing from a hot stove', '2024-01-20 14:00:00', 1, 3),
    ('Concussion from a car accident', '2024-02-05 10:00:00', 1, 4),
    ('Allergic reaction to pollen', '2024-02-15 13:00:00', 3, 5),
    ('Headache from stress', '2024-03-01 12:00:00', 1, 6),
    ('Stress from headacje', '2024-12-08 08:00:00', 1, 6);

INSERT INTO MEDICAL_TREATMENT (description, type)
VALUES
    ('Application of antibiotic ointment for wound healing', 'Ointment'),
    ('Pain-relief medication for post-surgery recovery', 'Medication'),
    ('Burn-soothing gel treatment', 'Gel'),
    ('Concussion-recovery therapy with rest and hydration', 'Therapy'),
    ('Antihistamine for allergic reaction', 'Medication'),
    ('Cognitive therapy for stress relief', 'Therapy');

INSERT INTO treatment_for_visit (visit_id, treatment_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6);