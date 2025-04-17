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
    (NULL, 'Buddy', '2018-06-10', 'Golden Retriever', 25.5, true, false, 'https://example.com/images/buddy.jpg'),
    (4, 'Nina', '2023-05-12', 'Cocker Spaniel', 10.0, true, true, 'https://example.com/nina.jpg'),
    (2, 'Simba', '2019-01-30', 'Persian Cat', 5.1, false, true, 'https://example.com/simba.jpg'),
    (NULL, 'Thor', '2020-10-05', 'Doberman', 32.0, true, false, 'https://example.com/thor.jpg'),
    (3, 'Daisy', '2021-04-18', 'Bulldog', 20.0, true, true, 'https://example.com/daisy.jpg'),
    (1, 'Maggie', '2019-07-22', 'Border Collie', 19.0, false, true, 'https://example.com/maggie.jpg'),
    (NULL, 'Chester', '2023-03-02', 'Basset Hound', 24.0, true, false, 'https://example.com/chester.jpg'),
    (NULL, 'Kiwi', '2022-08-17', 'Parrot', 0.9, false, false, 'https://example.com/kiwi.jpg'),
    (4, 'Bobby', '2021-06-12', 'Pug', 7.5, true, true, 'https://example.com/bobby.jpg'),
    (NULL, 'Leo', '2020-03-09', 'Tabby Cat', 4.0, false, false, 'https://example.com/leo.jpg'),
    (NULL, 'Olive', '2022-09-28', 'Sheltie', 14.0, true, false, 'https://example.com/olive.jpg'),
    (2, 'Shadow', '2023-02-11', 'Mixed Breed', 13.3, false, true, 'https://example.com/shadow.jpg'),
    (NULL, 'Nala', '2022-05-05', 'Husky', 27.0, true, false, 'https://example.com/nala.jpg'),
    (3, 'Oscar', '2021-10-22', 'Shar Pei', 22.0, true, true, 'https://example.com/oscar.jpg'),
    (4, 'Mimi', '2018-12-04', 'Russian Blue', 4.6, false, true, 'https://example.com/mimi.jpg'),
    (NULL, 'Loki', '2023-04-07', 'Great Dane', 45.0, true, false, 'https://example.com/loki.jpg'),
    (2, 'Penny', '2020-02-29', 'Mini Schnauzer', 7.0, true, true, 'https://example.com/penny.jpg'),
    (NULL, 'Bruce', '2021-01-17', 'Akita', 38.0, false, false, 'https://example.com/bruce.jpg');
