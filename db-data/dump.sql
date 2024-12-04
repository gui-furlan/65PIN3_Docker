--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
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
-- Name: atividade; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.atividade (
    id bigint NOT NULL,
    data_final date,
    data_inicio date,
    descricao character varying(255),
    quantidade double precision NOT NULL,
    titulo character varying(255),
    estudante_id bigint NOT NULL,
    tipo_atividade_id bigint NOT NULL,
    status character varying(255)
);


ALTER TABLE public.atividade OWNER TO postgres;

--
-- Name: atividade_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.atividade_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.atividade_seq OWNER TO postgres;

--
-- Name: curso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.curso (
    id bigint NOT NULL,
    titulo character varying(255)
);


ALTER TABLE public.curso OWNER TO postgres;

--
-- Name: curso_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.curso_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.curso_seq OWNER TO postgres;

--
-- Name: estudante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estudante (
    id bigint NOT NULL,
    ano_inicio integer NOT NULL,
    cpf character varying(255),
    nome character varying(255),
    semestre_inicio integer NOT NULL,
    sobrenome character varying(255),
    curso_id bigint,
    usuario_id bigint
);


ALTER TABLE public.estudante OWNER TO postgres;

--
-- Name: estudante_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estudante_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estudante_seq OWNER TO postgres;

--
-- Name: tipo_atividade; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_atividade (
    id bigint NOT NULL,
    categoria character varying(255),
    creditos_por_quantidade double precision NOT NULL,
    maximo_creditos integer NOT NULL,
    nome character varying(255),
    sigla character varying(255),
    CONSTRAINT tipo_atividade_categoria_check CHECK (((categoria)::text = ANY ((ARRAY['ENSINO'::character varying, 'EXTENSAO'::character varying, 'PESQUISA'::character varying, 'ADMINISTRACAO_UNIVERSITARIA'::character varying, 'MISTA'::character varying])::text[])))
);


ALTER TABLE public.tipo_atividade OWNER TO postgres;

--
-- Name: tipo_atividade_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.tipo_atividade ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.tipo_atividade_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id bigint NOT NULL,
    password character varying(255),
    tipo_usuario character varying(255),
    username character varying(255),
    CONSTRAINT usuario_tipo_usuario_check CHECK (((tipo_usuario)::text = ANY ((ARRAY['ESTUDANTE'::character varying, 'SERVIDOR'::character varying])::text[])))
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- Name: usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_seq OWNER TO postgres;

--
-- Data for Name: atividade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.atividade (id, data_final, data_inicio, descricao, quantidade, titulo, estudante_id, tipo_atividade_id, status) FROM stdin;
2	2024-12-01	2024-12-01	Trajado à caráter na Festa Junina CEAVI 2024	1	Festa Junina CEAVI	153	17	Pendente
54	2024-12-30	2023-12-31	Curso de Inglês Udemy	120	Curso de Inglês	153	8	Pendente
62	\N	\N	Teste 3	5	Teste 3	153	3	Pendente
102	2024-08-08	2024-08-04	Participei do SEMESO 2024	1	Participação SEMESO	153	10	Validada
152	2024-12-08	2024-09-08	Ministrei minucurso de Pyhton no SEPEX.	3	Ministrante de minicurso SEPEX	153	25	Validada
\.


--
-- Data for Name: curso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.curso (id, titulo) FROM stdin;
1	Engenharia de Software
\.


--
-- Data for Name: estudante; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.estudante (id, ano_inicio, cpf, nome, semestre_inicio, sobrenome, curso_id, usuario_id) FROM stdin;
153	2022	10046603913	Guilherme	2	Furlan Nunes	1	253
\.


--
-- Data for Name: tipo_atividade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_atividade (id, categoria, creditos_por_quantidade, maximo_creditos, nome, sigla) FROM stdin;
1	ENSINO	6	6	Disciplinas não previstas no currículo pleno	ENS01
2	ENSINO	4	8	Atividades no Programa de Educação Tutorial (PET)	ENS02
3	ENSINO	0.0278	8	Estágio não obrigatório	ENS03
4	ENSINO	2	4	Participação como bolsista ou voluntário em monitoria	ENS04
5	ENSINO	2	6	Projeto de Ensino envolvendo Atividades de Campo	ENS05
6	ENSINO	5	10	Participação em Projetos de Ensino	ENS06
7	ENSINO	6	6	Créditos em Disciplinas Eletivas/Optativas	ENS07
8	ENSINO	0.0556	6	Curso de idiomas	ENS08
9	ENSINO	0.0556	6	Disciplinas realizadas no exterior	ENS09
10	ENSINO	1	4	Participação em eventos de ensino de graduação	ENS10
11	ENSINO	1.5	6	Organização de eventos de ensino	ENS11
12	ENSINO	2	6	Apresentação de trabalhos em eventos	ENS12
13	ENSINO	2	6	Publicação de artigo em revista científica	ENS13
14	ENSINO	8	8	Publicação de Livro/Coletâneas relacionadas ao Ensino	ENS14
15	ENSINO	2	6	Publicação de capítulos em livros dedicados ao Ensino	ENS15
16	EXTENSAO	0.0556	4	Cursos de extensão relacionados ao curso	EXT01
17	EXTENSAO	1	4	Participação em eventos culturais, técnicos, científicos	EXT02
18	EXTENSAO	1.5	6	Organização de evento de extensão	EXT03
19	EXTENSAO	2	6	Apresentação de trabalhos em eventos de extensão	EXT04
20	EXTENSAO	2	6	Publicação de artigo em jornal ou periódico indexado	EXT05
21	EXTENSAO	2	4	Produção de eventos culturais, científicos e artísticos	EXT06
22	EXTENSAO	0.5	5	Trabalhos publicados em Anais de Evento Técnico-Científico	EXT07
23	EXTENSAO	0.25	10	Participação em projeto de extensão	EXT08
24	EXTENSAO	0.05	4	Participação em visitas técnicas	EXT09
25	EXTENSAO	1	4	Ministrante de minicurso ou oficina (>3 horas)	EXT10
26	EXTENSAO	2	6	Ministrante de minicurso (>8 horas)	EXT11
27	PESQUISA	2	6	Artigo publicado em periódico indexado	PES01
28	PESQUISA	8	8	Publicação de Livro	PES02
29	PESQUISA	2	9	Publicação de capítulo de Livro	PES03
30	PESQUISA	0.5	5	Trabalho publicado em Anais de Evento Técnico-Científico	PES04
31	PESQUISA	0.25	5	Publicação de textos em jornal ou revistas	PES05
32	PESQUISA	5	10	Participação em Iniciação Científica ou PIVIC	PES06
33	PESQUISA	0.0556	4	Participação em eventos compatíveis com o curso	PES07
34	PESQUISA	2	6	Palestrante ou ministrante em evento científico	PES08
35	PESQUISA	1	4	Apresentação oral ou pôster em eventos nacionais	PES09
36	PESQUISA	2	4	Prêmios por instituições acadêmicas ou científicas	PES10
37	ADMINISTRACAO_UNIVERSITARIA	2	4	Participação estudantil em Colegiados de Departamento	ADM01
38	ADMINISTRACAO_UNIVERSITARIA	2	4	Participação estudantil no Conselho de Centro	ADM02
39	ADMINISTRACAO_UNIVERSITARIA	2	4	Participação nas Câmaras Técnicas e CONSUNI	ADM03
40	ADMINISTRACAO_UNIVERSITARIA	2	4	Participação em Comissões Permanentes	ADM04
41	ADMINISTRACAO_UNIVERSITARIA	2	4	Participação em ligas acadêmicas	ADM05
42	MISTA	0.0278	8	Atividades realizadas em laboratórios e oficinas	ACM01
43	MISTA	4	10	Participação como bolsista em Programa de Apoio Discente	ACM02
44	MISTA	0.0278	8	Participação em atividades comunitárias	ACM03
45	MISTA	4	4	Participação em órgãos de representação de classe	ACM04
46	MISTA	0.3333	1	Participação em audiências públicas	ACM05
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (id, password, tipo_usuario, username) FROM stdin;
253	teste	ESTUDANTE	10046603913
\.


--
-- Name: atividade_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.atividade_seq', 201, true);


--
-- Name: curso_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.curso_seq', 1, false);


--
-- Name: estudante_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estudante_seq', 201, true);


--
-- Name: tipo_atividade_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_atividade_id_seq', 46, true);


--
-- Name: usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_seq', 301, true);


--
-- Name: atividade atividade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.atividade
    ADD CONSTRAINT atividade_pkey PRIMARY KEY (id);


--
-- Name: curso curso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.curso
    ADD CONSTRAINT curso_pkey PRIMARY KEY (id);


--
-- Name: estudante estudante_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudante
    ADD CONSTRAINT estudante_pkey PRIMARY KEY (id);


--
-- Name: tipo_atividade tipo_atividade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_atividade
    ADD CONSTRAINT tipo_atividade_pkey PRIMARY KEY (id);


--
-- Name: estudante uko8g10ogxcr2ce6k80rfgcrd9e; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudante
    ADD CONSTRAINT uko8g10ogxcr2ce6k80rfgcrd9e UNIQUE (usuario_id);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- Name: atividade fke9fy270xn12kvub7psa4e08f3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.atividade
    ADD CONSTRAINT fke9fy270xn12kvub7psa4e08f3 FOREIGN KEY (tipo_atividade_id) REFERENCES public.tipo_atividade(id);


--
-- Name: estudante fkgydobso33k1e1mhiogt7euwq3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudante
    ADD CONSTRAINT fkgydobso33k1e1mhiogt7euwq3 FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- Name: atividade fkonor8r5pgrqymlc3r94reahrh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.atividade
    ADD CONSTRAINT fkonor8r5pgrqymlc3r94reahrh FOREIGN KEY (estudante_id) REFERENCES public.estudante(id);


--
-- Name: estudante fkq3f7seeu4fkolbfu3je8hiam7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudante
    ADD CONSTRAINT fkq3f7seeu4fkolbfu3je8hiam7 FOREIGN KEY (curso_id) REFERENCES public.curso(id);


--
-- PostgreSQL database dump complete
--
