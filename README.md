# Filmes - Desafio Android

Aplicativo Android desenvolvido em Kotlin com Jetpack Compose para exibir informações sobre filmes utilizando a API do The Movie DB.

## 🚀 Funcionalidades

- ✅ Listagem de filmes populares com paginação
- ✅ Pesquisa de filmes
- ✅ Detalhes do filme (título, sinopse, data de lançamento, imagem)
- ✅ Sistema de favoritos com armazenamento local (Room Database)

## 🛠️ Stack Tecnológica

- **Kotlin** - Linguagem principal
- **Jetpack Compose** - Interface de usuário
- **Coroutines & Flow** - Programação assíncrona
- **Retrofit** - Consumo de API REST
- **StateFlow** - Gerenciamento de estado
- **Koin** - Injeção de dependências
- **Coil** - Carregamento de imagens
- **Paging 3** - Paginação de dados
- **Room Database** - Armazenamento local

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:

```
app/
├── data/
│   ├── local/          # Room Database
│   ├── remote/         # Retrofit API
│   ├── repository/     # Repositórios
│   └── paging/         # Paging Sources
├── domain/
│   └── model/          # Modelos de domínio
├── ui/
│   ├── component/      # Componentes Compose reutilizáveis
│   ├── screen/         # Telas do aplicativo
│   ├── viewmodel/      # ViewModels com StateFlow
│   └── navigation/     # Navegação
└── di/                 # Módulos Koin
