create table items
(
	id serial not null,
	name VARCHAR not null
);

create unique index items_id_uindex
	on items (id);

create unique index items_name_uindex
	on items (name);

alter table items
	add constraint items_pk
		primary key (id);

--
create table heroes
(
	id serial not null,
	name VARCHAR not null
);

create unique index heroes_id_uindex
	on heroes (id);

create unique index heroes_name_uindex
	on heroes (name);

alter table heroes
	add constraint heroes_pk
		primary key (id);

--
create table builds
(
	id serial not null,
	name VARCHAR not null,
	hero_id int
		constraint builds_heroes_id_fk
			references heroes,
	abilities varchar(18),
	rune_set_1 varchar,
	rune_set_2 varchar,
	rune_set_3 varchar
);

create unique index builds_id_uindex
	on builds (id);

create unique index builds_name_uindex
	on builds (name);


--
create table build_item
(
	build_id int not null
		constraint build_item_builds_id_fk
			references builds (id),
	item_id int not null
		constraint build_item_items_id_fk
			references items,
	constraint build_item_pk
		primary key (build_id, item_id)
);


