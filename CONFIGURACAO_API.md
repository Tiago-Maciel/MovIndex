# 🔑 Configuração da API Key - The Movie DB

## ⚠️ Erro 401 (Unauthorized)

O erro 401 indica que a API Key não está configurada ou é inválida. Siga os passos abaixo para resolver:

## 📋 Passo a Passo

### 1. Obter API Key do The Movie DB

1. Acesse o site [The Movie DB](https://www.themoviedb.org/)
2. Crie uma conta gratuita (se ainda não tiver)
3. Faça login na sua conta
4. Vá em **Settings** (Configurações) → **API**
5. Clique em **Request an API Key** (Solicitar uma chave de API)
6. Selecione **Developer** como tipo de uso
7. Preencha o formulário:
   - **Application Name**: Nome do seu app (ex: "Filmes App")
   - **Application URL**: Pode deixar vazio ou colocar `http://localhost`
   - **Application Summary**: Descrição do app (ex: "App Android para listar filmes")
8. Aceite os termos e clique em **Submit**
9. Copie a **API Key (v3 auth)** que será gerada

### 2. Configurar a API Key no Projeto

**Opção 1: Usando local.properties (RECOMENDADO - Mais Seguro)**

1. Abra o arquivo `local.properties` na raiz do projeto
2. Adicione a seguinte linha (substitua pela sua API Key):
   ```
   TMDB_API_KEY=sua_api_key_aqui
   ```
3. Exemplo:
   ```
   TMDB_API_KEY=1234567890abcdef1234567890abcdef
   ```

**Opção 2: Configuração Direta (Não recomendado para repositórios públicos)**

1. Abra o arquivo: `app/src/main/java/com/example/filmes/core/constants/ApiConstants.kt`
2. Localize a linha com `"SUA_API_KEY_AQUI"` no fallback
3. Substitua pela sua API Key

### 3. Sincronizar e Executar

1. Sincronize o projeto (Sync Project with Gradle Files)
2. Limpe o projeto (Build → Clean Project)
3. Reconstrua o projeto (Build → Rebuild Project)
4. Execute o aplicativo novamente

## ✅ Verificação

Após configurar a API Key corretamente, o erro 401 deve desaparecer e o aplicativo deve carregar os filmes normalmente.

## 🔒 Segurança

⚠️ **IMPORTANTE**: 
- O arquivo `local.properties` já está configurado para NÃO ser commitado (está no .gitignore)
- Use sempre a **Opção 1** (local.properties) para maior segurança
- Nunca commite sua API Key diretamente no código se o repositório for público
- O projeto já está configurado para ler do `local.properties` automaticamente

## 🆘 Problemas Comuns

- **Erro 401 persistente**: Verifique se copiou a API Key completa (geralmente tem 32 caracteres)
- **Erro de conexão**: Verifique sua conexão com a internet
- **Rate limit**: A API tem limite de requisições por dia (geralmente 40 requisições/10 segundos)
