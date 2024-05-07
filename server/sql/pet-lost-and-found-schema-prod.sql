drop database if exists pet_lost_and_found;
create database pet_lost_and_found;
use pet_lost_and_found;

-- tables
create table `user`(
    user_id int primary key auto_increment,
    `name` varchar(25) not null,
    phone varchar(250) not null,
    email varchar(255) not null
);
create table location(
	location_id int primary key auto_increment,
    address varchar(250) not null,
    city varchar(250) not null,
    state varchar(100) not null,
    zip_code varchar(20) not null
);

create table animal(
	animal_id int primary key auto_increment,
    `name` varchar(250),
    characteristics varchar(250) not null,
    animal varchar(100) not null,
    breed varchar(100) 
);

create table post(
	post_id int primary key auto_increment,
    animal_id int,
    user_id int not null,
    img_url varchar(1000) not null,
	`description` varchar(500) not null,
    `time` datetime,
    location_id int,
    gender varchar(10),
    size int,
    `found` boolean,
    constraint fk_animal_id
		foreign key(animal_id)
        references animal(animal_id),
	constraint fk_user_id
		foreign key(user_id)
        references user(user_id),
	constraint fk_location_id
		foreign key(location_id)
        references location(location_id)
);

-- data
insert into `user`(user_id, `name`, phone, email) values
	(1, 'Muffy Meyer', '555-555-5555', 'muffy@fakeemail.com'),
	(2, 'John Doe', '555-123-4567', 'john.doe@example.com'),
    (3, 'Samantha Right', '555-987-6543', 's.right@example.com'),
    (4, 'Lucas Gray', '555-876-5432', 'lucas.gray@example.com'),
    (5, 'Emily Johnson', '555-234-5678', 'emily.j@example.com'),
    (6, 'Carlos Smith', '555-345-6789', 'carlos.s@example.com'),
    (7, 'Lisa Ray', '555-456-7890', 'lisa.ray@example.com'),
    (8, 'Nina Patel', '555-567-8901', 'nina.patel@example.com');

insert into location(location_id, address, city, state, zip_code) values
	(1, '123 Maple Street', 'Springfield', 'IL', '62704'),
    (2, '456 Oak Avenue', 'Madison', 'WI', '53703'),
    (3, '789 Pine Lane', 'Phoenix', 'AZ', '85001'),
    (4, '1012 Birch Road', 'Austin', 'TX', '78701'),
    (5, '1314 Elm Street', 'Orlando', 'FL', '32801'),
    (6, '1516 Cedar Blvd', 'Seattle', 'WA', '98101'),
    (7, '1718 Willow Way', 'Denver', 'CO', '80202');
    
insert into animal(animal_id, `name`, characteristics, animal, breed) values
    (1, 'Buddy', 'Friendly, loves children', 'Dog', 'Golden Retriever'),
    (2, 'Whiskers', 'Shy, has a missing tail', 'Cat', 'Siamese'),
    (3, 'Coco', 'Very energetic, loves to play fetch', 'Dog', 'Border Collie'),
    (4, 'Daisy', 'Calm and loving', 'Dog', 'Beagle'),
    (5, 'Spike', 'Loud and protective', 'Dog', 'German Shepherd'),
    (6, 'Ginger', 'Affectionate, loves to cuddle', 'Cat', 'Persian'),
    (7, 'Shadow', 'Independent, night prowler', 'Cat', 'Maine Coon');

insert into post(post_id, animal_id, user_id, img_url, `description`, `time`, location_id, gender, size, `found`) values
    (1, 2, 2, 'https://example.com/images/whiskers.jpg', 'Found this cat in a garage, seems lost.', '2023-04-25 09:15:00', 2, 'Female', 10, true),
    (2, 3, 3, 'https://example.com/images/coco.jpg', 'Coco went missing during our walk in the neighborhood.', '2023-04-30 16:45:00', 3, 'Male', 20, false),
    (3, 4, 4, 'https://example.com/images/daisy.jpg', 'Spotted this dog wandering alone by the riverside.', '2023-05-02 08:00:00', 4, 'Female', 25, true),
    (4, 5, 5, 'https://example.com/images/spike.jpg', 'Our beloved Spike has been missing since last Friday.', '2023-05-01 19:30:00', 5, 'Male', 30, false),
    (5, 6, 6, 'https://example.com/images/ginger.jpg', 'Found a friendly cat in the park, looking for owner.', '2023-05-03 13:20:00', 6, 'Female', 12, true),
    (6, 7, 7, 'https://example.com/images/shadow.jpg', 'Shadow has not returned home since yesterday evening.', '2023-05-04 17:50:00', 7, 'Male', 15, false),
    (7, 1, 8, 'https://example.com/images/buddy2.jpg', 'Just found wandering near downtown. Is he yours?', '2023-05-05 12:00:00', 1, 'Male', 30, true);