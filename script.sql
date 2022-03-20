create table User
(
	id bigint not null
		primary key,
	email varchar(255) null,
	password varchar(255) null,
	constraint UK_e6gkqunxajvyxl5uctpl2vl2p
		unique (email)
);

create table agency
(
	id bigint not null
		primary key,
	email varchar(255) null,
	password varchar(255) null,
	constraint UK_83gf327dym4y446171hk5q670
		unique (email)
);

create table destination
(
	id bigint not null
		primary key,
	name varchar(255) null
);

create table hibernate_sequence
(
	next_val bigint null
);

create table package
(
	id bigint not null
		primary key,
	availablePlaces int null,
	endDate datetime(6) null,
	extraDetails varchar(255) null,
	name varchar(255) null,
	price double null,
	startDate datetime(6) null,
	destination_id bigint null,
	agency_id bigint null,
	constraint FKmqxwld0cpjbvjo647o5u5r7s2
		foreign key (agency_id) references agency (id),
	constraint FKtq1gn2h0fe7r8ddc34f6tlit1
		foreign key (destination_id) references destination (id)
);

create table bookings
(
	user_id bigint not null,
	package_id bigint not null,
	constraint FKk6at2rbmpwaiqmb7yf98h7un2
		foreign key (package_id) references package (id),
	constraint FKm44r8rjjcwsulo1vugwgcj7qi
		foreign key (user_id) references User (id)
);

