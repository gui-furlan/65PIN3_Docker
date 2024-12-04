FROM postgres:15

# Copiar o dump para o diretório de inicialização do PostgreSQL
COPY dump.sql /docker-entrypoint-initdb.d/dump.sql

# Configurações padrão do PostgreSQL
ENV POSTGRES_DB=pin3
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=password
