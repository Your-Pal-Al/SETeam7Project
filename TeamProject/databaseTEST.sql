--test inserts
INSERT INTO users
	VALUES('alex', aes_encrypt('abcde', 'key'));
INSERT INTO users
	VALUES('jacob', aes_encrypt('abcde', 'key'));
INSERT INTO users
	VALUES('luke', aes_encrypt('abcde', 'key'));
INSERT INTO users
	VALUES('santos', aes_encrypt('abcde', 'key')); 
