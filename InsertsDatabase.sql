USE escaperoomdb;

INSERT INTO escaperoom
(`escaperoom_name`, `escaperoom_price`, `escaperoom_theme`, `escaperoom_deleted`, `escaperoom_createdAt`) 
VALUES 
('Mystery Mansion', 120.50, 'Mystery', 0, NOW()),
('Pirates Cove', 100.00, 'Adventure', 0, NOW()),
('Zombie Apocalypse', 150.75, 'Horror', 0, NOW()),
('Space Odyssey', 180.00, 'Sci-Fi', 0, NOW());

INSERT INTO room
(`room_name`, `room_difficulty`, `room_price`, `room_deleted`, `room_createdAt`) 
VALUES 
('Library of Secrets', 'Intermediate', 60.00, 0, NOW()),
('Treasure Hunt', 'Easy', 50.00, 0, NOW()),
('Survivor\'s Shelter', 'Hard', 70.00, 0, NOW()),
('Galactic Puzzle', 'Expert', 80.00, 0, NOW());

INSERT INTO escaperoom_has_room
(`escaperoom_escaperoom_id`, `room_room_id`)
VALUES
(1,1),
(2,2),
(3,3),
(4,4);

INSERT INTO tips
(`tips_text`,`tips_deleted`) 
VALUES 
('Look under the rug for hidden clues.',0),
('The clock hands might be a code.',0),
('Check the bookshelf for a hidden key.',0),
('Use the blacklight to reveal hidden messages.',0);

INSERT INTO objectdeco
(`objectdeco_name`, `objectdeco_material`, `objectdeco_price`, `objectdeco_deleted`, `objectdeco_createdAt`) 
VALUES 
('Ancient Vase', 'Ceramic', 25.00, 0, NOW()),
('Treasure Chest', 'Wood', 75.00, 0, NOW()),
('Zombie Figure', 'Plastic', 15.00, 0, NOW()),
('Alien Artifact', 'Metal', 50.00, 0, NOW());

INSERT INTO game
(`game_escapeRoomId`, `game_finished`, `game_deleted`, `game_createdAt`) 
VALUES 
(1, 1, 0, NOW()),
(2, 0, 0, NOW()),
(3, 1, 0, NOW()),
(4, 0, 0, NOW());

INSERT INTO player
(`player_name`, `player_email`, `Player_consentNotif`, `player_deleted`, `player_createdAt`) 
VALUES 
('Alice Johnson', 'alice.johnson@example.com', 1, 0, NOW()),
('Bob Smith', 'bob.smith@example.com', 1, 0, NOW()),
('Charlie Brown', 'charlie.brown@example.com', 0, 0, NOW()),
('Dana White', 'dana.white@example.com', 1, 0, NOW());

INSERT INTO game_has_player
(`game_game_id`, `player_player_id`) 
VALUES 
(1, 1),
(1, 2),
(2, 3),
(3, 4);

INSERT INTO sale
(`sale_price`, `sale_gameId`, `sale_deleted`, `sale_createdAt`) 
VALUES 
(120.50, 1, 0, NOW()),
(100.00, 2, 0, NOW()),
(150.75, 3, 0, NOW()),
(180.00, 4, 0, NOW());

INSERT INTO ticket 
(`ticket_saleId`, `ticket_text`, `ticket_playerId`, `ticket_createdAt`) 
VALUES 
(1, 'General Admission', 1, NOW()),
(1, 'General Admission', 2, NOW()),
(2, 'VIP Admission', 3, NOW()),
(3, 'General Admission', 4, NOW());

INSERT INTO certificate
(`certificate_gameId`, `certificate_text`, `certificate_createdAt`, `player_player_id`) 
VALUES 
(1, 'Completed Mystery Mansion Escape Room!', NOW(), 1),
(2, 'Completed Pirates Cove Escape Room!', NOW(), 3),
(3, 'Completed Zombie Apocalypse Escape Room!', NOW(), 4),
(4, 'Completed Space Odyssey Escape Room!', NOW(), 2);

INSERT INTO gift 
(`gift_gameId`, `gift_text`, `gift_key`, `gift_createdAt`,`player_player_id`) 
VALUES 
(1, 'Free Entry Coupon', 'FREE2023', NOW(),1),
(2, 'Discount Voucher', 'DISC15', NOW(), 3),
(3, 'Gift Box', 'GIFTBOX', NOW(), 4),
(4, 'Special Keychain', 'KEY2023', NOW(), 2);

INSERT INTO notification
(`notification_playerId`, `notification_text`, `notification_createdAt`) 
VALUES 
(1, 'Your game starts at 3 PM.', NOW()),
(2, 'Don’t forget to check out the new room!', NOW()),
(3, 'You earned a certificate!', NOW()),
(4, 'Your reward is ready for collection.', NOW());

INSERT INTO room_has_tips
(`tips_tips_id`, `room_room_id`) 
VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4);

INSERT INTO room_has_objectdeco
(`room_room_id`, `objectdeco_objectdeco_id`) 
VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4);
