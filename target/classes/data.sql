-- ROLES
insert into rol(id_rol, description, rol_name) values (1, 'all acces', 'admin');
insert into rol(id_rol, description, rol_name) values (2, 'limit acces', 'user');
insert into rol(id_rol, description, rol_name) values (3, 'full acces', 'dba');
	
-- USER
insert into user (created, email, is_active, last_login, modified, name, password, id_user) values ({ts '2012-09-17 18:47:52.69'}, 'jajoo@gmail.com', 'true', {ts '2012-09-17 18:47:52.69'}, {ts '2012-09-17 18:47:52.69'}, 'jhef', '$2a$10$G0OF/lVchQlL2Z3WpuuQJOezocLZVp5A3dYn/iM6gMbUjvPklKbjy', '31d9e447eb974ea9b66896ac44f13081');
insert into user (created, email, is_active, last_login, modified, name, password, id_user) values ({ts '2012-09-17 18:47:52.69'}, 'jabm06@gmail.com', 'true', {ts '2012-09-17 18:47:52.69'}, {ts '2012-09-17 18:47:52.69'}, 'jhef2', '$2a$10$G0OF/lVchQlL2Z3WpuuQJOezocLZVp5A3dYn/iM6gMbUjvPklKbjy', 'f07c16e7535346d98774d77ad3b01cc9');

--USUARIOS_ROLES
insert into usuarios_roles(id_user, id_rol) values ('31d9e447eb974ea9b66896ac44f13081', 1);
insert into usuarios_roles(id_user, id_rol) values ('f07c16e7535346d98774d77ad3b01cc9', 2);

--PHONE
insert into phone (id_phone, city_code, country_code, number, user_id) values ('7dcb398599744ba3895083cc3646931f', 15, 5, 1234567, '31d9e447eb974ea9b66896ac44f13081');
insert into phone (id_phone, city_code, country_code, number, user_id) values ('a0d3e808832e4d9780bea93b6dd610ef', 11, 2, 7654321, 'f07c16e7535346d98774d77ad3b01cc9');

commit;