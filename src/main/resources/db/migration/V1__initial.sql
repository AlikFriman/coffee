-- Сорт кофе

create table coffee_type
(
    id        serial primary key not null,
    available boolean            not null,
    name_eng  text               not null,
    name_ru   text               not null,
    price     decimal
);

-- Заказ

create table "order"
(
    id               serial primary key not null,
    date_time        timestamp without time zone,
    customer_name    text               not null,
    delivery_address text,
    delivery_type    text               not null,
    sum              decimal,
    status           text               not null
);

-- Позиция заказа

create table order_item
(
    id             serial primary key not null,
    coffee_type_id integer            not null,
    count          integer            not null,
    order_id       integer            not null,
    sum            decimal,
    foreign key (coffee_type_id) references coffee_type (id),
    foreign key (order_id) REFERENCES "order" (id)
);

-- Заполнение списка сортов кофе

insert into coffee_type
    (available, name_eng, name_ru, price)
values ('true', 'Espresso', 'Эспрессо', '1');

insert into coffee_type
    (available, name_eng, name_ru, price)
values ('true', 'Americano', 'Американо', '2');

insert into coffee_type
    (available, name_eng, name_ru, price)
values ('true', 'Latte', 'Латте', '2');

insert into coffee_type
    (available, name_eng, name_ru, price)
values ('false', 'Cappuccino', 'Капучино', '4');

insert into coffee_type
    (available, name_eng, name_ru, price)
values ('true', 'Mocha', 'Моккачино', '4');
