
create table coffee_type
(
    id             serial primary key not null,
    disabled       boolean            not null,
    grade_name_eng text,
    grade_name_ru  text,
    price          decimal
);


