--test inserts
INSERT INTO users
	VALUES('alex', aes_encrypt('abcde', 'key'),0,0);
INSERT INTO users
	VALUES('jacob', aes_encrypt('abcde', 'key'),0,0);
INSERT INTO users
	VALUES('santos', aes_encrypt('abcde', 'key'),0,0); 
