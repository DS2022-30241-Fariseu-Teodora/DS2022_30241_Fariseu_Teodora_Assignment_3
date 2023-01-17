INSERT INTO site_user(user_id, e_mail, joined_at,password,username, role)
 VALUES('1bc77f46-9cb0-4a66-99d7-b8672f55b87e','co.nt@depro.ba','2022-11-16 13:54:07.097137','deproba','contutz',0),
('23a3cff9-41e1-47c2-a941-05966e66e848','cont@gmail.com','2022-11-06 10:56:48.475131','parola','cont', 0),
('5939c752-3423-4c74-a6c6-908e5813a1ec','life',	'2022-11-06 10:53:54.208415','life','beautiful',0),
('61e8b5fa-e1ef-4ad4-bc60-477f59333f0c','new.year@new.me','2022-11-06 22:18:07.165448','$2a$10$fiMuuNnI4PoPaxFYl2PVL.0IZhDW9.YEqCMkMoQrzl0x7pKvskDzy','newyear',0),
('65687b6b-636b-4797-a3c7-ab82de4c7b5a','user.name@gmail.com','2022-11-06 21:30:37.487613','pass','user',0),	
('4534','ad.min@yahoo.com',	'2022-11-15 16:47:29.94798','admin1234','admin',1 );

INSERT INTO device_model(model_id, max_device_consumption, model_name, serial_number )
 VALUES ('1d476242-a508-45fe-a3d0-73e9c9464e88',5,'Test','SN69420'),
('b0acb953-2017-44b4-8cf5-dc6565622e6c'	,15,'KM1','KM1-PMU2A-FLK'),
('f801e811-f101-4839-a625-b157b219e053',30,	'ADW300','ADW300');

INSERT INTO monitoring_device(device_id,description, address, user_id, model_id)
 VALUES('cdef0efe-6110-498d-be6e-87b070586128','Home','Observatorului Street no 34',	'5939c752-3423-4c74-a6c6-908e5813a1ec',	'f801e811-f101-4839-a625-b157b219e053'),	
('f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'	,null,'Str. Ceahlaului Nr 77','5939c752-3423-4c74-a6c6-908e5813a1ec','b0acb953-2017-44b4-8cf5-dc6565622e6c'),
('52c90c8c-bde7-4ca6-9df1-01a5542dbd6b', 'Here','Bd. Muncii 14','5939c752-3423-4c74-a6c6-908e5813a1ec','1d476242-a508-45fe-a3d0-73e9c9464e88');

INSERT INTO public.energy_consumption(
	entry_id, "timestamp", value_consumed, device_id)
	VALUES ('0', '2022-11-16 00:00:00'::timestamp, 45.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('1', '2022-11-16 01:00:00'::timestamp, 21.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('2', '2022-11-16 02:00:00'::timestamp, 25.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('3', '2022-11-16 03:00:00'::timestamp, 38.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('4', '2022-11-16 04:00:00'::timestamp, 10.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('5', '2022-11-16 05:00:00'::timestamp, 17.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('6', '2022-11-16 06:00:00'::timestamp, 32.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('7', '2022-11-16 07:00:00'::timestamp, 21.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('8', '2022-11-16 08:00:00'::timestamp, 23.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('9', '2022-11-16 09:00:00'::timestamp, 26.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('10', '2022-11-16 10:00:00'::timestamp, 36.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('11', '2022-11-16 11:00:00'::timestamp, 45.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('12', '2022-11-16 12:00:00'::timestamp, 26.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('13', '2022-11-16 13:00:00'::timestamp, 45.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('14', '2022-11-16 14:00:00'::timestamp, 34.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('15', '2022-11-16 15:00:00'::timestamp, 45.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('16', '2022-11-16 16:00:00'::timestamp, 23.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('17', '2022-11-16 17:00:00'::timestamp, 28.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('18', '2022-11-16 18:00:00'::timestamp, 26.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('19', '2022-11-16 19:00:00'::timestamp, 23.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('20', '2022-11-16 20:00:00'::timestamp, 36.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('21', '2022-11-16 21:00:00'::timestamp, 28.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('22', '2022-11-16 22:00:00'::timestamp, 35.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('23', '2022-11-16 23:00:00'::timestamp, 39.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a');

INSERT INTO public.energy_consumption(
	entry_id, "timestamp", value_consumed, device_id)
	VALUES ('0a', '2022-11-16 00:00:00'::timestamp, 21.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('1a', '2022-11-16 01:00:00'::timestamp, 11.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('2a', '2022-11-16 02:00:00'::timestamp, 5.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('3a', '2022-11-16 03:00:00'::timestamp, 8.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('4a', '2022-11-16 04:00:00'::timestamp, 10.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('5a', '2022-11-16 05:00:00'::timestamp, 17.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('6a', '2022-11-16 06:00:00'::timestamp, 21.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('7a', '2022-11-16 07:00:00'::timestamp, 21.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('8a', '2022-11-16 08:00:00'::timestamp, 23.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('9a', '2022-11-16 09:00:00'::timestamp, 26.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('10a', '2022-11-16 10:00:00'::timestamp, 23.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('11a', '2022-11-16 11:00:00'::timestamp, 20.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('12a', '2022-11-16 12:00:00'::timestamp, 16.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('13a', '2022-11-16 13:00:00'::timestamp, 13.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('14a', '2022-11-16 14:00:00'::timestamp, 14.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('15a', '2022-11-16 15:00:00'::timestamp, 15.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('16a', '2022-11-16 16:00:00'::timestamp, 23.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('17a', '2022-11-16 17:00:00'::timestamp, 22.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('18a', '2022-11-16 18:00:00'::timestamp, 26.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('19a', '2022-11-16 19:00:00'::timestamp, 23.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('20a', '2022-11-16 20:00:00'::timestamp, 36.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('21a', '2022-11-16 21:00:00'::timestamp, 28.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('22a', '2022-11-16 22:00:00'::timestamp, 35.0, 'cdef0efe-6110-498d-be6e-87b070586128'),
	('23a', '2022-11-16 23:00:00'::timestamp, 39.0, 'cdef0efe-6110-498d-be6e-87b070586128');

INSERT INTO public.energy_consumption(
	entry_id, "timestamp", value_consumed, device_id)
	VALUES ('0B', '2022-11-15 00:00:00'::timestamp, 23.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('1B', '2022-11-15 01:00:00'::timestamp, 12.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('2B', '2022-11-15 02:00:00'::timestamp, 4.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('3B', '2022-11-15 03:00:00'::timestamp, 12.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('4B', '2022-11-15 04:00:00'::timestamp, 10.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('5B', '2022-11-15 05:00:00'::timestamp, 17.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('6B', '2022-11-15 06:00:00'::timestamp, 22.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('7B', '2022-11-15 07:00:00'::timestamp, 21.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('8B', '2022-11-15 08:00:00'::timestamp, 23.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('9B', '2022-11-15 09:00:00'::timestamp, 26.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('10B', '2022-11-15 10:00:00'::timestamp, 21.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('11B', '2022-11-15 11:00:00'::timestamp, 15.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('12B', '2022-11-15 12:00:00'::timestamp, 26.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('13B', '2022-11-15 13:00:00'::timestamp, 12.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('14B', '2022-11-15 14:00:00'::timestamp, 24.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('15B', '2022-11-15 15:00:00'::timestamp, 25.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('16B', '2022-11-15 16:00:00'::timestamp, 13.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('17B', '2022-11-15 17:00:00'::timestamp, 28.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('18B', '2022-11-15 18:00:00'::timestamp, 26.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('19B', '2022-11-15 19:00:00'::timestamp, 23.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('20B', '2022-11-15 20:00:00'::timestamp, 16.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('21B', '2022-11-15 21:00:00'::timestamp, 28.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('22B', '2022-11-15 22:00:00'::timestamp, 25.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a'),
	('23B', '2022-11-15 23:00:00'::timestamp, 29.0, 'f5c5e688-b259-4dc8-a147-1a3fffc9dc7a');
