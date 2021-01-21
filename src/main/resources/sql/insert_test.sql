
-- INSERT
-- INTO users(user_id, email, login, password, validation_status)
-- VALUES (default, 'ubayforever0@gmail.com', 'Bilbo Baggins', 'burglar', 1);

INSERT INTO users VALUES (1, 'ubayforever0@gmail.com', 'Bilbo Baggins', 'burglar', null, 1);
INSERT INTO users VALUES (2, 'trogalska2208@gmail.com', 'tr', null, null, 1);

-- non dependent tables

INSERT INTO currency VALUES (1, 'UAN', 30);
INSERT INTO currency VALUES (2, 'USD', 1);
INSERT INTO currency VALUES (3, 'EUR', 0.8);

INSERT INTO wallet VALUES (1, 1020, 'my wallet', 1, 2);
INSERT INTO wallet VALUES (2, 20, 'dollars', 2, 2);
INSERT INTO wallet VALUES (3, 688, 'euro', 3, 2);

INSERT INTO category VALUES (1, 'auchan', 'supermarket');
INSERT INTO category VALUES (2, 'spain', 'vacation');
INSERT INTO category VALUES (3, 'corner store', 'small shop');
INSERT INTO category VALUES (4, 'fly tickets', 'tickets');
INSERT INTO category VALUES (5, 'food, paper, etc', 'work stuff');
INSERT INTO category VALUES (6, 'donations', 'giveaway');
INSERT INTO category VALUES (7, 'work', 'salary');

INSERT INTO tag VALUES (1, 'food', 'apple');
INSERT INTO tag VALUES (2, '404 bus', 'tickets');
INSERT INTO tag VALUES (3, 't-shirt', 'clothes');
INSERT INTO tag VALUES (4, 'dell 42', 'monitor');
INSERT INTO tag VALUES (5, 'i-phone to replace my old one', 'phone');
INSERT INTO tag VALUES (6, 'friday evenings dinner', 'dinner');
INSERT INTO tag VALUES (7, 'UCODE', 'salary for month');
INSERT INTO tag VALUES (8, 'put away for future vacation', 'save');

--  dependent tables

-- id, time, description, type, category, tag, wallet)
INSERT INTO transaction VALUES (1, pg_stat_get_snapshot_timestamp(), 'decembers', 'expenses', 5, 7, 1);
INSERT INTO transaction VALUES (2, pg_stat_get_snapshot_timestamp(), 'meal', 'expenses', 1, 5, 1);
INSERT INTO transaction VALUES (3, pg_stat_get_snapshot_timestamp(), 'save', 'expenses', 5, 7, 3);
INSERT INTO transaction VALUES (4, pg_stat_get_snapshot_timestamp(), 'clothes', 'expenses', 2, 3, 1);

INSERT INTO transaction VALUES (5, pg_stat_get_snapshot_timestamp(), 'salary for november', 'income', 7, 7, 1);
INSERT INTO transaction VALUES (6, pg_stat_get_snapshot_timestamp(), 'need $500 more for Maldives', 'income', 7, 8, 2);
INSERT INTO transaction VALUES (7, pg_stat_get_snapshot_timestamp(), 'salary for december', 'income', 7, 7, 1);

