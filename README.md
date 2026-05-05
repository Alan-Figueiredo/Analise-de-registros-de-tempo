# Desafio de Seleção - Análise de Registros de Tempo

Aplicação desenvolvida para processar registros de horas trabalhadas
a partir de um arquivo JSON.<br>
Processando os registros e salvando um resumo analítico em JSON, o projeto é totalmente conteinerizado, garantindo uma saida determinística e sem dependências locais além do Docker.

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

**1. Clone o repositório:**
```bash
git clone https://github.com/Alan-Figueiredo/Analise-de-registros-de-tempo.git
cd Analise-de-registros-de-tempo
```

**2. Execute com Docker Compose:**
```bash
docker compose up --build
```
Ao finalizar, o arquivo `result.json` será gerado automaticamente dentro do container e copiado para a pasta do projeto:

```
   Código-fonte
   data.json
   docker-compose.yml
   Dockerfile
   README.md
   result.json <<
   src/
```

> **Opcional:** caso queira copiar manualmente o arquivo de dentro do container:
> ```bash
> docker cp analise-tempo:/result.json .
> ```

---

## 📦 Funcionalidades Implementadas

A aplicação processa o `data.json` e gera um `result.json` com as seguintes análises:

- **`ignoredRecords`** — quantidade de registros com `minutes <= 0` que foram ignorados
- **`totalMinutes`** — soma total de minutos de todos os registros válidos
- **`tasks`** — total de minutos agrupado por `taskId`
- **`mostWorkedTask`** — tarefa com o maior total de minutos
- **`top3TasksPercentage`** — top 3 tarefas por minutos, com percentual sobre o total geral (2 casas decimais)
- **`top3Employees`** — 3 usuários com maior total de minutos
- **`mostDistinctUserOnTasks`** — usuário que trabalhou no maior número de tarefas distintas (`userId`, `userName`, `distinctTasks`, `taskIds`)

### Regras de ordenação

| Contexto | Critério 1 | Critério 2 (empate) |
|---|---|---|
| Tasks | `totalMinutes` desc | `taskId` asc |
| Funcionários | `totalMinutes` desc | `userId` asc |
| Usuário com mais tarefas distintas | `distinctTasks` desc | `userId` asc |
