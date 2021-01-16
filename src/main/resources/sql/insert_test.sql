\c cashflow;
-- INSERT INTO users VALUES (1, null, 'troh', null, null, 0);

-- non dependent tables

INSERT INTO currency VALUES (1, 'UAN', 30);
INSERT INTO currency VALUES (2, 'USD', 1);
INSERT INTO currency VALUES (3, 'EUR', 0.8);

INSERT INTO wallet VALUES (1, 1020, 'my wallet', 1, 1);
INSERT INTO wallet VALUES (2, 20, 'dollars', 2, 1);
INSERT INTO wallet VALUES (3, 688, 'euro', 3, 1);

INSERT INTO tag VALUES (1, 'asan', 'supermarket');
INSERT INTO tag VALUES (2, 'spain', 'vacation');
INSERT INTO tag VALUES (3, 'courner store', 'small shop');
INSERT INTO tag VALUES (4, 'fly tickets', 'tickets');
INSERT INTO tag VALUES (5, 'food, paper, etc', 'work stuff');
INSERT INTO tag VALUES (6, 'donations', 'giveaway');
INSERT INTO tag VALUES (7, 'work', 'salary');

INSERT INTO category VALUES (1, 'food', 'apple');
INSERT INTO category VALUES (2, '404 bus', 'tickets');
INSERT INTO category VALUES (3, 't-shirt', 'clothes');
INSERT INTO category VALUES (4, 'dell 42', 'monitor');
INSERT INTO category VALUES (5, 'i-phone to replace my old one', 'phone');
INSERT INTO category VALUES (6, 'friday evenings dinner', 'dinner');
INSERT INTO category VALUES (7, 'UCODE', 'salary for month');

--  dependent tables

INSERT INTO transaction VALUES (1, pg_stat_get_snapshot_timestamp(), 'decembers', 'what type is?', 7, 7, 1);
INSERT INTO transaction VALUES (2, pg_stat_get_snapshot_timestamp(), 'meal', 'what type is?', 1, 5, 1);
INSERT INTO transaction VALUES (3, pg_stat_get_snapshot_timestamp(), 'save', 'what type is?', 7, 7, 3);
INSERT INTO transaction VALUES (4, pg_stat_get_snapshot_timestamp(), 'clothes', 'what type is?', 2, 3, 1);