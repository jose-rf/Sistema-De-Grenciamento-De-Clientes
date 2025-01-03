
# Sistema de Gestão de Clientes - Clínica de Fisioterapia

Este projeto é um sistema de gestão de clientes desenvolvido em **Java**, com interface gráfica em **Swing**. O sistema permite **cadastrar**, **alterar** e **excluir** clientes de uma clínica de fisioterapia, interagindo com um banco de dados **MySQL**.

## Funcionalidades

- **Cadastro de Clientes**: Cadastro de novos clientes com dados como nome, CPF, endereço, telefone, etc.
- **Alteração de Dados**: Pesquisa um cliente pelo CPF e permite editar seus dados.
- **Exclusão de Clientes**: Exclui um cliente do banco de dados através do CPF.

## Tecnologias

- **Java** (Swing para interface gráfica)
- **JDBC** (para interação com o banco de dados MySQL)
- **MySQL** (banco de dados)

## Banco de Dados

Crie o banco de dados e a tabela de clientes com o seguinte script:

```sql
CREATE DATABASE clinica_fisioterapia;

USE clinica_fisioterapia;

CREATE TABLE CLIENTES (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    endereco VARCHAR(100),
    email VARCHAR(100),
    telefone VARCHAR(15),
    dataNascimento VARCHAR(10),
    historicoMedico TEXT,
    cpf VARCHAR(11) NOT NULL UNIQUE
);
```

## Instruções para Execução

1. **Configuração do Banco de Dados**:
   - Crie o banco de dados e a tabela utilizando o script acima.

2. **Configuração do Projeto**:
   - Abra o projeto em sua IDE.
   - Verifique a conexão com o banco de dados na classe `Conexao.java`. 
     - Modifique a URL, usuário e senha de acordo com seu banco de dados.

3. **Executando o Sistema**:
   - Compile e execute a classe `Main.java`.
   - A interface será aberta, permitindo cadastrar, alterar e excluir clientes.

