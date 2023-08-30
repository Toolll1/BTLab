insert into reason(name) values ('Отпуск'),
                                ('Больничный'),
                                ('Прогул');

insert into users (last_name, first_name, patronymic, date_of_bird, email, telephone, passport_series, passport_number, passport_issued, passport_date, passport_kp)
values ('Иванов', 'Иван', 'Иванович', '1985-10-10', 'user1@user.com', '88005553535', '0310', '351351', 'ОУФМС', '2020-10-10', '230003'),
       ('Петров', 'Петр', 'Петрович', '1990-10-10', 'use2r@user.com', '88003333333', '0312', '351352', 'ОУФМС', '2020-10-10', '230003');

insert into truancy(reason, start_date, duration, discounted, description, user_id)
values (1, '2022-10-10', 5, true, 'комментарий1', 1);