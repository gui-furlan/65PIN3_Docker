# Etapa 1: Build do frontend
FROM node:18 AS builder
WORKDIR /app

# Instalar dependências
COPY package.json package-lock.json ./
RUN npm install

# Copiar arquivos do projeto
COPY . ./
RUN npm run build

# Etapa 2: Configurar o NGINX
FROM nginx:latest
WORKDIR /usr/share/nginx/html
RUN rm -rf ./*
COPY --from=builder /app/dist ./
EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]
