CREATE TABLE public.tb_customers (
	id int8 NOT NULL DEFAULT nextval('tb_pessoa_id_seq'::regclass),
	"name" varchar(255) NULL,
	CONSTRAINT tb_pessoa_pkey PRIMARY KEY (id)
);

INSERT INTO public.tb_customers (id,name) VALUES(1,'Teste 01');
INSERT INTO public.tb_customers (id,name) VALUES(2,'Teste 02');
INSERT INTO public.tb_customers (id,name) VALUES(3,'Teste 03');
INSERT INTO public.tb_customers (id,name) VALUES(4,'Teste 04');
INSERT INTO public.tb_customers (id,name) VALUES(5,'Teste 05');
INSERT INTO public.tb_customers (id,name) VALUES(6,'Teste 06');
INSERT INTO public.tb_customers (id,name) VALUES(7,'Teste 07');
