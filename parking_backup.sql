--
-- PostgreSQL database dump
--

\restrict n0HnJKUhphpqWhfyfVLzxvMCArbJyBhw4xqjps7IVw0LQrqLcyRxIa5BKUuKBgp

-- Dumped from database version 18.1
-- Dumped by pg_dump version 18.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: car; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.car (
    regnumber character varying(255) NOT NULL
);


ALTER TABLE public.car OWNER TO admin;

--
-- Name: parkingoperation; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.parkingoperation (
    regnumofparkingact integer NOT NULL,
    endtime timestamp(6) without time zone,
    parkingnodename character varying(255) NOT NULL,
    placeno character varying(255) NOT NULL,
    starttime timestamp(6) without time zone NOT NULL,
    regnumber character varying(255)
);


ALTER TABLE public.parkingoperation OWNER TO admin;

--
-- Data for Name: car; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.car (regnumber) FROM stdin;
ABC123
XYZ789
DEF456
GHI789
JKL012
MNO345
PQR678
STU901
VWX234
YZA567
NEW456
\.


--
-- Data for Name: parkingoperation; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.parkingoperation (regnumofparkingact, endtime, parkingnodename, placeno, starttime, regnumber) FROM stdin;
2	\N	Shopping Mall	B-22	2024-12-03 09:15:00	XYZ789
4	2024-12-03 18:45:00	Business Center	D-12	2024-12-03 14:20:00	GHI789
5	2024-12-03 15:20:00	City Mall	E-07	2024-12-03 11:30:00	JKL012
6	2024-11-28 17:30:00	Central Parking	F-03	2024-11-28 13:45:00	MNO345
7	2024-11-29 19:45:00	Shopping Mall	G-18	2024-11-29 16:00:00	PQR678
8	2024-11-30 12:30:00	Airport Terminal	H-09	2024-11-30 09:00:00	STU901
9	2024-12-02 14:45:00	Stadium	I-11	2024-12-02 10:15:00	VWX234
10	2024-12-01 16:20:00	Cinema	J-14	2024-12-01 12:30:00	YZA567
11	2024-11-25 18:00:00	Hospital	K-22	2024-11-25 08:00:00	ABC123
12	2024-11-26 17:45:00	University	L-33	2024-11-26 09:30:00	XYZ789
13	2024-12-03 14:45:00	Pharmacy	M-07	2024-12-03 14:00:00	DEF456
14	2024-12-03 17:15:00	Bank	N-11	2024-12-03 16:30:00	GHI789
15	2024-12-02 17:30:00	Tech Park	O-09	2024-12-02 08:30:00	ABC123
16	2024-12-01 15:45:00	Shopping Center	P-14	2024-12-01 11:15:00	ABC123
17	2024-12-01 23:30:00	Restaurant	Q-18	2024-12-01 19:00:00	JKL012
18	2024-11-30 23:45:00	Cinema Complex	R-05	2024-11-30 20:15:00	MNO345
10010	\N	CentralParking	A-01	2024-12-04 13:00:00	ABC123
\.


--
-- Name: car car_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_pkey PRIMARY KEY (regnumber);


--
-- Name: parkingoperation parkingoperation_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.parkingoperation
    ADD CONSTRAINT parkingoperation_pkey PRIMARY KEY (regnumofparkingact);


--
-- Name: parkingoperation fkgmrq3an2beio9r8eo6fj9eob2; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.parkingoperation
    ADD CONSTRAINT fkgmrq3an2beio9r8eo6fj9eob2 FOREIGN KEY (regnumber) REFERENCES public.car(regnumber);


--
-- PostgreSQL database dump complete
--

\unrestrict n0HnJKUhphpqWhfyfVLzxvMCArbJyBhw4xqjps7IVw0LQrqLcyRxIa5BKUuKBgp

