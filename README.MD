# Sistema de Gestão de Equipes e Projetos (SG-EP)

## 🎯 Sobre o Projeto
O Sistema de Gestão de Equipes e Projetos (SG-EP) é uma solução desktop desenvolvida em **Java** para gerenciar as três entidades fundamentais de qualquer organização: **Usuários**, **Equipes** e **Projetos**.

O objetivo principal é fornecer uma interface gráfica intuitiva para realizar operações de **CRUD** (Create, Read, Update, Delete) com persistência de dados em um banco de dados **MySQL**, garantindo a integridade e a segurança das informações.

### ✨ Diferencial e Funcionalidades

* **Autenticação Segura:** Sistema de Login com validação de credenciais contra o banco de dados.
* **Controle de Acesso (Perfil):** O menu principal exibe as funcionalidades de gestão apenas para usuários com perfil `admin`, garantindo a segurança dos dados.
* **Interface Gráfica (GUI):** Telas de gestão intuitivas desenvolvidas com **Java Swing**.
* **Camada de Persistência (DAO):** Separação clara das responsabilidades de acesso ao banco de dados.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 24
* **Interface:** Java Swing
* **Banco de Dados:** MySQL
* **Conexão:** JDBC e MySQL Connector/J

## ⚙️ Como Executar o Projeto

Siga os passos abaixo para configurar e executar o projeto em sua máquina local.

### Pré-requisitos

1.  **Java Development Kit (JDK):** Versão 17 ou superior instalada.
2.  **MySQL Server:** Servidor MySQL ativo (via XAMPP, WAMPP, ou MySQL Workbench).
3.  **MySQL Connector/J:** O driver JDBC precisa estar configurado no classpath do projeto (geralmente como um arquivo `.jar` adicionado às dependências da IDE).

### 1. Configuração do Banco de Dados

Crie o banco de dados e as tabelas (certifique-se de que a sua classe `Conexao.java` esteja apontando para o nome correto do banco).

### 2. Execução da Aplicação

1.  Abra o projeto no **IntelliJ IDEA** (ou IDE de sua preferência).
2.  Verifique se o seu servidor MySQL está rodando.
3.  Execute a classe principal: **`src.Main`**.
4.  A tela de login será exibida.

### Credenciais de Teste

Utilize as seguintes credenciais para acesso como administrador:

| Perfil | Login | Senha |
| :--- | :--- | :--- |
| **Administrador** | `arthur.b` | `1234` |
| **Gestor** | `maria.o` | `1234` |

---

## 📂 Estrutura do Projeto

O projeto segue a arquitetura em camadas, separando as responsabilidades em pacotes:

* `src.Main`: Ponto de entrada da aplicação e menu principal.
* `src.LoginView`: Interface de autenticação.
* `src.*View`: Classes da Interface Gráfica (GUI) (`UsuarioView`, `ProjetoView`, etc.).
* `dao.*DAO`: Camada de Acesso a Dados, com a lógica de CRUD.
* `model.*`: Classes de Modelo (entidades) (`Usuario`, `Projeto`, `Equipe`).

---

## 🧑‍💻 Desenvolvedor

Este projeto foi desenvolvido por:

Arthur Rezende Bertizola