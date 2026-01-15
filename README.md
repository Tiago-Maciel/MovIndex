# Filmes - Desafio Dex Android

Aplicativo Android desenvolvido em Kotlin com Jetpack Compose para exibir informações sobre filmes utilizando a API do The Movie DB.

## 🚀 Funcionalidades

- ✅ Listagem de filmes populares com paginação
- ✅ Pesquisa de filmes
- ✅ Detalhes do filme (título, sinopse, data de lançamento, imagem)
- ✅ Sistema de favoritos com armazenamento local (Room Database)
- ✅ Tratamento de erros e estados de carregamento
- ✅ Interface moderna e intuitiva

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

## 📋 Pré-requisitos

1. Android Studio Hedgehog ou superior
2. JDK 11 ou superior
3. API Key do The Movie DB

## 🔑 Configuração da API Key

1. Acesse [The Movie DB](https://www.themoviedb.org/) e crie uma conta
2. Vá em Settings > API e gere uma API Key
3. Abra o arquivo `app/src/main/java/com/example/filmes/data/remote/api/RetrofitClient.kt`
4. Substitua `SUA_API_KEY_AQUI` pela sua API Key:

```kotlin
const val API_KEY = "sua_api_key_aqui"
```

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
```

## 📱 Como Executar

1. Clone o repositório
2. Abra o projeto no Android Studio
3. Configure sua API Key (veja seção acima)
4. Sincronize o projeto (Sync Project with Gradle Files)
5. Execute o aplicativo em um dispositivo ou emulador

## 🎯 Estrutura de Navegação

- **Tela Principal**: Lista de filmes populares com barra de pesquisa
- **Tela de Detalhes**: Informações completas do filme selecionado
- **Tela de Favoritos**: Lista de filmes salvos localmente

## 📝 Notas

- O aplicativo requer conexão com a internet para buscar filmes
- Os favoritos são armazenados localmente no dispositivo
- A paginação é automática ao rolar a lista

## 📄 Licença

Este projeto foi desenvolvido como parte do Desafio Dex Android.
