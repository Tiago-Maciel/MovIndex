# Arquitetura do Projeto Filmes

Este documento descreve a estrutura e organização do projeto seguindo princípios de Clean Architecture.

## 📁 Estrutura de Pastas

```
app/src/main/java/com/example/filmes/
│
├── core/                           # Camada Core - Configurações e constantes
│   ├── constants/
│   │   └── ApiConstants.kt         # Constantes da API (URLs, chaves)
│   ├── database/
│   │   └── DatabaseConstants.kt    # Constantes do banco de dados
│   └── network/
│       └── NetworkConfig.kt         # Configuração de rede (OkHttp)
│
├── data/                           # Camada de Dados
│   ├── datasource/
│   │   ├── local/
│   │   │   └── MovieLocalDataSource.kt    # Data source local (Room)
│   │   └── remote/
│   │       └── MovieRemoteDataSource.kt   # Data source remoto (API)
│   ├── local/
│   │   └── database/               # Entidades e DAOs do Room
│   │       ├── FavoriteMovie.kt
│   │       ├── FavoriteMovieDao.kt
│   │       └── MovieDatabase.kt
│   ├── mapper/                     # Conversores DTO -> Domain
│   │   └── MovieMapper.kt
│   ├── paging/                     # PagingSources para paginação
│   │   ├── MoviePagingSource.kt
│   │   └── SearchMoviePagingSource.kt
│   ├── remote/
│   │   ├── api/                    # Interfaces e cliente Retrofit
│   │   │   ├── MovieApiService.kt
│   │   │   └── RetrofitClient.kt
│   │   └── dto/                    # Data Transfer Objects
│   │       ├── MovieDto.kt
│   │       └── MovieDetailsDto.kt
│   └── repository/
│       └── MovieRepository.kt      # Implementação do repositório
│
├── domain/                         # Camada de Domínio
│   ├── model/                      # Modelos de domínio (entidades)
│   │   ├── Movie.kt
│   │   └── MovieDetails.kt
│   └── repository/                 # Interfaces de repositório
│       └── IMovieRepository.kt
│
├── ui/                             # Camada de Apresentação
│   ├── navigation/                 # Navegação entre telas
│   │   └── Navigation.kt
│   ├── presentation/              # Componentes de UI
│   │   ├── component/              # Componentes reutilizáveis
│   │   │   ├── MovieCard.kt
│   │   │   └── SearchBar.kt
│   │   ├── screen/                 # Telas do aplicativo
│   │   │   ├── MovieListScreen.kt
│   │   │   ├── MovieDetailsScreen.kt
│   │   │   └── FavoritesScreen.kt
│   │   └── viewmodel/              # ViewModels com StateFlow
│   │       ├── MovieListViewModel.kt
│   │       ├── MovieDetailsViewModel.kt
│   │       └── FavoritesViewModel.kt
│   └── theme/                      # Tema do aplicativo
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
│
├── di/                             # Injeção de Dependências (Koin)
│   ├── AppModule.kt                # Módulo de dependências de dados
│   └── ViewModelModule.kt         # Módulo de ViewModels
│
├── MainActivity.kt                 # Activity principal
└── MainApplication.kt             # Application class com Koin

```

## 🏗️ Camadas da Arquitetura

### 1. **Core Layer** (`core/`)
Responsável por:
- Constantes e configurações globais
- Configuração de rede
- Configuração de banco de dados

**Dependências:** Nenhuma (camada mais baixa)

### 2. **Data Layer** (`data/`)
Responsável por:
- Acesso a fontes de dados (API e banco local)
- Conversão de DTOs para modelos de domínio
- Implementação de repositórios
- Paginação de dados

**Estrutura:**
- `datasource/`: Abstração de fontes de dados
  - `remote/`: Acesso à API
  - `local/`: Acesso ao banco de dados
- `repository/`: Implementação dos repositórios
- `mapper/`: Conversores entre camadas
- `paging/`: Fontes de paginação

**Dependências:** `core/`, `domain/`

### 3. **Domain Layer** (`domain/`)
Responsável por:
- Modelos de negócio (entidades)
- Interfaces de repositórios
- Regras de negócio

**Estrutura:**
- `model/`: Entidades de domínio
- `repository/`: Interfaces (contratos)

**Dependências:** Nenhuma (camada pura de Kotlin)

### 4. **UI Layer** (`ui/`)
Responsável por:
- Interface do usuário (Jetpack Compose)
- ViewModels com StateFlow
- Navegação
- Componentes reutilizáveis

**Estrutura:**
- `presentation/`: Componentes de apresentação
  - `screen/`: Telas
  - `component/`: Componentes reutilizáveis
  - `viewmodel/`: ViewModels
- `navigation/`: Configuração de navegação
- `theme/`: Tema do aplicativo

**Dependências:** `domain/`, `data/` (apenas para injeção)

## 🔄 Fluxo de Dados

```
UI (ViewModel) 
    ↓
Domain (Repository Interface)
    ↓
Data (Repository Implementation)
    ↓
Data Sources (Remote/Local)
    ↓
API / Database
```

## 📦 Princípios Aplicados

1. **Separation of Concerns**: Cada camada tem uma responsabilidade específica
2. **Dependency Inversion**: Camadas superiores dependem de abstrações (interfaces)
3. **Single Responsibility**: Cada classe tem uma única responsabilidade
4. **Clean Architecture**: Separação clara entre camadas de dados, domínio e apresentação

## 🔌 Injeção de Dependências

O projeto utiliza **Koin** para injeção de dependências:

- `AppModule`: Configura dependências de dados (API, Database, Repositories)
- `ViewModelModule`: Configura ViewModels

## 📝 Convenções de Nomenclatura

- **Interfaces**: Prefixo `I` (ex: `IMovieRepository`)
- **Data Sources**: Sufixo `DataSource` (ex: `MovieRemoteDataSource`)
- **ViewModels**: Sufixo `ViewModel` (ex: `MovieListViewModel`)
- **Screens**: Sufixo `Screen` (ex: `MovieListScreen`)
- **DTOs**: Sufixo `Dto` (ex: `MovieDto`)
- **Mappers**: Funções de extensão (ex: `MovieDto.toDomain()`)

## 🎯 Benefícios desta Estrutura

1. **Testabilidade**: Fácil criar mocks e testar cada camada isoladamente
2. **Manutenibilidade**: Código organizado e fácil de encontrar
3. **Escalabilidade**: Fácil adicionar novas funcionalidades
4. **Reusabilidade**: Componentes e lógica podem ser reutilizados
5. **Clareza**: Estrutura clara facilita o entendimento do projeto
