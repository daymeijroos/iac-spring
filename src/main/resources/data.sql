INSERT INTO `admin` (user_id) VALUES ('auth0|63b6de02d53ea3596eb3ae14');

INSERT INTO `shipping_details` (user_id, first_name, last_name, email, country, city, postal_code, address) VALUES ('auth0|63b6de02d53ea3596eb3ae14', 'Day', 'Meijroos', 'DayMeijroos@gmail.com', 'EUROCEANIA', 'Voorburg', '2274TW', 'Van Halewijnlaan 300');

INSERT INTO `category` (name, description) VALUES ('Gathering', 'These are gathering machines');
INSERT INTO `category` (name, description) VALUES ('Transport', 'These are transport machines');
INSERT INTO `category` (name, description) VALUES ('Maintenance', 'These are maintenance machines');
INSERT INTO `category` (name, description) VALUES ('Sport', 'These are sport vehicles');
INSERT INTO `category` (name, description) VALUES ('Upcoming', 'These are sport machines');

INSERT INTO `product` (name, description, price, image_url, category_id) VALUES ('CROFTER', 'The most efficient multi-breed specialised crop harvester for your industry.', 400000000, 'https://www.geek-art.net/wp-content/uploads/2021/01/hamish-frater-bulldoser.jpg', (SELECT `id` FROM `category` WHERE `name` = 'Gathering'));
INSERT INTO `product` (name, description, price, image_url, category_id, filter) VALUES ('MINER', 'The most heavy duty industrial excavating machine for your industry.', 700000000, 'https://www.geek-art.net/wp-content/uploads/2021/01/hamish-frater-machine01-portrait-final.jpg', (SELECT `id` FROM `category` WHERE `name` = 'Gathering'), 'FEATURED');
INSERT INTO `product` (name, description, price, image_url, category_id) VALUES ('LUMBERER', 'The ultimate portable deforestation solution, shiver inducing amongst all green conservativists.', 30000000, 'https://cdna.artstation.com/p/assets/images/images/016/507/676/large/hamish-frater-lumberjack05.jpg', (SELECT `id` FROM `category` WHERE `name` = 'Gathering'));

INSERT INTO `product` (name, description, price, image_url, category_id) VALUES ('BARGE', 'This heavy duty transport ship is meant for cross planet cargo deliveries of interplanetary scale.', 900000000, 'https://www.geek-art.net/wp-content/uploads/2021/01/hamish-frater-flyingbardge03.jpg', (SELECT `id` FROM `category` WHERE `name` = 'Transport'));
INSERT INTO `product` (name, description, price, image_url, category_id, filter) VALUES ('WALKER MINI', 'The walker mini provides the comfort of a walker at a much smaller profile.', 4000000, 'https://www.geek-art.net/wp-content/uploads/2021/01/hamish-frater-20200303-190709.jpg', (SELECT `id` FROM `category` WHERE `name` = 'Transport'), 'FEATURED');
INSERT INTO `product` (name, description, price, image_url, category_id, filter) VALUES ('STROLLER', 'This all round portable storage barge gets your goods from anywhere, to anywhere in a reasonable amount of time.', 1000000, 'https://www.geek-art.net/wp-content/uploads/2021/01/hamish-frater-transportwalker03-planet-sm.jpg', (SELECT `id` FROM `category` WHERE `name` = 'Transport'), 'LANDING');

INSERT INTO `product` (name, description, price, image_url, category_id, filter) VALUES ('SOLDER', 'This all round portable storage barge gets your goods from anywhere, to anywhere in a reasonable amount of time.', 2000000, 'https://www.geek-art.net/wp-content/uploads/2021/01/hamish-frater-gorilla.jpg', (SELECT `id` FROM `category` WHERE `name` = 'Maintenance'), 'FEATURED');
INSERT INTO `product` (name, description, price, image_url, category_id) VALUES ('BUILDER', 'This mobile construction site is meant for those who want their architectural projects finished.', 1800000, 'https://www.geek-art.net/wp-content/uploads/2021/01/hamish-frater-deserttraider.jpg', (SELECT `id` FROM `category` WHERE `name` = 'Maintenance'));
INSERT INTO `product` (name, description, price, image_url, category_id, filter) VALUES ('FUELER', 'This mobile fuel station is the backbone of your project. It is able to fuel all IAC machines.', 2300000, 'https://cdnb.artstation.com/p/assets/images/images/048/905/113/large/hamish-frater-mediumsalvage02-sm.jpg', (SELECT `id` FROM `category` WHERE `name` = 'Maintenance'), 'FEATURED');

INSERT INTO `product` (name, description, price, image_url, category_id) VALUES ('RACER', 'This joyous ride is for speedy business travel. It might also get used for fun on the weekends.', 7500000, 'https://www.geek-art.net/wp-content/uploads/2021/01/hamish-frater-speeder02.jpg', (SELECT `id` FROM `category` WHERE `name` = 'Sport'));

INSERT INTO `product` (name, description, price, image_url, category_id) VALUES ('COOKER', 'This upcoming wonder removes the need for central processing facilities. Bringing material processing to wherever is needed.', 6500000, 'https://cdnb.artstation.com/p/assets/images/images/053/237/045/4k/hamish-frater-cauldron-final.jpg', (SELECT `id` FROM `category` WHERE `name` = 'Upcoming'));