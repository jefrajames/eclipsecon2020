--  Load some test data

--  Merchant 1
insert into merchant (brandname, businessactivity, contractstatus, joindate, city, country, number, street, scoreenabled, scoreid, servicelevel, website) values ('Chez Marcel', 'RESTAURANT', 'ENABLED', '1979-12-12', 'New York City', 'USA', '5', 'Broadway', true, 'ms-1', 'PREMIUM', 'http://www.restaurantmarcel.fr/')

-- Card Schemes for Merchant 1
insert into merchant_cardschemes (merchant_id, cardschemes) values (1, 'AMEX')
insert into merchant_cardschemes (merchant_id, cardschemes) values (1, 'VISA')
insert into merchant_cardschemes (merchant_id, cardschemes) values (1, 'CB')
insert into merchant_cardschemes (merchant_id, cardschemes) values (1, 'JCB')
insert into merchant_cardschemes (merchant_id, cardschemes) values (1, 'UNIONPAY')
insert into merchant_cardschemes (merchant_id, cardschemes) values (1, 'MASTERCARD')

-- Payment Channels for Merchant 1
insert into merchant_paymentchannels (merchant_id, paymentchannels) values (1, 'IN_STORE')
insert into merchant_paymentchannels (merchant_id, paymentchannels) values (1, 'ON_LINE')
insert into merchant_paymentchannels (merchant_id, paymentchannels) values (1, 'MOBILE')

-- Contacts of Merchant 1
insert INTO merchant_contacts (merchant_id, emailaddresses, name, phonenumbers) VALUES (1, 'marcel@gmail.com', 'Marcel Dupont', '12-34-56-78-90')
insert INTO merchant_contacts (merchant_id, emailaddresses, name, phonenumbers) VALUES (1, 'mauricette@hotmail.com', 'Mauricette Dupont', '12-56-78-90-34')
insert INTO merchant_contacts (merchant_id, emailaddresses, name, phonenumbers) VALUES (1, 'thomas@yahoo.com', 'Thomas Dupont', '16-52-78-90-34')


-- Merchant 2
insert into merchant (brandname, businessactivity, contractstatus, joindate, city, country, number, street, scoreenabled, scoreid, servicelevel, website) values ('Labbe Bookstore', 'BOOKSTORE', 'ENABLED', '2002-05-05', 'Blois', 'France', '2', 'rue Porte Chartraine', true, 'ms-2', 'STANDARD', 'https://librairie-labbe.business.site/')

-- Card Schemes for Merchant 2
insert into merchant_cardschemes (merchant_id, cardschemes) values (2, 'MASTERCARD')
insert into merchant_cardschemes (merchant_id, cardschemes) values (2, 'VISA')
insert into merchant_cardschemes (merchant_id, cardschemes) values (2, 'CB')

-- Payment Channels for Merchant 2
insert into merchant_paymentchannels (merchant_id, paymentchannels) values (2, 'IN_STORE')

-- Contacts of Merchant 2
insert INTO merchant_contacts (merchant_id, emailaddresses, name, phonenumbers) VALUES (2, 'labbe@laposte.net', 'Philippe Labbe', '34-56-78-90-12')
insert INTO merchant_contacts (merchant_id, emailaddresses, name, phonenumbers) VALUES (2, 'gegelab@laposte.net', 'Gerard Labbe', '78-56-34-90-12')

-- Merchant 3
insert into merchant (brandname, businessactivity, contractstatus, joindate, city, country, number, street, scoreenabled, scoreid, servicelevel, website) values ('Chinese Theatre', 'THEATER', 'ENABLED', '2012-06-05', 'Los Angeles', 'USA', '2', 'Main Street', true, 'ms-3', 'PREMIUM', 'http://www.tclchinesetheatres.com/')

-- Card Schemes for Merchant 3
insert into merchant_cardschemes (merchant_id, cardschemes) values (3, 'MASTERCARD')
insert into merchant_cardschemes (merchant_id, cardschemes) values (3, 'VISA')
insert into merchant_cardschemes (merchant_id, cardschemes) values (3, 'JCB')

-- Payment Channels for Merchant 3
insert into merchant_paymentchannels (merchant_id, paymentchannels) values (3, 'IN_STORE')
insert into merchant_paymentchannels (merchant_id, paymentchannels) values (3, 'MOBILE')
insert into merchant_paymentchannels (merchant_id, paymentchannels) values (3, 'ON_LINE')

-- Contacts of Merchant 3
insert INTO merchant_contacts (merchant_id, emailaddresses, name, phonenumbers) VALUES (3, 'jcvd@hotmail.com', 'JCVD', '37-56-08-90-12')
insert INTO merchant_contacts (merchant_id, emailaddresses, name, phonenumbers) VALUES (3, 'chuckno@hotmail.com', 'Chuck Norris', '77-56-08-95-12')

 -- Merchant 4
 insert into merchant (brandname, businessactivity, contractstatus, joindate, city, country, number, street, scoreenabled, scoreid, servicelevel, website) values ('Sport pour tous', 'SPORT', 'ENABLED', '2018-06-11', 'Montreal', 'Canada', '171', 'Bernard Ouest', true, 'ms-4', 'BASIC', 'http://www.sportpourtous.ca/')
 
 -- Card Schemes for Merchant 4
 insert into merchant_cardschemes (merchant_id, cardschemes) values (4, 'MASTERCARD')
 insert into merchant_cardschemes (merchant_id, cardschemes) values (4, 'VISA')
 
 -- Payment Channels for Merchant 4
 insert into merchant_paymentchannels (merchant_id, paymentchannels) values (4, 'IN_STORE')
 
 -- Contacts of Merchant 4
 insert INTO merchant_contacts (merchant_id, emailaddresses, name, phonenumbers) VALUES (4, 'raypoul@hotmail.com', 'Raymond Poulidor', '87-66-18-90-12')
 
 -- Merchant 5
 insert into merchant (brandname, businessactivity, contractstatus, joindate, city, country, number, street, scoreenabled, scoreid, servicelevel, website) values ('What else', 'CLOTHING', 'ENABLED', '2028-06-01', 'Paris', 'France', '5', 'Rivoli', true, 'ms-5', 'PREMIUM', 'http://www.whatelse.com/')
 
 -- Card Schemes for Merchant 5
 insert into merchant_cardschemes (merchant_id, cardschemes) values (5, 'MASTERCARD')
 insert into merchant_cardschemes (merchant_id, cardschemes) values (5, 'VISA')
 insert into merchant_cardschemes (merchant_id, cardschemes) values (5, 'CB')
 
 -- Payment Channels for Merchant 5
 insert into merchant_paymentchannels (merchant_id, paymentchannels) values (5, 'IN_STORE')
 insert into merchant_paymentchannels (merchant_id, paymentchannels) values (5, 'ON_LINE')
 
 -- Contacts of Merchant 5
 insert INTO merchant_contacts (merchant_id, emailaddresses, name, phonenumbers) VALUES (5, 'gclou@gmail.com', 'Georges Clooney', '87-96-08-40-12')
