# Spring Fantasy - Sistema de Fantasy Football

Sistema de futebol fantasia desenvolvido em Spring Boot, inspirado no Cartola FC. Permite criar times fantasia, escalar jogadores reais do Brasileirão e competir com outros usuários através de um sistema de pontuação por rodada.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Security** com autenticação JWT
- **Spring Data JPA** com Hibernate
- **PostgreSQL**
- **Swagger/OpenAPI 3** para documentação
- **Docker & Docker Compose** para containerização
- **Maven** para gerenciamento de dependências

## Funcionalidades Principais

- **Autenticação JWT** - Sistema completo de login/registro
- **Gestão de Usuários** - Perfis USER e ADMIN
- **Base de Dados Completa** - 20 clubes e 500+ jogadores reais do Brasileirão
- **Times Fantasia** - Criação e gerenciamento de times personalizados
- **Escalação Tática** - Sistema com 5 formações diferentes (3-4-3, 3-5-2, 4-3-3, 4-4-2, 4-5-1)
- **Sistema de Pontuação** - Pontuação por rodada baseada no desempenho real
- **Classificação** - Ranking geral dos times por pontuação acumulada
- **Controle de Rodadas** - Sistema de mercado aberto/fechado
- **Painel Administrativo** - Gestão completa de jogadores, clubes e pontuações

## Como Executar com Docker

### Pré-requisitos
- Docker e Docker Compose instalados
- Porta 8080 e 5433 livres

### Execução Rápida
```bash
# Clone o repositório
git clone <url-do-repositorio>
cd Spring-Fantasy

# Execute com Docker Compose (um comando apenas!)
docker-compose up -d

# Aguarde alguns segundos e acesse:
# Aplicação: http://localhost:8080
# Swagger UI: http://localhost:8080/swagger-ui/index.html
```

### Logs e Monitoramento
```bash
# Ver logs da aplicação
docker-compose logs -f app

# Ver logs do banco
docker-compose logs -f postgres

# Parar os containers
docker-compose down

# Parar e remover dados (CUIDADO!)
docker-compose down -v
```

## Credenciais de Acesso

O sistema já vem com usuários pré-cadastrados:

### Administrador
- **Email:** `admin@springfantasy.com`
- **Senha:** `admin123`
- **Permissões:** Acesso total (rotas /admin/*)

### Usuário Comum
- **Email:** `joao@email.com`
- **Senha:** `123456`
- **Permissões:** Criar e gerenciar time fantasia

## API Reference - Guia Completo

### Base URL
```
http://localhost:8080
```

---

## Rotas Públicas (Sem Autenticação)

### Autenticação (`/auth`)

#### Registrar Usuário
```http
POST /auth/register
Content-Type: application/json

{
  "nome": "Novo Usuário",
  "email": "usuario@email.com", 
  "senha": "minhasenha"
}
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "usuario": {
    "id": 3,
    "nome": "Novo Usuário",
    "email": "usuario@email.com",
    "role": "USER"
  }
}
```

#### Fazer Login
```http
POST /auth/login
Content-Type: application/json

{
  "email": "admin@springfantasy.com",
  "senha": "admin123"
}
```

### Clubes (`/clubes`)

#### Listar Todos os Clubes
```http
GET /clubes
```

**Resposta:**
```json
[
  {"id": 1, "nome": "Atlético-MG"},
  {"id": 2, "nome": "Botafogo"},
  {"id": 3, "nome": "Ceará"}
]
```

### Jogadores (`/jogadores`)

#### Listar Todos os Jogadores
```http
GET /jogadores
```

#### Filtrar Jogadores por Posição
```http
GET /jogadores?posicao=ATA
```

#### Filtrar Jogadores por Clube
```http
GET /jogadores?clubeId=1
```

#### Filtrar por Posição E Clube
```http
GET /jogadores?posicao=GOL&clubeId=6
```

#### Buscar Jogador Específico
```http
GET /jogadores/1
```

**Exemplo de Resposta:**
```json
{
  "id": 1,
  "nome": "Hulk",
  "posicao": "ATA", 
  "preco": 12.00,
  "clube": {"id": 1, "nome": "Atlético-MG"}
}
```

### Rodadas (`/rodadas`)

#### Obter Rodada Atual
```http
GET /rodadas/atual
```

**Resposta:**
```json
{
  "id": 1,
  "numero": 1,
  "status": "MERCADO_ABERTO"
}
```

### Classificação (`/classificacao`)

#### Ver Classificação Geral
```http
GET /classificacao
```

**Resposta:**
```json
[
  {
    "posicao": 1,
    "timeFantasia": {
      "id": 1,
      "nome": "Time do João",
      "usuario": {"nome": "João Silva"}
    },
    "pontuacaoTotal": 245.5
  }
]
```

#### Status da Temporada
```http
GET /classificacao/temporada/status
```

---

## Rotas Protegidas (Requer Token JWT)

**Para todas as rotas abaixo, inclua o header:**
```http
Authorization: Bearer seu_token_jwt_aqui
```

### Times Fantasia (`/times-fantasia`)

#### Criar Meu Time Fantasia
```http
POST /times-fantasia
Authorization: Bearer <token>
Content-Type: application/json

{
  "nome": "Meu Time dos Sonhos"
}
```

#### Ver Meu Time Atual
```http
GET /times-fantasia/me
Authorization: Bearer <token>
```

**Resposta:**
```json
{
  "id": 1,
  "nome": "Meu Time dos Sonhos",
  "usuario": {"nome": "João Silva"},
  "escalacao": [
    {
      "jogador": {
        "id": 3,
        "nome": "Everson",
        "posicao": "GOL",
        "clube": {"nome": "Atlético-MG"}
      }
    }
  ],
  "formacao": "4-3-3",
  "pontuacaoTotal": 123.5
}
```

#### Atualizar Escalação
```http
PUT /times-fantasia/me/escalacao?formacao=F_4_3_3
Authorization: Bearer <token>
Content-Type: application/json

[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
```

**Formações Disponíveis:**
- `F_3_4_3` → 3-4-3 (1 GOL, 3 DEF, 4 MEI, 3 ATA)
- `F_3_5_2` → 3-5-2 (1 GOL, 3 DEF, 5 MEI, 2 ATA)  
- `F_4_3_3` → 4-3-3 (1 GOL, 4 DEF, 3 MEI, 3 ATA)
- `F_4_4_2` → 4-4-2 (1 GOL, 4 DEF, 4 MEI, 2 ATA)
- `F_4_5_1` → 4-5-1 (1 GOL, 4 DEF, 5 MEI, 1 ATA)

---

## Rotas Administrativas (ADMIN apenas)

**Requer token de usuário ADMIN:**

### Gerenciar Clubes (`/admin/clubes`)

#### Adicionar Novo Clube
```http
POST /admin/clubes
Authorization: Bearer <admin_token>
Content-Type: application/json

{
  "nome": "Novo Clube FC"
}
```

### Gerenciar Jogadores (`/admin/jogadores`)

#### Adicionar Novo Jogador
```http
POST /admin/jogadores
Authorization: Bearer <admin_token>
Content-Type: application/json

{
  "nome": "Cristiano Ronaldo",
  "posicao": "ATA", 
  "preco": 15.00,
  "clubeId": 1
}
```

### Gerenciar Rodadas (`/admin/rodadas`)

#### Criar Nova Rodada
```http
POST /admin/rodadas?numero=2
Authorization: Bearer <admin_token>
```

#### Atualizar Status da Rodada
```http
PUT /admin/rodadas/1/status?status=MERCADO_FECHADO
Authorization: Bearer <admin_token>
```

**Status Disponíveis:**
- `MERCADO_ABERTO` → Usuários podem alterar escalações
- `MERCADO_FECHADO` → Escalações travadas, rodada em andamento
- `ENCERRADA` → Rodada finalizada com pontuações registradas

### Gerenciar Pontuações

#### Registrar Pontuação de Um Jogador
```http
POST /admin/rodadas/1/jogadores/15/pontuacao
Authorization: Bearer <admin_token>
Content-Type: application/json

{
  "pontos": 8.5
}
```

#### Registrar Múltiplas Pontuações
```http
POST /admin/rodadas/1/pontuacoes/multiplas
Authorization: Bearer <admin_token>
Content-Type: application/json

{
  "pontuacoesPorJogador": {
    "idJogador": pontuacao,
    "15": 12.0,
    "22": 6.0,
    "35": 9.5
  }
}
```

#### Calcular Pontuações dos Times Fantasia
```http
POST /admin/rodadas/1/times/pontuacao
Authorization: Bearer <admin_token>
```

**Fluxo Recomendado para Admins:**
1. Criar nova rodada com `MERCADO_ABERTO`
2. Usuários fazem suas escalações
3. Alterar status para `MERCADO_FECHADO` 
4. Registrar pontuações dos jogadores
5. Calcular pontuações dos times fantasia
6. Alterar status para `ENCERRADA`

### Controle de Temporada

#### Iniciar Nova Temporada
```http
POST /admin/temporada/nova
Authorization: Bearer <admin_token>
```

**CUIDADO:** Reseta todas as pontuações acumuladas!

---

## Exemplos Práticos de Uso

### 1. Fluxo do Usuário Comum

```bash
# 1. Registrar-se
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"nome":"Meu Nome","email":"eu@email.com","senha":"123456"}'

# 2. Fazer login e pegar o token
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"eu@email.com","senha":"123456"}'

# 3. Criar time fantasia
curl -X POST http://localhost:8080/times-fantasia \
  -H "Authorization: Bearer SEU_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"nome":"Meu Time"}'

# 4. Ver jogadores disponíveis
curl http://localhost:8080/jogadores

# 5. Atualizar escalação (exemplo com 11 jogadores)
curl -X PUT "http://localhost:8080/times-fantasia/me/escalacao?formacao=F_4_3_3" \
  -H "Authorization: Bearer SEU_TOKEN" \
  -H "Content-Type: application/json" \
  -d '[1,2,3,4,5,6,7,8,9,10,11]'
```

### 2. Fluxo do Administrador

```bash
# 1. Login como admin
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@springfantasy.com","senha":"admin123"}'

# 2. Criar rodada 2
curl -X POST "http://localhost:8080/admin/rodadas?numero=2" \
  -H "Authorization: Bearer ADMIN_TOKEN"

# 3. Fechar mercado da rodada 1  
curl -X PUT "http://localhost:8080/admin/rodadas/1/status?status=MERCADO_FECHADO" \
  -H "Authorization: Bearer ADMIN_TOKEN"

# 4. Registrar pontuação do Hulk (ID 1) na rodada 1
curl -X POST http://localhost:8080/admin/rodadas/1/jogadores/1/pontuacao \
  -H "Authorization: Bearer ADMIN_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"pontos":10.5}'

# 5. Calcular pontuações dos times
curl -X POST http://localhost:8080/admin/rodadas/1/times/pontuacao \
  -H "Authorization: Bearer ADMIN_TOKEN"
```

## Documentação Swagger

Acesse a documentação interativa completa em:
**http://localhost:8080/swagger-ui/index.html**

A documentação Swagger permite:
- Testar todas as rotas diretamente no navegador
- Ver exemplos de request/response  
- Autenticar-se e usar o token automaticamente
- Ver todos os modelos de dados (DTOs)

## Dados Pré-carregados

O sistema já vem com:
- **20 clubes** do Brasileirão (Atlético-MG, Botafogo, Ceará, etc.)
- **500+ jogadores reais** com posições e preços
- **2 usuários** de teste (admin e user comum)
- **Rodada 1** criada com mercado aberto

---

**Divirta-se criando seu time dos sonhos no Spring Fantasy!**
