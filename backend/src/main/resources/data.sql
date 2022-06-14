
insert into tbl_role (id, name) values ('2f1e573f-6290-4581-b54b-7e3ba2c98832', 'STUDENT');
insert into tbl_role (id, name) values ('0629910c-f847-4672-a11c-a80718f18026', 'TEACHER');

insert into tbl_authority (id, name) values ('aebe836e-83a6-4be7-84c5-1eda33a933fe', 'READ');
insert into tbl_authority (id, name) values ('dc1cb5ac-e142-4b6a-aa87-ce509cc7b5f0', 'CREATE');
insert into tbl_authority (id, name) values ('a0f02331-1baa-4428-984b-5cd4328f6f7b', 'UPDATE');
insert into tbl_authority (id, name) values ('c02e84f2-f069-483a-bd47-85833e7d1334', 'DELETE');

insert into tbl_role_authorities (role_id, authority_id) values ('0629910c-f847-4672-a11c-a80718f18026', 'aebe836e-83a6-4be7-84c5-1eda33a933fe');
insert into tbl_role_authorities (role_id, authority_id) values ('0629910c-f847-4672-a11c-a80718f18026', 'dc1cb5ac-e142-4b6a-aa87-ce509cc7b5f0');
insert into tbl_role_authorities (role_id, authority_id) values ('0629910c-f847-4672-a11c-a80718f18026', 'a0f02331-1baa-4428-984b-5cd4328f6f7b');
insert into tbl_role_authorities (role_id, authority_id) values ('0629910c-f847-4672-a11c-a80718f18026', 'c02e84f2-f069-483a-bd47-85833e7d1334');
insert into tbl_role_authorities (role_id, authority_id) values ('2f1e573f-6290-4581-b54b-7e3ba2c98832', 'aebe836e-83a6-4be7-84c5-1eda33a933fe');

insert into tbl_user (id, username, email, password) values ('e74d2176-e7d7-4003-8543-369057a4279e', 'andrin', 'andrin@admin.com', '$2a$12$v9oHZkO7Sck8bQCtxn9s0OhqOxGS8d5ltc0J/mEzYXUQAey/qHeQO');
insert into tbl_user (id, username, email, password) values ('2c61dac7-29fa-4baa-95e5-9053c7879d41', 'anton', 'anton@admin.com', '$2a$12$Dz0YdddwYhFC9YSwWNEyI.wGbgI.8ZgIPz20OCThxqZQFOXvw3Cu6');
insert into tbl_user (id, username, email, password) values ('9da0c2ba-98c5-4504-821a-259942a298b2', 'bob', 'bob@user.com', '$2a$12$zP9Pmnx1C5gqqGUcc.WnmeDbeJcJUsBZtj1BXSutyNuhKhVqrCrFO');

insert into tbl_user_role (user_id, role_id) values ('e74d2176-e7d7-4003-8543-369057a4279e', '0629910c-f847-4672-a11c-a80718f18026');
insert into tbl_user_role (user_id, role_id) values ('2c61dac7-29fa-4baa-95e5-9053c7879d41', '2f1e573f-6290-4581-b54b-7e3ba2c98832');

