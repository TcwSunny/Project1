CREATE TABLE [exchangeRate](
	name NVARCHAR(50) NOT NULL,
	date DATE NOT NULL,
	buy_cash decimal(10,5),
	buy_spot decimal(10,5),
	sell_cash decimal(10,5),
	sell_spot decimal(10,5)
	CONSTRAINT name_date_PK PRIMARY KEY (name,date)
)

DROP TABLE exchangeRate;

TRUNCATE TABLE exchangeRate;

SELECT * FROM exchangeRate;


