create table coffee_type
(
    id             serial primary key not null,
    disabled       boolean            not null,
    grade_name_eng text not null ,
    grade_name_ru  text not null ,
    price          decimal
);

insert into coffee_type
    (disabled, grade_name_eng, grade_name_ru, price)
values ('false', 'Espresso', 'Эспрессо', '1');

insert into coffee_type
    (disabled, grade_name_eng, grade_name_ru, price)
values ('false', 'Americano', 'Американо', '2');

insert into coffee_type
    (disabled, grade_name_eng, grade_name_ru, price)
values ('false', 'Latte', 'Латте', '2');

insert into coffee_type
    (disabled, grade_name_eng, grade_name_ru, price)
values ('true', 'Cappuccino', 'Капучино', '4');

insert into coffee_type
    (disabled, grade_name_eng, grade_name_ru, price)
values ('false', 'Mocha', 'Моккачино', '4');

create table "order"
(
    id               serial primary key not null,
    order_date_time  timestamp without time zone,
    customer_name    text not null,
    delivery_address text,
    delivery_type    text not null,
    full_order_price decimal,
    status           text not null
);


create table order_item
(
    id             serial primary key not null,
    coffee_type_id integer            not null,
    cup_counter    integer            not null,
    order_id       integer            not null,
    foreign key (coffee_type_id) references coffee_type (id),
    foreign key (order_id) REFERENCES "order" (id),
    full_price     decimal
);