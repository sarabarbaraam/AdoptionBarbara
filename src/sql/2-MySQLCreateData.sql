-- Insert example into OWNER table
INSERT INTO OWNER (name, address, phone_number)
VALUES
    ('Alice Johnson', '123 Elm Street', '555-0101'),
    ('Bob Williams', '456 Pine Avenue', '555-0202'),
    ('Charlie Brown', '789 Maple Road', '555-0303'),
    ('Diana Ross', '321 Oak Boulevard', '555-0404');

-- Insert example into PET table
INSERT INTO PET (id_owner, name, birth_date, breed, weight, has_chip, is_adopted, photo_url)
VALUES
    (1, 'Buddy', '2022-06-10', 'Golden Retriever', 25.5, true, true, 'https://example.com/buddy.jpg'),
    (2, 'Whiskers', '2020-09-05', 'Siamese Cat', 4.2, false, true, 'https://example.com/whiskers.jpg'),
    (NULL, 'Rocky', '2023-01-20', 'Mixed Breed', 12.0, false, false, 'https://example.com/rocky.jpg'),
    (NULL, 'Max', '2021-11-14', 'Labrador Retriever', 30.0, true, false, 'https://example.com/max.jpg'),
    (3, 'Bella', '2019-03-25', 'German Shepherd', 35.0, true, true, 'https://example.com/bella.jpg'),
    (NULL, 'Milo', '2018-07-19', 'Beagle', 8.0, false, false, 'https://example.com/milo.jpg'),
    (NULL, 'Luna', '2020-11-02', 'French Bulldog', 9.5, true, false, 'https://example.com/luna.jpg'),
    (NULL, 'Coco', '2022-04-30', 'Poodle', 5.3, true, false, 'https://example.com/coco.jpg'),
    (1, 'Rex', '2017-10-11', 'Rottweiler', 40.0, true, true, 'https://example.com/rex.jpg'),
    (NULL, 'Sasha', '2022-01-15', 'Chihuahua', 3.5, true, false, 'https://example.com/sasha.jpg'),
    (NULL, 'Toby', '2021-08-03', 'Shih Tzu', 6.0, false, false, 'https://example.com/toby.jpg'),
    (NULL, 'Zoe', '2022-12-25', 'Boxer', 28.0, true, false, 'https://example.com/zoe.jpg'),
    (NULL, 'Buddy', '2018-06-10', 'Golden Retriever', 25.5, true, false, 'https://example.com/images/buddy.jpg')
