

INSERT INTO drone (serial_number, model, weight_limit, battery_capacity, state)
VALUES ('1234567890', 'Lightweight', 300, 90, 'IDLE'),
       ('0987654321', 'Heavyweight', 450, 50, 'IDLE'),
       ('1111111111', 'Middleweight', 490, 80, 'IDLE'),
       ('2222222222', 'Cruiserweight', 500, 70, 'IDLE'),
       ('3333333333', 'Heavyweight', 370, 60, 'IDLE'),
       ('4444444444', 'Lightweight', 290, 95, 'IDLE'),
       ('5555555555', 'Middleweight', 450, 85, 'IDLE'),
       ('6666666666', 'Cruiserweight',390, 75, 'IDLE'),
       ('7777777777', 'Heavyweight', 420.0, 55, 'IDLE'),
       ('8888888888', 'Lightweight', 500, 100, 'IDLE');

INSERT INTO medication (id, name, weight, code, image_url)
VALUES
    (1, 'Ibuprofen', 0.1, 'IBUPROFEN_01', 'https://example.com/ibuprofen.jpg'),
    (2, 'Paracetamol', 0.05, 'PARACETAMOL_01', 'https://example.com/paracetamol.jpg'),
    (3, 'Amoxicillin', 0.2, 'AMOXICILLIN_01', 'https://example.com/amoxicillin.jpg'),
    (4, 'Aspirin', 0.1, 'ASPIRIN_01', 'https://example.com/aspirin.jpg'),
    (5, 'Codeine', 0.15, 'CODEINE_01', 'https://example.com/codeine.jpg'),
    (6, 'Ciprofloxacin', 0.2, 'CIPROFLOXACIN_01', 'https://example.com/ciprofloxacin.jpg'),
    (7, 'Doxycycline', 0.15, 'DOXYCYCLINE_01', 'https://example.com/doxycycline.jpg'),
    (8, 'Morphine', 0.25, 'MORPHINE_01', 'https://example.com/morphine.jpg'),
    (9, 'Oxycodone', 0.2, 'OXYCODONE_01', 'https://example.com/oxycodone.jpg'),
    (10, 'Penicillin', 0.2, 'PENICILLIN_01', 'https://example.com/penicillin.jpg'),
    (11, 'Propranolol', 0.1, 'PROPRANOLOL_01', 'https://example.com/propranolol.jpg'),
    (12, 'Ranitidine', 0.1, 'RANITIDINE_01',  'https://example.com/ranitidine.jpg'),
    (13, 'Sildenafil', 0.1, 'SILDENAFIL_01', 'https://example.com/sildenafil.jpg'),
    (14, 'Tramadol', 0.15, 'TRAMADOL_01', 'https://example.com/tramadol.jpg'),
    (15, 'Venlafaxine', 0.1, 'VENLAFAXINE_01', 'https://example.com/venlafaxine.jpg');
