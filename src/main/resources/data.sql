INSERT INTO drone (serial_number, model, weight_limit, battery_capacity, state)
VALUES ('1234567890', 'LIGHTWEIGHT', 200, 90, 'IDLE'),
       ('0987654321', 'HEAVYWEIGHT', 500, 50, 'IDLE'),
       ('1111111111', 'MIDDLEWEIGHT', 400, 80, 'IDLE'),
       ('2222222222', 'CRUSIERWEIGHT', 390, 70, 'IDLE'),
       ('3333333333', 'HEAVYWEIGHT', 500, 60, 'IDLE'),
       ('4444444444', 'LIGHTWEIGHT', 200, 95, 'IDLE'),
       ('5555555555', 'MIDDLEWEIGHT', 400, 85, 'IDLE'),
       ('6666666666', 'CRUSIERWEIGHT', 390, 75, 'IDLE'),
       ('7777777777', 'HEAVYWEIGHT', 500, 55, 'IDLE'),
       ('8888888888', 'LIGHTWEIGHT', 200, 100, 'IDLE');

INSERT INTO medication (id, name, weight, code, image_url)
VALUES (1, 'Ibuprofen', 125, 'IBUPROFEN_01', 'https://example.com/ibuprofen.jpg'),
       (2, 'Paracetamol', 70, 'PARACETAMOL_01', 'https://example.com/paracetamol.jpg'),
       (3, 'Amoxicillin', 100, 'AMOXICILLIN_01', 'https://example.com/amoxicillin.jpg'),
       (4, 'Aspirin', 180, 'ASPIRIN_01', 'https://example.com/aspirin.jpg'),
       (5, 'Codeine', 190, 'CODEINE_01', 'https://example.com/codeine.jpg'),
       (6, 'Ciprofloxacin', 140, 'CIPROFLOXACIN_01', 'https://example.com/ciprofloxacin.jpg'),
       (7, 'Doxycycline', 110, 'DOXYCYCLINE_01', 'https://example.com/doxycycline.jpg'),
       (8, 'Morphine', 80, 'MORPHINE_01', 'https://example.com/morphine.jpg'),
       (9, 'Oxycodone', 70, 'OXYCODONE_01', 'https://example.com/oxycodone.jpg'),
       (10, 'Penicillin', 155, 'PENICILLIN_01', 'https://example.com/penicillin.jpg'),
       (11, 'Propranolol', 165, 'PROPRANOLOL_01', 'https://example.com/propranolol.jpg'),
       (12, 'Ranitidine', 130, 'RANITIDINE_01', 'https://example.com/ranitidine.jpg'),
       (13, 'Sildenafil', 140, 'SILDENAFIL_01', 'https://example.com/sildenafil.jpg'),
       (14, 'Tramadol', 140, 'TRAMADOL_01', 'https://example.com/tramadol.jpg'),
       (15, 'Venlafaxine', 120, 'VENLAFAXINE_01', 'https://example.com/venlafaxine.jpg');
