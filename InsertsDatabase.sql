USE escaperoomdb;

INSERT INTO escaperoom
(`escaperoom_name`, `escaperoom_price`, `escaperoom_theme`, `escaperoom_deleted`, `escaperoom_createdAt`) 
VALUES 
('Mystery Mansion', 120.50, 'Mystery', 0, NOW()),
('Pirates Cove', 100.00, 'Adventure', 0, NOW()),
('Zombie Apocalypse', 150.75, 'Horror', 0, NOW()),
('Space Odyssey', 180.00, 'Sci-Fi', 0, NOW()),
('Haunted Hotel', 110.00, 'Horror', 0, NOW()),
('Jungle Adventure', 130.50, 'Adventure', 0, NOW()),
('Time Travelers Quest', 160.00, 'Sci-Fi', 0, NOW()),
('Cursed Castle', 140.00, 'Fantasy', 0, NOW()),
('Submarine Escape', 125.75, 'Mystery', 0, NOW()),
('Spy Headquarters', 155.00, 'Thriller', 0, NOW()),
('Superhero Hideout', 145.25, 'Action', 0, NOW()),
('Detectives Office', 135.00, 'Crime', 0, NOW()),
('Ancient Pyramid', 150.00, 'Adventure', 0, NOW()),
('Virtual Reality Escape', 170.00, 'Tech', 0, NOW());


INSERT INTO room
(`room_name`, `room_difficulty`, `room_price`, `room_deleted`, `room_createdAt`) 
VALUES 
('Library of Secrets', 'Intermediate', 60.00, 0, NOW()),
('Treasure Hunt', 'Easy', 50.00, 0, NOW()),
('Survivor\'s Shelter', 'Hard', 70.00, 0, NOW()),
('Galactic Puzzle', 'Expert', 80.00, 0, NOW()),
('Hidden Lab', 'Expert', 85.00, 0, NOW()),
('Pharaohns Tomb', 'Hard', 75.00, 0, NOW()),
('Secret Garden', 'Intermediate', 65.00, 0, NOW()),
('Prison Break', 'Easy', 55.00, 0, NOW()),
('Witches Lair', 'Hard', 80.00, 0, NOW()),
('Lost Island', 'Intermediate', 70.00, 0, NOW()),
('Robot Factory', 'Expert', 90.00, 0, NOW()),
('Dragons Cave', 'Hard', 85.00, 0, NOW()),
('Mafia Den', 'Intermediate', 60.00, 0, NOW()),
('Alien Spaceship', 'Expert', 95.00, 0, NOW());

INSERT INTO escaperoom_has_room
(`escaperoom_escaperoom_id`, `room_room_id`)
VALUES
(1,1),
(2,2),
(3,3),
(4,4),
(5,5),
(6,6),
(7,7),
(8,8),
(9,9),
(10,10),
(1,2),
(2,3),
(3,4),
(4,5),
(5,6),
(6,7),
(7,8),
(8,9),
(9,10),
(10,1);

INSERT INTO tips
(`tips_text`,`tips_deleted`) 
VALUES 
('Look under the rug for hidden clues.',0),
('The clock hands might be a code.',0),
('Check the bookshelf for a hidden key.',0),
('Use the blacklight to reveal hidden messages.',0),
('Pay attention to patterns.',0),
('Think outside the box.',0),
('Work as a team.',0),
('Revisit previously solved clues.',0),
('Double-check locked areas.',0),
('Use your hints wisely.',0),
('Keep track of time.',0),
('Separate used clues.',0),
('Try different combinations.',0),
('Don’t overthink.',0);

INSERT INTO objectdeco
(`objectdeco_name`, `objectdeco_material`, `objectdeco_price`, `objectdeco_deleted`, `objectdeco_createdAt`) 
VALUES 
('Ancient Vase', 'Ceramic', 25.00, 0, NOW()),
('Treasure Chest', 'Wood', 75.00, 0, NOW()),
('Zombie Figure', 'Plastic', 15.00, 0, NOW()),
('Alien Artifact', 'Metal', 50.00, 0, NOW()),
('Crystal Ball', 'Glass', 35.00, 0, NOW()),
('Golden Statue', 'Gold', 200.00, 0, NOW()),
('Enchanted Mirror', 'Glass', 50.00, 0, NOW()),
('Rusty Sword', 'Iron', 40.00, 0, NOW()),
('Vintage Compass', 'Brass', 30.00, 0, NOW()),
('Pirate Hat', 'Cloth', 20.00, 0, NOW()),
('Magic Wand', 'Wood', 15.00, 0, NOW()),
('Treasure Map', 'Paper', 10.00, 0, NOW()),
('Lantern', 'Metal', 25.00, 0, NOW()),
('Old Clock', 'Wood', 45.00, 0, NOW());

INSERT INTO game
(`game_escapeRoomId`, `game_finished`, `game_deleted`, `game_createdAt`) 
VALUES 
(1, 1, 0, NOW()),
(2, 0, 0, NOW()),
(3, 1, 0, NOW()),
(4, 0, 0, NOW()),
(5, 1, 0, NOW()),
(6, 0, 0, NOW()),
(7, 1, 0, NOW()),
(8, 0, 0, NOW()),
(9, 1, 0, NOW()),
(10, 0, 0, NOW());

INSERT INTO player
(`player_name`, `player_email`, `Player_consentNotif`, `player_deleted`, `player_createdAt`) 
VALUES 
('Alice Johnson', 'alice.johnson@example.com', 1, 0, NOW()),
('Bob Smith', 'bob.smith@example.com', 1, 0, NOW()),
('Charlie Brown', 'charlie.brown@example.com', 0, 0, NOW()),
('Dana White', 'dana.white@example.com', 1, 0, NOW()),
('Eve Torres', 'eve.torres@example.com', 1, 0, NOW()),
('Frank Miller', 'frank.miller@example.com', 1, 0, NOW()),
('Grace Harper', 'grace.harper@example.com', 0, 0, NOW()),
('Henry Adams', 'henry.adams@example.com', 1, 0, NOW()),
('Ivy Brooks', 'ivy.brooks@example.com', 1, 0, NOW()),
('Jack Carter', 'jack.carter@example.com', 0, 0, NOW());

INSERT INTO game_has_player
(`game_game_id`, `player_player_id`) 
VALUES 
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(3, 5),
(3, 6),
(3, 7),
(3, 8),
(5,5),
(6,6),
(7,7),
(8,8),
(9,9),
(10,10),
(5,6),
(6,7),
(7,8),
(8,9),
(9,10),
(10,5);

INSERT INTO sale
(`sale_price`, `sale_gameId`, `sale_deleted`, `sale_createdAt`) 
VALUES 
(120.50, 1, 0, NOW()),
(100.00, 2, 0, NOW()),
(150.75, 3, 0, NOW()),
(180.00, 4, 0, NOW()),
(110.00, 5, 0, NOW()),
(130.50, 6, 0, NOW()),
(160.00, 7, 0, NOW()),
(140.00, 8, 0, NOW()),
(125.75, 9, 0, NOW()),
(155.00, 10, 0, NOW());

INSERT INTO ticket 
(`ticket_saleId`, `ticket_text`, `ticket_playerId`, `ticket_createdAt`) 
VALUES 
(1, 'General Admission', 1, NOW()),
(1, 'General Admission', 2, NOW()),
(2, 'VIP Admission', 3, NOW()),
(3, 'General Admission', 4, NOW()),
(5, 'Group Pass', 5, NOW()),
(5, 'Group Pass', 6, NOW()),
(6, 'VIP Entry', 7, NOW()),
(7, 'Early Access', 8, NOW()),
(8, 'General Admission', 9, NOW()),
(9, 'Exclusive Entry', 10, NOW());

INSERT INTO certificate
(`certificate_gameId`, `certificate_text`, `certificate_createdAt`, `player_player_id`) 
VALUES 
(1, 'Completed Mystery Mansion Escape Room!', NOW(), 1),
(2, 'Completed Pirates Cove Escape Room!', NOW(), 3),
(3, 'Completed Zombie Apocalypse Escape Room!', NOW(), 4),
(4, 'Completed Space Odyssey Escape Room!', NOW(), 2),
(5, 'Completed Haunted Hotel!', NOW(), 5),
(6, 'Completed Jungle Adventure!', NOW(), 7),
(7, 'Completed Time Travelers Quest!', NOW(), 8),
(8, 'Completed Cursed Castle!', NOW(), 9),
(9, 'Completed Submarine Escape!', NOW(), 10);


INSERT INTO gift 
(`gift_gameId`, `gift_text`, `gift_key`, `gift_createdAt`,`player_player_id`) 
VALUES 
(1, 'Free Entry Coupon', 'FREE2023', NOW(),1),
(2, 'Discount Voucher', 'DISC15', NOW(), 3),
(3, 'Gift Box', 'GIFTBOX', NOW(), 4),
(4, 'Special Keychain', 'KEY2023', NOW(), 2),
(5, 'Special Discount', 'DISC20', NOW(), 5),
(6, 'VIP Keychain', 'VIPKEY', NOW(), 7),
(7, 'Souvenir T-Shirt', 'TSHIRT', NOW(), 8),
(8, 'Limited Edition Mug', 'MUG2023', NOW(), 9),
(9, 'Exclusive Puzzle', 'PUZZLE', NOW(), 10);

INSERT INTO notification
(`notification_playerId`, `notification_text`, `notification_createdAt`) 
VALUES 
(1, 'Your game starts at 3 PM.', NOW()),
(2, 'Don’t forget to check out the new room!', NOW()),
(3, 'You earned a certificate!', NOW()),
(4, 'Your reward is ready for collection.', NOW()),
(5, 'Reminder: Your game starts soon!', NOW()),
(6, 'Congratulations on completing the room!', NOW()),
(7, 'Your certificate is ready.', NOW()),
(8, 'Claim your reward now.', NOW()),
(9, 'Check out the new escape rooms!', NOW());


INSERT INTO room_has_tips
(`tips_tips_id`, `room_room_id`) 
VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(5,5),
(6,6),
(7,7),
(8,8),
(9,9),
(10,10),
(5,6),
(6,7),
(7,8),
(8,9),
(9,10),
(10,5);


INSERT INTO room_has_objectdeco
(`room_room_id`, `objectdeco_objectdeco_id`) 
VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(5,5),
(6,6),
(7,7),
(8,8),
(9,9),
(10,10),
(5,6),
(6,7),
(7,8),
(8,9),
(9,10),
(10,5);