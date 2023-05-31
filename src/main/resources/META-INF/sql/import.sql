insert into institutions(description, name) VALUES ('Cel i misja: Pomoc dzieciom z ubogich rodzin.', 'Fundacja "Dbam o Zdrowie2"');
insert into institutions(description, name) VALUES ('Cel i misja: Pomoc wybudzaniu dzieci ze śpiączki.', 'Fundacja "A kogo2"');
insert into institutions(description, name) VALUES ('Cel i misja: Pomoc dla osób nie posiadających miejsca zamieszkania', 'Fundacja “Bez domu2”');
insert into institutions(description, name) VALUES ('Cel i misja: Pomoc osobom znajdującym się w trudnej sytuacji życiowej.', 'Fundacja “Dla dzieci2"');

insert into donations(city,
                      pick_up_comment,
                      pick_up_date,
                      pick_up_time,
                      quantity,
                      street,
                      zip_code,
                      institution_id) values (
                                              'Warszawa',
                                              'no comment',
                                              '2023-06-12',
                                              '10:00:00',
                                              1,
                                              'Kleczewska',
                                              '01-975',
                                              2
                                             );

insert into donations(city,
                      pick_up_comment,
                      pick_up_date,
                      pick_up_time,
                      quantity,
                      street,
                      zip_code,
                      institution_id) values (
                                                 'Warszawa',
                                                 'no comment',
                                                 '2023-06-11',
                                                 '16:00:00',
                                                 2,
                                                 'Kleczewska',
                                                 '01-975',
                                                 1
                                             )
insert into donations(city,
                      pick_up_comment,
                      pick_up_date,
                      pick_up_time,
                      quantity,
                      street,
                      zip_code,
                      institution_id) values (
                                                 'Warszawa',
                                                 'no comment',
                                                 '2023-06-01',
                                                 '16:00:00',
                                                 2,
                                                 'Kleczewska',
                                                 '01-975',
                                                 3
                                             )



