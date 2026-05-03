# Desafio de Seleção - Análise de Registros de Tempo

Aplicação em Java 21 desenvolvida para processar registros de timesheet (horas trabalhadas) a partir de um arquivo JSON. O projeto é totalmente conteinerizado, garantindo uma execução determinística e sem dependências locais além do Docker.

## 🚀 Tecnologias Utilizadas

*   **Java 21**: Linguagem principal.
*   **Maven**: Gerenciamento de dependências e build (`maven-shade-plugin`).
*   **Jackson**: Biblioteca para processamento de JSON (`jackson-databind`).
*   **Docker & Docker Compose**: Para conteinerização e orquestração.

## 📋 Pré-requisitos

Para rodar o projeto, você precisará ter instalado em sua máquina:
*   [Docker](https://docs.docker.com/get-docker/)
*   [Docker Compose](https://docs.docker.com/compose/install/)

## ⚙️ Como Executar

A aplicação foi projetada para ser executada com um único comando, sem a necessidade de passos manuais de compilação.

1. Clone este repositório:
   ```bash
   git clone https://github.com/Alan-Figueiredo/Analise-de-registros-de-tempo.git
   cd Nome da pasta

2. Entre na pasta e execute o seguinte comando:
    ```bash
    docker compose up --build
3. Aguardar o arquivo result.json aparecer na pasta do projeto:
    ```bash
   Código-fonte
   data.json
   docker-compose.yml
   Dockerfile
   README.md
   result.json <<
   