USE escaperoomdb;

INSERT INTO escaperoom (EscapeRoom_name, EscapeRoom_price, EscapeRoom_theme, EscapeRoom_deleted, EscapeRoom_createdAt, EscapeRoom_updatedAt) VALUES
('Mystery Mansion', 50.0, 'Horror', 0, NOW(), NOW()),
('Pirate’s Treasure', 40.0, 'Adventure', 0, NOW(), NOW()),
('Space Odyssey', 60.0, 'Sci-Fi', 0, NOW(), NOW()),
('Haunted Hotel', 55.0, 'Horror', 0, NOW(), NOW());

INSERT INTO game (Game_date, Game_escapeRoomId, Game_finished, Game_deleted) VALUES 
('2025-01-13 14:00:00', 1, 1, 0),
('2025-01-13 15:00:00', 2, 1, 0),
('2025-01-13 16:00:00', 3, 0, 0),
('2025-01-13 17:00:00', 4, 1, 0);

INSERT INTO player (Player_name, Player_email, Player_consentNotif, Player_deleted) VALUES
('Alice', 'alice@example.com', 1, 0),
('Bob', 'bob@example.com', 1, 0),
('Charlie', 'charlie@example.com', 1, 0),
('David', 'david@example.com', 1, 0);

INSERT INTO gamehasplayer (gameHasPlayer_gameId, gameHasPlayer_playerId) VALUES 
(1, 1),
(1, 2),
(2, 3),
(3, 1);

INSERT INTO gift (Gift_gameId, Gift_text, Gift_playerId) VALUES 
(1, 'Gold Medal',1),
(2, 'Mystery Box',2),
(3, 'VIP Pass',3),
(4, 'Extra Time',4);

INSERT INTO certificate (Certificate_gameId, Certificate_text, Certificate_playerId) VALUES 
(1, 'Congratulations on completing the challenge!',1),
(2, 'You have successfully unlocked the secrets of the room!',2),
(3, 'Well done! You have finished the game!',3),
(4, 'Great job! You solved all the puzzles!',1);

INSERT INTO room (Room_name, Room_difficulty, Room_price, Room_escapeRoomId, Room_deleted, Room_createdAt, Room_updatedAt) VALUES
('Library', 'Easy', 10.0, 1, 0, NOW(), NOW()),
('Treasure Chamber', 'Medium', 15.0, 2, 0, NOW(), NOW()),
('Space Lab', 'Hard', 20.0, 3, 0, NOW(), NOW()),
('Haunted Hallway', 'Easy', 12.0, 4, 0, NOW(), NOW());

INSERT INTO objectdeco (ObjectDeco_name, ObjectDeco_material, ObjectDeco_roomId, ObjectDeco_price, ObjectDeco_deleted, ObjectDeco_createdAt, ObjectDeco_updatedAt) VALUES
('Ancient Book', 'Leather', 1, 20.0, 0, NOW(), NOW()),
('Pirate Flag', 'Cloth', 2, 15.0, 0, NOW(), NOW()),
('Space Helmet', 'Metal', 3, 30.0, 0, NOW(), NOW()),
('Creepy Doll', 'Porcelain', 4, 25.0, 0, NOW(), NOW());

INSERT INTO sale (Sale_date, Sale_price, Sale_gameId, Sale_deleted) VALUES
('2025-01-13 14:10:00', 100.0, 1, 0),
('2025-01-13 15:10:00', 80.0, 2, 0),
('2025-01-13 16:10:00', 120.0, 3, 0),
('2025-01-13 17:10:00', 90.0, 4, 0);

INSERT INTO ticket (Ticket_saleId, Ticket_text, Ticket_playerId) VALUES
(1, 'Ticket for Mystery Mansion',1),
(2, 'Ticket for Pirate’s Treasure',2),
(3, 'Ticket for Space Odyssey',3),
(4, 'Ticket for Haunted Hotel',4);

INSERT INTO tips (Tips_text, Tips_Room_id) VALUES
('Check the shelves for hidden clues.', 1),
('Look for patterns in the paintings on the wall.', 2),
('Don’t forget to use the equipment in the room carefully.', 3),
('Pay attention to the sounds in the hallway.', 4);
